package com.gamehouse.weather.application.controller.request;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class WeatherCreateRequestTest {

    @Test
    public void when_request_is_created_then_data_can_be_retrieved() {
        WeatherCreateRequest request = new WeatherCreateRequest()
                .setStation("ABC")
                .setMeasuredTime(LocalDateTime.of(2025,2,18,15, 30, 0))
                .setTemperature(BigDecimal.valueOf(15.0))
                .setHumidity(BigDecimal.valueOf(60.50))
                .setWindSpeed(BigDecimal.valueOf(70.50));

        assertEquals("ABC", request.getStation());
        assertEquals(LocalDateTime.of(2025,2,18,15, 30, 0), request.getMeasuredTime());
        assertEquals(BigDecimal.valueOf(15.0), request.getTemperature());
        assertEquals(BigDecimal.valueOf(60.50), request.getHumidity());
        assertEquals(BigDecimal.valueOf(70.50), request.getWindSpeed());
    }
}
