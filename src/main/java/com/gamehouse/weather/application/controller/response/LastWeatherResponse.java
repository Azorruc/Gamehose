package com.gamehouse.weather.application.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
public class LastWeatherResponse {

    private String station;
    private LocalDateTime measuredTime;
    private LocalDateTime receivedTime;
    private BigDecimal temperature;
    private BigDecimal humidity;
    private BigDecimal windSpeed;
}
