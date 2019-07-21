package us.codecraft.tinyioc.context;

import org.junit.Test;
import us.codecraft.tinyioc.HelloWorldService;

import static org.junit.Assert.*;

public class ApplicationContextTest {
    @Test
    public void test(){
        ApplicationContext applicationContext = new ClassPathXmlApplication("tinyioc.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
        System.out.println(helloWorldService.helloWorld());
    }

}