package org.bulatnig.smpp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.bulatnig.smpp.pdu.Pdu;

public class PduHandler extends ChannelInboundHandlerAdapter {

    private final PduListener pduListener;

    public PduHandler(PduListener pduListener) {
        this.pduListener = pduListener;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        pduListener.receive((Pdu)msg);
    }
}
