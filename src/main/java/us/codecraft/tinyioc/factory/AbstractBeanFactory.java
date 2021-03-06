package us.codecraft.tinyioc.factory;

import us.codecraft.tinyioc.BeanDefiniation;

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
        beanDefiniationMap.put(name,beanDefiniation);
    }

    protected abstract Object doCreateBean(BeanDefiniation beanDefiniation);
}
