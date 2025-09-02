package com.dflong.kapu.fram.protocol;

import com.dflong.kapu.fram.Invocation;

public interface Protocol {

    public void start(String hostname, int port);

    public String send(String hostname, int port, Invocation invocation);
}
