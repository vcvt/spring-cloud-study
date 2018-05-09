package com.waylau.spring.cloud.weather.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waylau.spring.cloud.weather.service.WeatherDataService;
import com.waylau.spring.cloud.weather.vo.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author smmit
 * @date 2018-05-08
 */
@Service
public class WeatherDataServiceImpl implements WeatherDataService {
    private final static Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);
    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";
    private static final long TIMEOUT = 60L;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        return doGetWeather(uri);
    }

    @Override
    public WeatherResponse getDataByCityName(String cityName) {
        String uri = WEATHER_URI + "city=" + cityName;
        return doGetWeather(uri);
    }

    private WeatherResponse doGetWeather(String uri) {
        String key = uri;
        //json字符串转String
        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse weatherResponse = null;
        String strBody = null;
        ValueOperations<String,String> ops = stringRedisTemplate.opsForValue();
        //先查缓存，缓存有的取缓存数据
        if (stringRedisTemplate.hasKey(key)) {
            logger.info("找到key: " + key + ", value=" + ops.get(key));
            strBody = ops.get(key);
        } else {
            //没有再查第三方数据
            logger.info("未找到key: " + key);
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
            if (responseEntity.getStatusCodeValue() == 200) {
                strBody = responseEntity.getBody();
            }

            //数据写入缓存,timeout表示超时时间
            ops.set(key, strBody, TIMEOUT, TimeUnit.SECONDS);
        }

        try {
            weatherResponse = mapper.readValue(strBody, WeatherResponse.class);
        } catch (IOException e) {
            logger.error("JSON反序列化异常", e);
        }
        return weatherResponse;
    }

    @Override
    public void syncDataByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        this.saveWeatherData(uri);
    }

    /**
     * 把天气数据放到缓存中
     * @param uri
     */
    private void saveWeatherData(String uri) {
        String key = uri;
        String strBody = null;
        ValueOperations<String,String> ops = stringRedisTemplate.opsForValue();
        //调用服务接口来获取
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        if (responseEntity.getStatusCodeValue() == 200) {
            strBody = responseEntity.getBody();
        }
        //数据写入缓存,timeout表示超时时间
        ops.set(key, strBody, TIMEOUT, TimeUnit.SECONDS);
    }
}
