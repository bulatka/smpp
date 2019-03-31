package org.bulatnig.smpp.pdu;

import io.netty.buffer.ByteBuf;

public class Unbind extends Pdu {

    public Unbind() {
        super(CommandId.UNBIND);
    }

    public Unbind(ByteBuf buf) {
        super(buf);
    }
}
