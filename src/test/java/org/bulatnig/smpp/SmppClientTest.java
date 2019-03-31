package org.bulatnig.smpp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class SmppClientTest {

    private static final Logger logger = LogManager.getLogger(SmppClientTest.class);

    @Test
    public void t() throws Exception {
        SmppClient smppClient = new SmppClient(1);
        SessionConfig config = new SessionConfig();
        config.setName("local");
        config.setHost("localhost");
        config.setPort(8090);
        config.setSystemId("smppclient1");
        config.setPassword("password");
        SmppSession session = smppClient.createSession(config, pdu -> logger.info(pdu));

        Thread.sleep(5_000);
        session.close();
        Thread.sleep(60_000);
        smppClient.shutdown();
    }

}
