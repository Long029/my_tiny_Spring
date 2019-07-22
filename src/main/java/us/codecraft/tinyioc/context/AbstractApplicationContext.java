package us.codecraft.tinyioc.context;

import us.codecraft.tinyioc.beans.BeanPostProcessor;
import us.codecraft.tinyioc.beans.factory.AbstractBeanFactory;

import java.util.List;

public abstract class AbstractApplicationContext implements ApplicationContext{
    protected AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }

    public void refresh() throws Exception{
        loadBeanDefinition(beanFactory);
        registerBeanPostprocessors(beanFactory);
        onRefresh();
    }

    private void onRefresh() throws Exception {
        beanFactory.preInstantiateSinglatons();
    }

    private void registerBeanPostprocessors(AbstractBeanFactory beanFactory) throws Exception {
        List beanPostprocessors =beanFactory.getBeansForType(BeanPostProcessor.class);
        for(Object beanPostProcessor : beanPostprocessors){
            beanFactory.addBeanPostProcessor((BeanPostProcessor) beanPostProcessor);
        }
    }

    protected abstract void loadBeanDefinition(AbstractBeanFactory beanFactory) throws Exception;
}
