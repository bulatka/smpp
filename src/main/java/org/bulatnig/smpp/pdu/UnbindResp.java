package org.bulatnig.smpp.pdu;

import io.netty.buffer.ByteBuf;

public class UnbindResp extends Pdu {

    public UnbindResp() {
        super(CommandId.UNBIND_RESP);
    }

    public UnbindResp(ByteBuf buf) {
        super(buf);
    }
}
