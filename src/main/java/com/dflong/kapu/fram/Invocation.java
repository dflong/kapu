package com.dflong.kapu.fram;

import com.dflong.kapu.api.dto.OrderAmount;
import lombok.Data;

@Data
public class Invocation {

    private String interfaceName;

    private String methodName;

    private Class[] parameterTypes;

    private Object[] parameters;

}
