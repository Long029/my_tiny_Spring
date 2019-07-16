package us.codecraft.tinyioc;

public interface BeanFactory {
    Object getBean(String name);

    void registerBeanDefinition(String name, BeanDefiniation beanDefiniation);
}
