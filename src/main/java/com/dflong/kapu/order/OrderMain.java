package com.dflong.kapu.order;

import com.alibaba.fastjson.JSONObject;
import com.dflong.kapu.api.PreOrderBilling;
import com.dflong.kapu.api.bo.BillingAmount;
import com.dflong.kapu.api.dto.OrderAmount;
import com.dflong.kapu.fram.proxy.ProxyFactory;

public class OrderMain {

    public static void main(String[] args) {
        CreateOrder createOrder = new CreateOrder();
        OrderAmount orderAmount = createOrder.create();

        PreOrderBilling preOrderBilling = ProxyFactory.createProxy(PreOrderBilling.class);
        BillingAmount billingAmount = preOrderBilling.getBillingAmount(orderAmount);

        System.out.println("result over ... " + JSONObject.toJSONString(billingAmount));
    }
}
