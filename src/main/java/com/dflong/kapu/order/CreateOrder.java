package com.dflong.kapu.order;

import com.dflong.kapu.api.dto.OrderAmount;

import java.math.BigDecimal;

public class CreateOrder {

    public OrderAmount create() {
        OrderAmount orderAmount = new OrderAmount();
        orderAmount.setContractId("MC000001");
        orderAmount.setTotalAMount(new BigDecimal("250.00"));
        orderAmount.setReduceAmount(new BigDecimal("68.90"));

        return orderAmount;
    }
}
