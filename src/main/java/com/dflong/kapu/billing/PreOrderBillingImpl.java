package com.dflong.kapu.billing;

import com.dflong.kapu.api.PreOrderBilling;
import com.dflong.kapu.api.bo.BillingAmount;
import com.dflong.kapu.api.dto.OrderAmount;

public class PreOrderBillingImpl implements PreOrderBilling {
    @Override
    public BillingAmount getBillingAmount(OrderAmount orderAmount) {
        BillingAmount billingAmount = new BillingAmount();
        billingAmount.setContractId(orderAmount.getContractId());
        billingAmount.setTotalAMount(orderAmount.getTotalAMount());
        billingAmount.setReduceAmount(orderAmount.getReduceAmount());
        billingAmount.setRealAmount(orderAmount.getTotalAMount().subtract(orderAmount.getReduceAmount()));
        return billingAmount;
    }

}
