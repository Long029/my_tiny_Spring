package us.codecraft.tinyioc.io;

import org.junit.Assert;
import org.junit.Test;
import us.codecraft.tinyioc.BeanDefiniation;
import us.codecraft.tinyioc.HelloWorldService;
import us.codecraft.tinyioc.factory.AbstractBeanFactory;
import us.codecraft.tinyioc.factory.AutowireCapableBeanFactory;
import us.codecraft.tinyioc.xml.XmlBeanDefinationReader;

public class XmlBeanDefinationReaderTest {

    @Test
    public void test() throws Exception {
        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
        XmlBeanDefinationReader xmlBeanDefinationReader = new XmlBeanDefinationReader(new ResourceLoader());
        xmlBeanDefinationReader.readBeanDefinations("tinyioc.xml");
        BeanDefiniation beanDefiniation = xmlBeanDefinationReader.getRegister().get("helloWorldService");
        beanFactory.registerBeanDefinition("helloWorldService", beanDefiniation);
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        Assert.assertEquals("Hello world ! Hahahaha", helloWorldService.helloWorld());
    }
}