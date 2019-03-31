package org.bulatnig.smpp;

public class SessionConfig {

    private String name;
    // connection
    private String host;
    private int port;
    // TODO ssl
    // bind
    private String systemId;
    private String password;
    private String systemType;
    private byte interfaceVersion;
    private byte addrTon;
    private byte addrNpi;
    private String addressRange;

    // millis after which request is considered failed and exception raised
    private int requestTimeout;
    // period of inactivity since last PDU sent, in millis
    // after which it's necessary to send EnquireLink request to keep connection open
    private int enquireLinkTimeout;
    // max amount of submit_sm requests pending responses
    private int window;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public byte getInterfaceVersion() {
        return interfaceVersion;
    }

    public void setInterfaceVersion(byte interfaceVersion) {
        this.interfaceVersion = interfaceVersion;
    }

    public byte getAddrTon() {
        return addrTon;
    }

    public void setAddrTon(byte addrTon) {
        this.addrTon = addrTon;
    }

    public byte getAddrNpi() {
        return addrNpi;
    }

    public void setAddrNpi(byte addrNpi) {
        this.addrNpi = addrNpi;
    }

    public String getAddressRange() {
        return addressRange;
    }

    public void setAddressRange(String addressRange) {
        this.addressRange = addressRange;
    }

    public int getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(int requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public int getEnquireLinkTimeout() {
        return enquireLinkTimeout;
    }

    public void setEnquireLinkTimeout(int enquireLinkTimeout) {
        this.enquireLinkTimeout = enquireLinkTimeout;
    }

    public int getWindow() {
        return window;
    }

    public void setWindow(int window) {
        this.window = window;
    }
}
