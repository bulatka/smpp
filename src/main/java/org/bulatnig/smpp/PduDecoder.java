package org.bulatnig.smpp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.apache.logging.log4j.CloseableThreadContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bulatnig.smpp.pdu.BindTransceiverResp;
import org.bulatnig.smpp.pdu.CommandId;
import org.bulatnig.smpp.pdu.Pdu;
import org.bulatnig.smpp.pdu.UnbindResp;

import java.util.List;

public class PduDecoder extends ByteToMessageDecoder {

    private static final Logger logger = LogManager.getLogger("pdu");

    private final String session;

    public PduDecoder(String session) {
        this.session = session;
    }

    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out)
            throws Exception {
        try (final CloseableThreadContext.Instance ctc = CloseableThreadContext.put("session", session)) {
            if (logger.isTraceEnabled()) {
                logger.trace("Received {}", ByteBufUtil.hexDump(buf));
            }
            if (buf.readableBytes() < Pdu.HEADER_LENGTH) {
                return;
            }
            int length = buf.readInt();
            int commandId = buf.readInt();
            buf.resetReaderIndex();

            if (buf.readableBytes() < length) {
                return;
            }
            ByteBuf pduBytes = buf.readBytes(length);
            if (logger.isDebugEnabled()) {
                logger.debug("<< {}", ByteBufUtil.hexDump(pduBytes));
            }
            Pdu pdu = parsePdu(commandId, pduBytes);
            if (pdu != null) {
                out.add(pdu);
            }
        }
    }

    private Pdu parsePdu(int commandId, ByteBuf buf) {
        if (CommandId.BIND_TRANSCEIVER_RESP == commandId) {
            return new BindTransceiverResp(buf);
        } else if (CommandId.UNBIND_RESP == commandId) {
            return new UnbindResp(buf);
        } else {
            return null;
        }
    }
}
