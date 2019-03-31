package org.bulatnig.smpp;

import org.bulatnig.smpp.pdu.Pdu;

public interface PduProvider {

    Pdu next();

}
