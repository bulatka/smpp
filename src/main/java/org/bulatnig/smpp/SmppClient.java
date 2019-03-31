package org.bulatnig.smpp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SmppClient {

    private static final Logger logger = LogManager.getLogger(SmppClient.class);

    static final String LISTENER_STUB_NAME = "";
    static final int RECONNECT_TIMEOUT = 10;

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final EventLoopGroup workerGroup;
    private final Bootstrap bootstrap;

    public SmppClient(int nioThreads) {
        workerGroup = new NioEventLoopGroup(nioThreads);
        bootstrap = new Bootstrap();
        bootstrap.group(workerGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(LISTENER_STUB_NAME, new ChannelInboundHandlerAdapter());
            }
        });
    }

    public SmppSession createSession(SessionConfig config, PduListener pduListener) {
        return new SmppSession(this, config, pduListener);
    }

    void connectNow(String host, int port, ChannelFutureListener listener) {
        bootstrap.connect(host, port).addListener(listener);
    }

    void connectAfterTimeout(String host, int port, ChannelFutureListener listener) {
        scheduler.schedule(() -> connectNow(host, port, listener), RECONNECT_TIMEOUT, TimeUnit.SECONDS);
    }

    public void shutdown() throws Exception {
        scheduler.shutdown();
        workerGroup.shutdownGracefully();
    }


}
