package com.dflong.kapu.fram.protocol;

import com.dflong.kapu.fram.protocol.http.HttpProtocol;
import com.dflong.kapu.fram.protocol.dubbo.NettyProtocol;

public class ProtocolFactory {

    public static Protocol getProtocol() {
        String protocolName = System.getProperty("protocolName");

        if ("dubbo".equals(protocolName)) {
            return new NettyProtocol();
        }
        return new HttpProtocol();
    }
}
