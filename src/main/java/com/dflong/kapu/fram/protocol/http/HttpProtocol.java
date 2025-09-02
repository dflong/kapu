package com.dflong.kapu.fram.protocol.http;

import com.alibaba.fastjson.JSON;
import com.dflong.kapu.fram.Invocation;
import com.dflong.kapu.fram.protocol.Protocol;
import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class HttpProtocol implements Protocol {

    public void start(String hostname, int port) {
        Tomcat tomcat = new Tomcat();

        Server server = tomcat.getServer();
        Service service = server.findService("Tomcat");

        Connector connector = new Connector();
        connector.setPort(port);

        Engine engine = new StandardEngine();
        engine.setDefaultHost(hostname);

        Host host = new StandardHost();
        host.setName(hostname);

        String contextPath = "";
        Context context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());

        host.addChild(context);
        engine.addChild(host);

        service.setContainer(engine);
        service.addConnector(connector);

        tomcat.addServlet(contextPath, "dispatcher", new DispatcherServlet1());
        context.addServletMappingDecoded("/*", "dispatcher");

        try {
            tomcat.start();
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }
    }

    public String send(String hostname, int port, Invocation invocation) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.postForObject(new URI("http", "", hostname, port, "/", null, null),
                    JSON.toJSONString(invocation), String.class);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
