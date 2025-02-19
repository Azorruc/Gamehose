package com.gamehouse.weather.application.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
public class MeasuredData {
    private BigDecimal avg;
    private BigDecimal max;
    private BigDecimal min;
}
