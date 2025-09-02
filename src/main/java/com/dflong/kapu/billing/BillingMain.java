package com.dflong.kapu.billing;

import com.dflong.kapu.api.PreOrderBilling;
import com.dflong.kapu.fram.bo.URL;
import com.dflong.kapu.fram.protocol.Protocol;
import com.dflong.kapu.fram.protocol.ProtocolFactory;
import com.dflong.kapu.fram.protocol.http.HttpProtocol;
import com.dflong.kapu.fram.register.RemoteZookeeper;
import com.dflong.kapu.fram.register.StaticZookeeper;

public class BillingMain {

    // http://localhost:8081/
    public static void main(String[] args) {
        StaticZookeeper.register(PreOrderBilling.class.getName(), PreOrderBillingImpl.class);

        URL url = new URL("localhost", 8081);
        RemoteZookeeper.register(PreOrderBilling.class.getName(), url);

        Protocol protocol = ProtocolFactory.getProtocol();
        protocol.start(url.getHostname(), url.getPort());
    }
}
