package us.codecraft.tinyioc.context;

import us.codecraft.tinyioc.beans.BeanDefination;
import us.codecraft.tinyioc.beans.factory.AbstractBeanFactory;
import us.codecraft.tinyioc.beans.factory.AutowireCapableBeanFactory;
import us.codecraft.tinyioc.beans.io.ResourceLoader;
import us.codecraft.tinyioc.beans.xml.XmlBeanDefinationReader;

import java.util.Map;

public class ClassPathXmlApplication extends AbstractApplicationContext {

    private String location;

    public ClassPathXmlApplication(String location) {
        this(location,  new AutowireCapableBeanFactory());
    }

    public ClassPathXmlApplication(String location, AbstractBeanFactory abstractBeanFactory) {
        super(abstractBeanFactory);
        this.location = location;
        try {
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void loadBeanDefinition(AbstractBeanFactory beanFactory) throws Exception {
        XmlBeanDefinationReader xmlBeanDefinationReader = new XmlBeanDefinationReader(new ResourceLoader());
        xmlBeanDefinationReader.readBeanDefinations(location);
        //为什么不在此处直接将xmlBeanDefinationReader中的Register 获取后直接放入abstractBeanFactory?
        //因为abstractBeanFactory并不支持setMap
        for (Map.Entry<String, BeanDefination> beanDefinationEntry : xmlBeanDefinationReader.getRegister().entrySet()){
            beanFactory.registerBeanDefinition(beanDefinationEntry.getKey(), beanDefinationEntry.getValue());
        }
    }

    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }
}
