package us.codecraft.tinyioc.factory;

import us.codecraft.tinyioc.BeanDefiniation;

public class AutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    protected Object doCreateBean(BeanDefiniation beanDefiniation) {
        try {
            Object bean = beanDefiniation.getBeanClass().newInstance();
            return bean;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
