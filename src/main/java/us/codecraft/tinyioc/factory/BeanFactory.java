package us.codecraft.tinyioc.factory;

import us.codecraft.tinyioc.BeanDefiniation;

public interface BeanFactory {
    Object getBean(String name);

    void registerBeanDefinition(String name, BeanDefiniation beanDefiniation);
}
