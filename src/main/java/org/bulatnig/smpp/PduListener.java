package org.bulatnig.smpp;

import org.bulatnig.smpp.pdu.Pdu;

public interface PduListener {

    void receive(Pdu pdu);

}
