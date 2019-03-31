package org.bulatnig.smpp.pdu;

public class CommandStatus {

    public static final int ESME_ROK = 0x00000000;
    public static final int ESME_RINVMSGLEN = 0x00000001;
    public static final int ESME_RINVCMDLEN = 0x00000002;
    public static final int ESME_RINVCMDID = 0x00000003;
    public static final int ESME_RINVBNDSTS = 0x00000004;
    public static final int ESME_RALYBND = 0x00000005;
    public static final int ESME_RINVPRTFLG = 0x00000006;
    public static final int ESME_RINVREGDLVFLG = 0x00000007;
    public static final int ESME_RSYSERR = 0x00000008;
    public static final int ESME_RINVSRCADR = 0x0000000A;
    public static final int ESME_RINVDSTADR = 0x0000000B;
    public static final int ESME_RINVMSGID = 0x0000000C;
    public static final int ESME_RBINDFAIL = 0x0000000D;
    public static final int ESME_RINVPASWD = 0x0000000E;
    public static final int ESME_RINVSYSID = 0x0000000F;
    public static final int ESME_RCANCELFAIL = 0x00000011;
    public static final int ESME_RREPLACEFAIL = 0x00000013;
    public static final int ESME_RMSGQFUL = 0x00000014;
    public static final int ESME_RINVSERTYP = 0x00000015;
    public static final int ESME_RINVNUMDESTS = 0x00000033;
    public static final int ESME_RINVDLNAME = 0x00000034;
    public static final int ESME_RINVDESTFLAG = 0x00000040;
    public static final int ESME_RINVSUBREP = 0x00000042;
    public static final int ESME_RINVESMCLASS = 0x00000043;
    public static final int ESME_RCNTSUBDL = 0x00000044;
    public static final int ESME_RSUBMITFAIL = 0x00000045;
    public static final int ESME_RINVSRCTON = 0x00000048;
    public static final int ESME_RINVSRCNPI = 0x00000049;
    public static final int ESME_RINVDSTTON = 0x00000050;
    public static final int ESME_RINVDSTNPI = 0x00000051;
    public static final int ESME_RINVSYSTYP = 0x00000053;
    public static final int ESME_RINVREPFLAG = 0x00000054;
    public static final int ESME_RINVNUMMSGS = 0x00000055;
    public static final int ESME_RTHROTTLED = 0x00000058;
    public static final int ESME_RINVSCHED = 0x00000061;
    public static final int ESME_RINVEXPIRY = 0x00000062;
    public static final int ESME_RINVDFTMSGID = 0x00000063;
    public static final int ESME_RX_T_APPN = 0x00000064;
    public static final int ESME_RX_P_APPN = 0x00000065;
    public static final int ESME_RX_R_APPN = 0x00000066;
    public static final int ESME_RQUERYFAIL = 0x00000067;
    public static final int ESME_RINVOPTPARSTREAM = 0x000000C0;
    public static final int ESME_ROPTPARNOTALLWD = 0x000000C1;
    public static final int ESME_RINVPARLEN = 0x000000C2;
    public static final int ESME_RMISSINGOPTPARAM = 0x000000C3;
    public static final int ESME_RINVOPTPARAMVAL = 0x000000C4;
    public static final int ESME_RDELIVERYFAILURE = 0x000000FE;
    public static final int ESME_RUNKNOWNERR = 0x000000FF;
    
}
