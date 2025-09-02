package com.dflong.kapu.fram.loadBalance;

import com.alibaba.fastjson.JSONObject;
import com.dflong.kapu.fram.bo.URL;

import java.util.Collections;
import java.util.List;

public class LoadBalance {

    public static URL random(List<URL> urls) {

        return new URL("localhost", 8081);
    }
}
