package com.waylau.spring.cloud.weather.vo;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author smmit
 * @date 2018-05-09
 */
@XmlRootElement(name = "c")
@XmlAccessorType(XmlAccessType.FIELD)
public class CityList {
    //Element和Attribute还是有差异的
    @XmlElement(name = "d")
    private List<City> cityList;

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
}
