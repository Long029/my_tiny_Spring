package us.codecraft.tinyioc.aop;

import org.junit.Test;
import us.codecraft.tinyioc.HelloWorldService;
import us.codecraft.tinyioc.context.ApplicationContext;
import us.codecraft.tinyioc.context.ClassPathXmlApplication;

public class JdkDynamicAopProxyTest {
    @Test
    public void test(){
        ApplicationContext applicationContext = new ClassPathXmlApplication("tinyioc.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");

        //设置被代理代理对象
        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(helloWorldService, HelloWorldService.class);
        advisedSupport.setTargetSource(targetSource);

        //设置拦截器
        TimerInterceptor timerInterceptor = new TimerInterceptor();
        advisedSupport.setMethodInterceptor(timerInterceptor);
        //创建代理
        JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
        HelloWorldService helloWorldServiceProxy = (HelloWorldService) jdkDynamicAopProxy.getProxy();

        //基于AOP的调用
        helloWorldServiceProxy.helloPrint();
    }

}