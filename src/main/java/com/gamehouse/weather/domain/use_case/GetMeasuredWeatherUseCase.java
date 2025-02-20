package com.gamehouse.weather.domain.use_case;

import com.gamehouse.weather.application.controller.response.MeasuredData;
import com.gamehouse.weather.application.controller.response.MeasuredWeatherResponse;
import com.gamehouse.weather.domain.entity.WeatherData;
import com.gamehouse.weather.domain.repository.WeatherDataRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Builder
@Data
@AllArgsConstructor
public class GetMeasuredWeatherUseCase {

    @Autowired
    private final WeatherDataRepository weatherDataRepository;

    public Optional<MeasuredWeatherResponse> execute(String stationId, LocalDateTime start, LocalDateTime end) {
        List<WeatherData> data = weatherDataRepository.findByStationAndReceptionTimeBetween(stationId, start, end);
        if(data.isEmpty()) {
            return Optional.empty();
        }
        else {
            BigDecimal totalTemperature = BigDecimal.ZERO;
            BigDecimal totalHumidity = BigDecimal.ZERO;
            BigDecimal totalWindSpeed = BigDecimal.ZERO;
            for(WeatherData weatherData : data) {
                totalTemperature = totalTemperature.add(weatherData.getTemperature());
                totalHumidity = totalHumidity.add(weatherData.getHumidity());
                totalWindSpeed = totalWindSpeed.add(weatherData.getWindSpeed());
            }
            BigDecimal avgTemperature = totalTemperature.divide(BigDecimal.valueOf(data.size()), 2, RoundingMode.HALF_UP);
            BigDecimal avgHumidity = totalHumidity.divide(BigDecimal.valueOf(data.size()), 2, RoundingMode.HALF_UP);
            BigDecimal avgWindSpeed = totalWindSpeed.divide(BigDecimal.valueOf(data.size()), 2, RoundingMode.HALF_UP);
            WeatherData maxTemp = data.stream()
                    .max(Comparator.comparing(WeatherData::getTemperature))
                    .get();
            WeatherData minTemp = data.stream()
                    .min(Comparator.comparing(WeatherData::getTemperature))
                    .get();
            WeatherData maxHum = data.stream()
                    .max(Comparator.comparing(WeatherData::getHumidity))
                    .get();
            WeatherData minHum = data.stream()
                    .min(Comparator.comparing(WeatherData::getHumidity))
                    .get();
            WeatherData maxWnd = data.stream()
                    .max(Comparator.comparing(WeatherData::getWindSpeed))
                    .get();
            WeatherData minWnd = data.stream()
                    .min(Comparator.comparing(WeatherData::getWindSpeed))
                    .get();
            MeasuredData temp = new MeasuredData(avgTemperature, maxTemp.getTemperature().setScale(2,RoundingMode.HALF_UP), minTemp.getTemperature().setScale(2,RoundingMode.HALF_UP) );
            MeasuredData hum = new MeasuredData(avgHumidity, maxHum.getHumidity().setScale(2, RoundingMode.HALF_UP), minHum.getHumidity().setScale(2, RoundingMode.HALF_UP) );
            MeasuredData wnd = new MeasuredData(avgWindSpeed, maxWnd.getWindSpeed().setScale(2, RoundingMode.HALF_UP), minWnd.getWindSpeed().setScale(2, RoundingMode.HALF_UP) );

            return Optional.of(new MeasuredWeatherResponse(temp,hum,wnd));
        }
    }
}
