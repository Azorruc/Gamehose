package com.gamehouse.weather.application.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
public class WeatherCreateRequest {

    private String station;

    private LocalDateTime measuredTime;

    private BigDecimal temperature;

    private BigDecimal humidity;

    private BigDecimal windSpeed;
}
