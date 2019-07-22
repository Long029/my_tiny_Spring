package us.codecraft.tinyioc.beans.factory;

import us.codecraft.tinyioc.beans.BeanDefination;
import us.codecraft.tinyioc.beans.BeanPostProcessor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractBeanFactory implements BeanFactory {

    private Map<String, BeanDefination> beanDefiniationMap = new ConcurrentHashMap<String, BeanDefination>();
    private List<String> beanDefinationNames = new ArrayList<String>();
    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();
    @Override
    public Object getBean(String name) throws Exception {
        BeanDefination beanDefination = beanDefiniationMap.get(name);
        if(beanDefination == null){
            throw new IllegalArgumentException("No bean named "+ name + "is defined");
        }
        Object bean = beanDefination.getBean();
        if(null == bean){
            bean = doCreateBean(beanDefination);
            initializeBean(bean, name);
        }
        return bean;
    }

    private void initializeBean(Object bean, String name) throws Exception {
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors){
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, name);
        }
        // TODO:call initialize method
        for(BeanPostProcessor beanPostProcessor : beanPostProcessors){
            bean = beanPostProcessor.postProcessAfterInitialization(bean,name);
        }
    }

    public void registerBeanDefinition(String name, BeanDefination beanDefination){
        //此时的beanDefiniation中只有ClassName而已, 要通过ClassName创建一个它的实例
        beanDefiniationMap.put(name, beanDefination);
        beanDefinationNames.add(name);
    }

    public void preInstantiateSinglatons() throws Exception {
        for (Iterator it = this.beanDefinationNames.iterator() ; it.hasNext();){
            String beanName = (String) it.next();
            getBean(beanName);
        }
    }

    protected Object doCreateBean(BeanDefination beanDefination) throws Exception{
        Object bean = createBeanInstance(beanDefination);
        beanDefination.setBean(bean);
        setBeanFeild(beanDefination, bean);
        return bean;
    }

    protected void setBeanFeild(BeanDefination beanDefination, Object bean) throws Exception {};

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

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List getBeansForType(Class type) throws Exception {
        List beans = new ArrayList<Object>();
        for(String beanDefinationName : beanDefinationNames){
            if(type.isAssignableFrom(beanDefiniationMap.get(beanDefinationName).getBeanClass())){
                beans.add(getBean(beanDefinationName));
            }
        }
        return beans;
    }
}
