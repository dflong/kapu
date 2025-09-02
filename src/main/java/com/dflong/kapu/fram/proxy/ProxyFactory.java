package com.dflong.kapu.fram.proxy;

import com.alibaba.fastjson.JSONObject;
import com.dflong.kapu.fram.Invocation;
import com.dflong.kapu.fram.bo.URL;
import com.dflong.kapu.fram.loadBalance.LoadBalance;
import com.dflong.kapu.fram.protocol.Protocol;
import com.dflong.kapu.fram.protocol.ProtocolFactory;
import com.dflong.kapu.fram.protocol.http.HttpProtocol;
import com.dflong.kapu.fram.register.RemoteZookeeper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class ProxyFactory {

    public static <T> T createProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] {clazz}, new InvocationHandler() {
            // 实现代理方法
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Invocation invocation = new Invocation();
                invocation.setInterfaceName(clazz.getName());
                invocation.setMethodName(method.getName());
                invocation.setParameterTypes(method.getParameterTypes());
                invocation.setParameters(args);

                // 注册中心获取提供者
                List<URL> urls = RemoteZookeeper.getURL(clazz.getName());
                // 负载均衡
                URL url = LoadBalance.random(urls);

                Protocol protocol = ProtocolFactory.getProtocol();
                String result = protocol.send(url.getHostname(), url.getPort(), invocation);
                return JSONObject.parseObject(result, method.getReturnType());
            }
        });
    }
}
