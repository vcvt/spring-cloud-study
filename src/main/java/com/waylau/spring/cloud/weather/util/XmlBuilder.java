package com.waylau.spring.cloud.weather.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.StringReader;

/**
 * @author smmit
 * @date 2018-05-09
 */
public class XmlBuilder {
    public static Object xmlStrToObject(Class<?> clazz, String xmlStr) throws Exception{
        Object xmlObject;
        Reader reader;
        JAXBContext context = JAXBContext.newInstance(clazz);
        //将XML转为对象的接口
        Unmarshaller unmarshaller = context.createUnmarshaller();
        reader = new StringReader(xmlStr);
        xmlObject = unmarshaller.unmarshal(reader);
        reader.close();
        return xmlObject;
    }
}
