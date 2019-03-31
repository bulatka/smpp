package org.bulatnig.smpp;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bulatnig.smpp.pdu.BindTransceiver;
import org.bulatnig.smpp.pdu.CommandStatus;
import org.bulatnig.smpp.pdu.Pdu;
import org.bulatnig.smpp.pdu.Unbind;

public class SmppSession {

    private static final Logger logger = LogManager.getLogger(SmppSession.class);

    private final SmppClient smppClient;
    private final SessionConfig config;
    private final PduHandler pduHandler;

    private final SeqNumGenerator seqNumGenerator = new SeqNumGenerator();

    private volatile Channel channel;
    private volatile State state = State.DISCONNECTED;

    public SmppSession(SmppClient smppClient, SessionConfig config, PduListener pduListener) {
        this.smppClient = smppClient;
        this.config = config;
        this.pduHandler = new PduHandler(pduListener);

        smppClient.connectNow(config.getHost(), config.getPort(), new ConnectListener());
    }

    private void sendBindRequest() {
        BindTransceiver pdu = new BindTransceiver();
        pdu.setSystemId(config.getSystemId());
        pdu.setPassword(config.getPassword());
        pdu.setSystemType(config.getSystemType());
        pdu.setInterfaceVersion(config.getInterfaceVersion());
        pdu.setAddrNpi(config.getAddrNpi());
        pdu.setAddrTon(config.getAddrTon());
        pdu.setAddressRange(config.getAddressRange());
        send(pdu);
    }

    public void close() {
        if (State.BOUND == state) {
            send(new Unbind());
        }
        state = State.CLOSED;
    }

    private void send(Pdu pdu) {
        pdu.setSequenceNumber(seqNumGenerator.next());
        channel.writeAndFlush(pdu);
    }

    private final class ConnectListener implements ChannelFutureListener {
        @Override
        public void operationComplete(ChannelFuture future) throws Exception {
            if (future.isSuccess()) {
                logger.info("Connect successful");
                channel = future.channel();
                channel.closeFuture().addListener(new DisconnectListener());
                if (State.CLOSED == state) {
                    channel.close();
                } else {
                    state = State.CONNECTED;
                    channel.pipeline().removeLast();
                    channel.pipeline()
                            .addLast(new PduDecoder(config.getName()))
                            .addLast(new PduEncoder(config.getName()))
                            .addLast(new BindRespHandler());
                    sendBindRequest();
                }
            } else {
                if (State.CLOSED == state) {
                    return; // connect failed so we don't need to close anything
                }
                if (future.isDone()) {
                    logger.info("Connect failed. Reconnect in {} seconds", SmppClient.RECONNECT_TIMEOUT);
                    smppClient.connectAfterTimeout(config.getHost(), config.getPort(), new ConnectListener());
                } else { // cancelled
                    logger.info("Connect cancelled");
                    // TODO
                }
            }
        }
    }

    private final class DisconnectListener implements ChannelFutureListener {
        @Override
        public void operationComplete(ChannelFuture future) throws Exception {
            if (State.CLOSED == state) {
                logger.info("Connection closed");
                return;
            }
            logger.info("Connection closed. Reconnecting...");
            state = State.DISCONNECTED;
            channel = null;
            smppClient.connectNow(config.getHost(), config.getPort(), new ConnectListener());
        }
    }

    private final class BindRespHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            Pdu pdu = (Pdu) msg;
            if (CommandStatus.ESME_ROK == pdu.getCommandStatus()) {
                state = State.BOUND;
                channel.pipeline().removeLast();
                channel.pipeline().addLast(pduHandler);
            } else {
                channel.close();
            }
        }
    }

    public enum State {
        DISCONNECTED,
        CONNECTED,
        BOUND,
        CLOSED,
    }
}
