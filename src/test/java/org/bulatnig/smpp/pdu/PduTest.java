package org.bulatnig.smpp.pdu;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.UnpooledByteBufAllocator;
import org.junit.Test;

public class PduTest {

    @Test
    public void t() {
        UnpooledByteBufAllocator bufAllocator = new UnpooledByteBufAllocator(false, true);
        ByteBuf buf = bufAllocator.buffer();
        EnquireLink pdu = new EnquireLink();
        pdu.setCommandStatus(2);
        pdu.setSequenceNumber(3);
        pdu.write(buf);
        System.out.println(ByteBufUtil.prettyHexDump(buf));
    }

}
