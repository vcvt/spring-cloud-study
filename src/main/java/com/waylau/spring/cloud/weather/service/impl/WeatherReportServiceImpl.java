package com.waylau.spring.cloud.weather.service.impl;

import com.waylau.spring.cloud.weather.service.WeatherDataService;
import com.waylau.spring.cloud.weather.service.WeatherReportService;
import com.waylau.spring.cloud.weather.vo.Weather;
import com.waylau.spring.cloud.weather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author smmit
 * @date 2018-05-09
 */
@Service
public class WeatherReportServiceImpl implements WeatherReportService {
    @Autowired
    private WeatherDataService weatherDataService;
    @Override
    public Weather getDataByCityId(String cityId) {
        WeatherResponse weatherResponse = weatherDataService.getDataByCityId(cityId);
        return weatherResponse.getData();
    }
}
