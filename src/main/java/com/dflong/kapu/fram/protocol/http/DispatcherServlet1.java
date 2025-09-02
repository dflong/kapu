package com.dflong.kapu.fram.protocol.http;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet1 extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        HttpServerHandler handler = new HttpServerHandler();
        handler.handler(req, resp);
    }
}
