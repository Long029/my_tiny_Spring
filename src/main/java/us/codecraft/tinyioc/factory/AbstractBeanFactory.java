package us.codecraft.tinyioc.factory;

import us.codecraft.tinyioc.BeanDefiniation;
import us.codecraft.tinyioc.PropertyValue;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractBeanFactory implements BeanFactory {

    private Map<String, BeanDefiniation> beanDefiniationMap = new ConcurrentHashMap<String, BeanDefiniation>();

    @Override
    public Object getBean(String name){
        return beanDefiniationMap.get(name).getBean();
    }
    @Override
    public void registerBeanDefinition(String name, BeanDefiniation beanDefiniation){
        //此时的beanDefiniation中只有ClassName而已, 要通过ClassName创建一个它的实例
        Object bean = doCreateBean(beanDefiniation);
//        beanDefiniation.setBean(bean);
        setBeanField(beanDefiniation, bean);
        beanDefiniationMap.put(name,beanDefiniation);
    }

    private void setBeanField(BeanDefiniation beanDefiniation, Object bean) {
        for (PropertyValue propertyValue : beanDefiniation.getPropertyValues().getPropertyValues()){
            try {
                Field field = bean.getClass().getDeclaredField(propertyValue.getName());
                field.setAccessible(true);
                field.set(bean, propertyValue.getValue());
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        beanDefiniation.setBean(bean);
    }

    protected abstract Object doCreateBean(BeanDefiniation beanDefiniation);
}
