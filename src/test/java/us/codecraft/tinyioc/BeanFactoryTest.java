package us.codecraft.tinyioc;


import org.junit.Assert;
import org.junit.Test;

/**
 * author long029
 */
public class BeanFactoryTest {
    @Test
    public void test(){
        BeanFactory beanFactory = new BeanFactory();

        BeanDefiniation beanDefiniation = new BeanDefiniation(new HelloWorldService());
        beanFactory.registerBeanDefinition("HelloWorldService", beanDefiniation);

        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("HelloWorldService");
        helloWorldService.print();

        BeanDefiniation goHome = new BeanDefiniation(new EndClassService());
        beanFactory.registerBeanDefinition("EndClassService", goHome);

        EndClassService endClassService = (EndClassService) beanFactory.getBean("EndClassService");
        Assert.assertEquals("We can go home now..", endClassService.goHome());
    }
}