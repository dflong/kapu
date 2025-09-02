package com.dflong.kapu.api.bo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BillingAmount {

    String contractId;

    BigDecimal totalAMount;

    BigDecimal reduceAmount;

    BigDecimal realAmount;
}
