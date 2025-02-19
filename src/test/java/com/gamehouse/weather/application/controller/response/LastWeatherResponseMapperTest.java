package com.gamehouse.weather.application.controller.response;

import com.gamehouse.weather.domain.entity.WeatherData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class LastWeatherResponseMapperTest {

    @InjectMocks
    private LastWeatherResponseMapper lastWeatherResponseMapper;

    @Test
    public void when_mapper_is_used_then_data_is_mapped() {
        WeatherData data = WeatherData.builder()
                .station("ABC")
                .localTime(LocalDateTime.of(2025, 2, 18, 10, 20, 30))
                .receptionTime(LocalDateTime.of(2025, 2, 18, 10, 21, 30))
                .temperature(BigDecimal.valueOf(15.5))
                .humidity(BigDecimal.valueOf(32.5))
                .windSpeed(BigDecimal.valueOf(45.25))
                .build();

        LastWeatherResponse response = lastWeatherResponseMapper.map(data);

        assertEquals("ABC", response.getStation());
        assertEquals(LocalDateTime.of(2025, 2, 18, 10, 20, 30), response.getMeasuredTime());
        assertEquals(LocalDateTime.of(2025, 2, 18, 10, 21, 30), response.getSavedTime());
        assertEquals(BigDecimal.valueOf(15.5), response.getTemperature());
        assertEquals(BigDecimal.valueOf(32.5), response.getHumidity());
        assertEquals(BigDecimal.valueOf(45.25), response.getWindSpeed());
    }
}
