package com.dflong.kapu.fram.protocol.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dflong.kapu.fram.Invocation;
import com.dflong.kapu.fram.register.StaticZookeeper;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class HttpServerHandler {

    public void handler(HttpServletRequest req, HttpServletResponse resp) {
        // 请求处理
        System.out.println("http handler start ...");
        try {
            Invocation invocation = JSONObject.parseObject(req.getInputStream(), Invocation.class);
            String interfaceName = invocation.getInterfaceName();

            Class implClazz = StaticZookeeper.get(interfaceName);
            Method method = implClazz.getMethod(invocation.getMethodName(), invocation.getParameterTypes());

            JSONObject parameter = (JSONObject)invocation.getParameters()[0];
            Class parameterType = invocation.getParameterTypes()[0];
            Object result = method.invoke(implClazz.newInstance(), JSONObject.parseObject(parameter.toJSONString(), parameterType));
            IOUtils.write(JSON.toJSONString(result), resp.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
        System.out.println("http handler end ...");
    }
}
