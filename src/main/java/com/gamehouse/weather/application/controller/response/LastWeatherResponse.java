package com.gamehouse.weather.application.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Accessors(chain = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LastWeatherResponse {

    private String station;
    private LocalDateTime measuredTime;
    private LocalDateTime savedTime;
    private BigDecimal temperature;
    private BigDecimal humidity;
    private BigDecimal windSpeed;
}
