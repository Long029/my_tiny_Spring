package us.codecraft.tinyioc.factory;

import us.codecraft.tinyioc.BeanDefiniation;
import us.codecraft.tinyioc.PropertyValue;

import java.lang.reflect.Field;

public class AutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    protected Object doCreateBean(BeanDefiniation beanDefiniation) {
        Object bean = createBeanInstance(beanDefiniation);
        setBeanFeild(beanDefiniation, bean);
        return bean;
    }

    private void setBeanFeild(BeanDefiniation beanDefiniation, Object bean) {
        try {
            for (PropertyValue propertyValue : beanDefiniation.getPropertyValues().getPropertyValues()) {
                Field field = bean.getClass().getDeclaredField(propertyValue.getName());
                field.setAccessible(true);
                field.set(bean, propertyValue.getValue());
            }
            beanDefiniation.setBean(bean);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    private Object createBeanInstance(BeanDefiniation beanDefiniation) {
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
