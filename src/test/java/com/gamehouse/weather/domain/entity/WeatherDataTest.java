package com.gamehouse.weather.domain.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class WeatherDataTest {

    @Test
    public void when_data_is_created_then_data_can_be_retrieved() {
        WeatherData weatherData = WeatherData.builder()
                .id(1L)
                .station("ABC")
                .localTime(LocalDateTime.of(2025,2,18,10,30,0))
                .receptionTime(LocalDateTime.of(2025,2,18,10,31,0))
                .temperature(BigDecimal.valueOf(22.00))
                .humidity(BigDecimal.valueOf(80.00))
                .windSpeed(BigDecimal.TEN)
                .build();

        assertEquals(1L, weatherData.getId());
        assertEquals("ABC", weatherData.getStation());
        assertEquals(LocalDateTime.of(2025,2,18,10,30,0), weatherData.getLocalTime());
        assertEquals(LocalDateTime.of(2025,2,18,10,31,0), weatherData.getReceptionTime());
        assertEquals(BigDecimal.valueOf(22.00), weatherData.getTemperature());
        assertEquals(BigDecimal.valueOf(80.00), weatherData.getHumidity());
        assertEquals(BigDecimal.TEN, weatherData.getWindSpeed());
    }
}
