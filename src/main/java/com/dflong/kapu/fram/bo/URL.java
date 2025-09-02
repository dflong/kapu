package com.dflong.kapu.fram.bo;

import lombok.Data;

@Data
public class URL {

    private String hostname;

    private int port;

    public URL() {
    }

    public URL(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }
}
