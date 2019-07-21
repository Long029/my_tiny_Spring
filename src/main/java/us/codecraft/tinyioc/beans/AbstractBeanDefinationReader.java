package us.codecraft.tinyioc.beans;

import us.codecraft.tinyioc.beans.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * 从配置文件中读取BeanDefiniation
 */
public abstract class AbstractBeanDefinationReader implements BeanDefiniationReader {
    private Map<String, BeanDefination> register;
    private ResourceLoader resourceLoader;

    public AbstractBeanDefinationReader(ResourceLoader resourceLoader) {
        this.register = new HashMap<String, BeanDefination>();
        this.resourceLoader = resourceLoader;
    }

    public Map<String, BeanDefination> getRegister() {
        return register;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

}
