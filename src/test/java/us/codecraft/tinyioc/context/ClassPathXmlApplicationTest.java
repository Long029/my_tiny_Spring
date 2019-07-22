package us.codecraft.tinyioc.context;

import org.junit.Assert;
import org.junit.Test;
import us.codecraft.tinyioc.HelloWorldService;

public class ClassPathXmlApplicationTest {
    @Test
    public void test() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplication("tinyioc-postbeanprocessor.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
        Assert.assertEquals("Hello world ! Hahahaha", helloWorldService.helloWorld());
    }

}