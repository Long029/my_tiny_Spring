package us.codecraft.tinyioc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory {
    private Map<String,BeanDefiniation> beanDefiniationMap = new ConcurrentHashMap<String, BeanDefiniation>();

    public Object getBean(String name){
        return beanDefiniationMap.get(name).getBean();
    }
    public void registerBeanDefinition(String name, BeanDefiniation beanDefiniation){
        beanDefiniationMap.put(name,beanDefiniation);
    }
}
