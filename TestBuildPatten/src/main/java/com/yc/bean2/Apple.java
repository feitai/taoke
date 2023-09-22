package com.yc.bean2;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Apple {
    private String name;
    private String color;
}
