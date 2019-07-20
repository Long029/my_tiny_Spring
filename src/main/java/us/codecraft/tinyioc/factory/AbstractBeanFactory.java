package us.codecraft.tinyioc.factory;

import us.codecraft.tinyioc.BeanDefination;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractBeanFactory implements BeanFactory {

    private Map<String, BeanDefination> beanDefiniationMap = new ConcurrentHashMap<String, BeanDefination>();
    private List<String> beanDefinationNames = new ArrayList<String>();
    @Override
    public Object getBean(String name){
        BeanDefination beanDefination = beanDefiniationMap.get(name);
        if(beanDefination == null){
            throw new IllegalArgumentException("No bean named "+ name + "is defined");
        }
        Object bean = beanDefination.getBean();
        if(null == bean){
            bean = doCreateBean(beanDefination);
        }
        return bean;
    }

    public void registerBeanDefinition(String name, BeanDefination beanDefination){
        //此时的beanDefiniation中只有ClassName而已, 要通过ClassName创建一个它的实例
        beanDefiniationMap.put(name, beanDefination);
        beanDefinationNames.add(name);
    }

    public void preInstantiateSinglatons(){
        for (Iterator it = this.beanDefinationNames.iterator() ; it.hasNext();){
            String beanName = (String) it.next();
            getBean(beanName);
        }
    }
    protected abstract Object doCreateBean(BeanDefination beanDefination);
}
