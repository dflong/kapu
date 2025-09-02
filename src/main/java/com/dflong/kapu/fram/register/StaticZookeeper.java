package com.dflong.kapu.fram.register;

import java.util.*;

public class StaticZookeeper {

    public static Map<String, Class> map = new HashMap<>();

    public static void register(String interfaceName, Class clazz) {
        map.put(interfaceName, clazz);
    }

    public static Class get(String interfaceName) {
        return map.get(interfaceName);
    }
}
