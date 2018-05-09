package com.waylau.spring.cloud.weather.service.impl;

import com.waylau.spring.cloud.weather.service.CityDataService;
import com.waylau.spring.cloud.weather.util.XmlBuilder;
import com.waylau.spring.cloud.weather.vo.City;
import com.waylau.spring.cloud.weather.vo.CityList;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author smmit
 * @date 2018-05-09
 */
@Service
public class CityDataServiceImpl implements CityDataService{
    @Override
    public List<City> listCity() throws Exception {
        //读取XML文件
        Resource resource = new ClassPathResource("citylist.xml");
        BufferedReader bf = new BufferedReader(new InputStreamReader(resource.getInputStream(), "UTF-8"));
        //StringBuffer是线程安全的，StringBuilder不是
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = bf.readLine()) != null) {
            buffer.append(line);
        }
        bf.close();

        //将XML转为JAVA对象
        CityList cityList =(CityList)XmlBuilder.xmlStrToObject(CityList.class, buffer.toString());
        return cityList.getCityList();
    }
}
