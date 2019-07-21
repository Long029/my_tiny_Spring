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
    public void refresh() throws Exception{
        //location是一个文件路径, 需要把文件读取, 并解析到beanFactory中
        XmlBeanDefinationReader xmlBeanDefinationReader = new XmlBeanDefinationReader(new ResourceLoader());
        xmlBeanDefinationReader.readBeanDefinations(location);
        //为什么不在此处直接将xmlBeanDefinationReader中的Register 获取后直接放入abstractBeanFactory?
        //因为abstractBeanFactory并不支持setMap
        for (Map.Entry<String, BeanDefination> beanDefiniationEntry: xmlBeanDefinationReader.getRegister().entrySet()){
            beanFactory.registerBeanDefinition(beanDefiniationEntry.getKey(), beanDefiniationEntry.getValue());
        }

    }

    @Override
    public Object getBean(String name) {
        return beanFactory.getBean(name);
    }
}
