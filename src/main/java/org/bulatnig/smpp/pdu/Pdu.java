package org.bulatnig.smpp.pdu;

import io.netty.buffer.ByteBuf;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class Pdu {
    public static final int HEADER_LENGTH = 16;

    private final int commandId;
    private int commandStatus;
    private int sequenceNumber;

//    private List<Tlv> tlvs;

    public Pdu(int commandId) {
        this.commandId = commandId;
    }

    public Pdu(ByteBuf buf) {
        buf.readInt(); // ignore command_length as we don't store it
        this.commandId = buf.readInt();
        this.commandStatus = buf.readInt();
        this.sequenceNumber = buf.readInt();
    }

    public void write(ByteBuf buf) {
        writeHeader(buf);
        writeBody(buf);
        writeTlv(buf);
        writeCommandLength(buf);
    }

    private void writeHeader(ByteBuf buf) {
        buf.writeInt(0); // reserve space for command_length
        buf.writeInt(commandId);
        buf.writeInt(commandStatus);
        buf.writeInt(sequenceNumber);
    }

    protected void writeBody(ByteBuf buf) {

    }

    private void writeTlv(ByteBuf buf) {
        // TODO
    }

    private void writeCommandLength(ByteBuf buf) {
        int length = buf.writerIndex();
        buf.resetWriterIndex();
        buf.writeInt(length);
        buf.writerIndex(length);
    }

    protected void writeCOctetString(ByteBuf buf, String value) {
        if (value != null) {
            buf.writeBytes(value.getBytes(StandardCharsets.ISO_8859_1));
        }
        buf.writeByte((byte) 0x00);
    }

    protected String readCOctetString(ByteBuf buf) {
        int maxLength = buf.readableBytes();
        int offset = buf.readerIndex();
        int zeroPos = 0;
        while ((zeroPos < maxLength) && (buf.getByte(zeroPos + offset) != 0x00)) {
            zeroPos++;
        }
        if (zeroPos >= maxLength) {
            // TODO
        }
        String result = null;
        if (zeroPos > 0) {
            byte[] bytes = new byte[zeroPos];
            buf.readBytes(bytes);
                result = new String(bytes, StandardCharsets.ISO_8859_1);
        }
        buf.readByte(); // read zero byte
        return result;
    }

    public int getCommandId() {
        return commandId;
    }

    public int getCommandStatus() {
        return commandStatus;
    }

    public void setCommandStatus(int commandStatus) {
        this.commandStatus = commandStatus;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
}
