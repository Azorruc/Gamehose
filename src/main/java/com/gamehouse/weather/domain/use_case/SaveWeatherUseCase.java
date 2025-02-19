package com.gamehouse.weather.domain.use_case;

import com.gamehouse.weather.domain.entity.WeatherData;
import com.gamehouse.weather.domain.repository.WeatherDataRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Builder
@Data
@AllArgsConstructor
public class SaveWeatherUseCase {

    @Autowired
    private final WeatherDataRepository weatherDataRepository;

    public WeatherData execute(WeatherData weatherData) {
        return weatherDataRepository.save(weatherData);
    }
}
