package us.codecraft.tinyioc.beans.factory;

import us.codecraft.tinyioc.beans.BeanDefination;
import us.codecraft.tinyioc.BeanReference;
import us.codecraft.tinyioc.beans.PropertyValue;

import java.lang.reflect.Field;

/**
 * 可自动装配的beanFactory
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    protected void setBeanFeild(BeanDefination beanDefination, Object bean) throws Exception {
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

}