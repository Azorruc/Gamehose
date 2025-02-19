package com.gamehouse.weather.domain.repository;


import com.gamehouse.weather.domain.entity.WeatherData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:weather_db",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class WeatherDataRepositoryTest {

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    @Test
    public void when_saved_data_can_be_find() {
        WeatherData weatherData = WeatherData.builder()
                .station("CBD")
                .localTime(LocalDateTime.of(2025, 2, 18, 15, 0, 0))
                .receptionTime(LocalDateTime.of(2025, 2, 18, 15, 2, 0))
                .temperature(BigDecimal.valueOf(25.00))
                .humidity(BigDecimal.valueOf(50.00))
                .windSpeed(BigDecimal.valueOf(10.00))
                .build();
        weatherDataRepository.save(weatherData);
        List<WeatherData> results = weatherDataRepository.findAll();
        assert(results.size() == 1);
        assertEquals("CBD", results.get(0).getStation());
        assertEquals(LocalDateTime.of(2025, 2, 18, 15, 0, 0), results.get(0).getLocalTime());
        assertEquals(LocalDateTime.of(2025, 2, 18, 15, 2, 0), results.get(0).getReceptionTime());
        assertEquals(BigDecimal.valueOf(25.00), results.get(0).getTemperature());
        assertEquals(BigDecimal.valueOf(50.00), results.get(0).getHumidity());
        assertEquals(BigDecimal.valueOf(10.00), results.get(0).getWindSpeed());
    }
}
