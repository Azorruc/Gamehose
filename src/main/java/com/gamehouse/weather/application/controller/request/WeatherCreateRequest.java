package com.gamehouse.weather.application.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherCreateRequest {

    public static final String GLOBAL_DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm:ss.SSS";

    private String station;

    @DateTimeFormat(pattern = GLOBAL_DATE_TIME_PATTERN)
    @JsonFormat(pattern = GLOBAL_DATE_TIME_PATTERN)
    private LocalDateTime measuredTime;

    private BigDecimal temperature;

    private BigDecimal humidity;

    private BigDecimal windSpeed;
}
