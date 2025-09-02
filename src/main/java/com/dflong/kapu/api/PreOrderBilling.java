package com.dflong.kapu.api;

import com.dflong.kapu.api.bo.BillingAmount;
import com.dflong.kapu.api.dto.OrderAmount;

public interface PreOrderBilling {

    BillingAmount getBillingAmount(OrderAmount orderAmount);
}
