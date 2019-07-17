package us.codecraft.tinyioc;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface BeanDefiniationReader {

 void readBeanDefinations(String location) throws IOException, ParserConfigurationException, Exception;
}
