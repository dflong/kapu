package com.dflong.kapu.fram.register;

import com.alibaba.fastjson.JSONObject;
import com.dflong.kapu.fram.bo.URL;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 无法使用，register时在提供者JVM、getURL时在消费者JVM，无法获取；使用ZK、Redis
public class RemoteZookeeper {

    public static Map<String, List<URL>> map = new HashMap<>();

    public static void register(String interfaceName, URL url) {
        List<URL> urls = map.get(interfaceName);
        if (urls == null) {
            urls = new ArrayList<>();
        }
        urls.add(url);
        map.put(interfaceName, urls);
    }

    public static void unregister(String interfaceName) {
        List<URL> urls = map.get(interfaceName);
        if (urls != null) {
            urls.clear();
        }
    }

    public static List<URL> getURL(String interfaceName) {
        List<URL> urls = map.get(interfaceName);
        return urls;
    }


}
