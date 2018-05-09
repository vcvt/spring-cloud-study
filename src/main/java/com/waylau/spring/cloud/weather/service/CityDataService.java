package com.waylau.spring.cloud.weather.service;

import com.waylau.spring.cloud.weather.vo.City;

import java.util.List;

/**
 * @author smmit
 * @date 2018-05-09
 */
public interface CityDataService {
    List<City> listCity() throws Exception;

}
