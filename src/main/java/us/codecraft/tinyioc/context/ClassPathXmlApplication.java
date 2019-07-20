package us.codecraft.tinyioc.context;

import us.codecraft.tinyioc.BeanDefination;
import us.codecraft.tinyioc.factory.AbstractBeanFactory;
import us.codecraft.tinyioc.factory.AutowireCapableBeanFactory;
import us.codecraft.tinyioc.io.ResourceLoader;
import us.codecraft.tinyioc.xml.XmlBeanDefinationReader;

import java.util.Map;

public class ClassPathXmlApplication extends AbstractApplicationContext {

    private AbstractBeanFactory abstractBeanFactory;
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
            abstractBeanFactory.registerBeanDefinition(beanDefiniationEntry.getKey(), beanDefiniationEntry.getValue());
        }

    }

    @Override
    public Object getBean(String name) {
        return abstractBeanFactory.getBean(name);
    }
}
