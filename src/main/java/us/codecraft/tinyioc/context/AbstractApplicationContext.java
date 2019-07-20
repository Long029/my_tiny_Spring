package us.codecraft.tinyioc.context;

import us.codecraft.tinyioc.factory.AbstractBeanFactory;

public abstract class AbstractApplicationContext implements ApplicationContext{
    private AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public Object getBean(String name){
        return beanFactory.getBean(name);
    };

    public abstract void refresh() throws Exception;
}
