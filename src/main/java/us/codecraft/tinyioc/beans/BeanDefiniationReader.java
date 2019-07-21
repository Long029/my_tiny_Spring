package us.codecraft.tinyioc.beans;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * 从配置中读取BeanDefination的数据
 */
public interface BeanDefiniationReader {

 void readBeanDefinations(String location) throws IOException, ParserConfigurationException, Exception;
}
