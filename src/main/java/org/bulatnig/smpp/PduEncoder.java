package org.bulatnig.smpp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.apache.logging.log4j.CloseableThreadContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bulatnig.smpp.pdu.Pdu;

public class PduEncoder extends MessageToByteEncoder<Pdu> {

    private static final Logger logger = LogManager.getLogger("pdu");

    private final String session;

    public PduEncoder(String session) {
        this.session = session;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Pdu msg, ByteBuf out) throws Exception {
        try (final CloseableThreadContext.Instance ctc = CloseableThreadContext.put("session", session)) {
            msg.write(out);
            if (logger.isDebugEnabled()) {
                logger.debug(">> {}", ByteBufUtil.hexDump(out));
            }
        }
    }
}
