package us.codecraft.tinyioc.beans.xml;

import org.junit.Assert;
import org.junit.Test;
import us.codecraft.tinyioc.beans.BeanDefination;
import us.codecraft.tinyioc.HelloWorldService;
import us.codecraft.tinyioc.beans.factory.AbstractBeanFactory;
import us.codecraft.tinyioc.beans.factory.AutowireCapableBeanFactory;
import us.codecraft.tinyioc.beans.io.ResourceLoader;

import java.util.Map;

public class XmlBeanDefinationReaderTest {

    @Test
    public void test() throws Exception {

        XmlBeanDefinationReader xmlBeanDefinationReader = new XmlBeanDefinationReader(new ResourceLoader());
        xmlBeanDefinationReader.readBeanDefinations("tinyioc-postbeanprocessor.xml");

        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
        for (Map.Entry<String, BeanDefination> beanDefinitionEntry : xmlBeanDefinationReader.getRegister().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }

        beanFactory.preInstantiateSinglatons();

        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        Assert.assertEquals("Hello world ! Hahahaha", helloWorldService.helloWorld());
    }
}