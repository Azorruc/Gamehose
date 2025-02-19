package com.gamehouse.weather.application.controller.response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class LastWeatherResponseTest {

    @Test
    public void when_response_is_created_then_data_can_be_retrieved() {
        LastWeatherResponse response = new LastWeatherResponse()
                .setStation("ABC")
                .setMeasuredTime(LocalDateTime.of(2025,2,18,15, 30, 0))
                .setSavedTime(LocalDateTime.of(2025,2,18,15, 31, 0))
                .setTemperature(BigDecimal.valueOf(15.0))
                .setHumidity(BigDecimal.valueOf(60.50))
                .setWindSpeed(BigDecimal.valueOf(70.50));

        assertEquals("ABC", response.getStation());
        assertEquals(LocalDateTime.of(2025,2,18,15, 30, 0), response.getMeasuredTime());
        assertEquals(LocalDateTime.of(2025,2,18,15, 31, 0), response.getSavedTime());
        assertEquals(BigDecimal.valueOf(15.0), response.getTemperature());
        assertEquals(BigDecimal.valueOf(60.50), response.getHumidity());
        assertEquals(BigDecimal.valueOf(70.50), response.getWindSpeed());
    }
}
