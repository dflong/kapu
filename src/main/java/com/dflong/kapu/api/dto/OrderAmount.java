package com.dflong.kapu.api.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderAmount {

    String contractId;

    BigDecimal totalAMount;

    BigDecimal reduceAmount;

    BigDecimal realAmount;
}
