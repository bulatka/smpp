package org.bulatnig.smpp.pdu;

import io.netty.buffer.ByteBuf;

public class BindTransceiverResp extends Pdu {

    private String systemId;

    public BindTransceiverResp() {
        super(CommandId.BIND_TRANSCEIVER);
    }

    public BindTransceiverResp(ByteBuf buf) {
        super(buf);
        this.systemId = readCOctetString(buf);
    }

    @Override
    protected void writeBody(ByteBuf buf) {
        writeCOctetString(buf, systemId);
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }
}
