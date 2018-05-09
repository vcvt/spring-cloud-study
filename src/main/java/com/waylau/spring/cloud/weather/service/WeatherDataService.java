package com.waylau.spring.cloud.weather.service;

import com.waylau.spring.cloud.weather.vo.WeatherResponse;

/**
 * @author smmit
 * @date 2018-05-08
 */
public interface WeatherDataService {
    /**
     * 根据城市ID获取数据
     */
    WeatherResponse getDataByCityId(String cityId);

    /**
     * 根据城市名称获取数据
     */
    WeatherResponse getDataByCityName(String cityName);

    /**
     * 根据城市ID来同步天气
     */
    void syncDataByCityId(String cityId);
}
