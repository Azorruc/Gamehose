package com.gamehouse.weather.domain.repository;

import com.gamehouse.weather.domain.entity.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
}
