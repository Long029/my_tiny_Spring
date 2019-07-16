package us.codecraft.tinyioc;


import org.junit.Assert;
import org.junit.Test;
import us.codecraft.tinyioc.factory.AutowireCapableBeanFactory;
import us.codecraft.tinyioc.factory.BeanFactory;

/**
 * author long029
 */
public class BeanFactoryTest {
    @Test
    public void test() {
        BeanFactory beanFactory = new AutowireCapableBeanFactory();

        BeanDefiniation beanDefiniation = new BeanDefiniation();
        beanDefiniation.setBeanClassName("us.codecraft.tinyioc.HelloWorldService");
        beanFactory.registerBeanDefinition("HelloWorldService", beanDefiniation);

        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("HelloWorldService");
        Assert.assertEquals("Hello World !", helloWorldService.helloWorld());

        BeanDefiniation anotherBeanDefiniation = new BeanDefiniation();
        anotherBeanDefiniation.setBeanClassName("us.codecraft.tinyioc.EndClassService");
        beanFactory.registerBeanDefinition("EndClassService", anotherBeanDefiniation);

        EndClassService endClassService = (EndClassService) beanFactory.getBean("EndClassService");
        Assert.assertEquals("We can go home now..", endClassService.goHome());
    }
}