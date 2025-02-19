package com.gamehouse.weather.application.util;

public interface Mapper<T, V> {
	V map(T input);
}
