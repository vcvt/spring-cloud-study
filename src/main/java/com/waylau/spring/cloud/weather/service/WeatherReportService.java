package com.waylau.spring.cloud.weather.service;

import com.waylau.spring.cloud.weather.vo.Weather;

/**
 * @author smmit
 * @date 2018-05-09
 */
public interface WeatherReportService {

    Weather getDataByCityId(String cityId);
}
