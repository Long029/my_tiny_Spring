package us.codecraft.tinyioc.beans.factory;

import us.codecraft.tinyioc.BeanReference;
import us.codecraft.tinyioc.aop.BeanFactoryAware;
import us.codecraft.tinyioc.beans.BeanDefination;
import us.codecraft.tinyioc.beans.PropertyValue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 可自动装配的beanFactory
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    protected void setBeanFeild(BeanDefination beanDefination, Object bean) throws Exception {
        if(bean instanceof BeanFactoryAware){
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }
        try {
            for (PropertyValue propertyValue : beanDefination.getPropertyValues().getPropertyValues()) {
//                Field field = bean.getClass().getDeclaredField(propertyValue.getName());
//                field.setAccessible(true);
                Object value =  propertyValue.getValue();
                if(value instanceof BeanReference){
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getName());
                }
//                field.set(bean,value);
                try{
                    Method method = bean.getClass().getDeclaredMethod("set"+ propertyValue.getName().substring(0,1).toUpperCase()
                    +propertyValue.getName().substring(1), value.getClass());
                    method.setAccessible(true);
                    method.invoke(bean, value);
                } catch (NoSuchMethodException e) {
                    Field field = bean.getClass().getDeclaredField(propertyValue.getName());
                    field.setAccessible(true);
                    field.set(bean, value);
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}
