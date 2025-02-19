package com.gamehouse.weather.application.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherCreateRequest {

    private String station;

    private LocalDateTime measuredTime;

    private BigDecimal temperature;

    private BigDecimal humidity;

    private BigDecimal windSpeed;
}
