package us.codecraft.tinyioc;


import org.junit.Test;
import us.codecraft.tinyioc.factory.AbstractBeanFactory;
import us.codecraft.tinyioc.factory.AutowireCapableBeanFactory;

/**
 * author long029
 */
public class BeanFactoryTest {
    @Test
    public void test() {

        BeanDefination beanDefination = new BeanDefination();
        beanDefination.setBeanClassName("us.codecraft.tinyioc.HelloWorldService");
        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();

        PropertyValue propertyValue = new PropertyValue("text", "Hello World !");
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(propertyValue);

        beanDefination.setPropertyValues(propertyValues);

        beanFactory.registerBeanDefinition("HelloWorldService", beanDefination);

        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("HelloWorldService");

    }
}