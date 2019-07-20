package us.codecraft.tinyioc.factory;

import us.codecraft.tinyioc.BeanDefination;
import us.codecraft.tinyioc.BeanReference;
import us.codecraft.tinyioc.PropertyValue;

import java.lang.reflect.Field;

public class AutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    protected Object doCreateBean(BeanDefination beanDefination) {
        Object bean = createBeanInstance(beanDefination);
        beanDefination.setBean(bean);
        setBeanFeild(beanDefination, bean);
        return bean;
    }

    private void setBeanFeild(BeanDefination beanDefination, Object bean) {
        try {
            for (PropertyValue propertyValue : beanDefination.getPropertyValues().getPropertyValues()) {
                Field field = bean.getClass().getDeclaredField(propertyValue.getName());
                field.setAccessible(true);
                Object value =  propertyValue.getValue();
                if(value instanceof BeanReference){
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getName());
                }
                field.set(bean,value);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    private Object createBeanInstance(BeanDefination beanDefination) {
        try {
            Object bean = beanDefination.getBeanClass().newInstance();
            return bean;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
