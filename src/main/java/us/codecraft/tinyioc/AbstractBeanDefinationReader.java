package us.codecraft.tinyioc;

import us.codecraft.tinyioc.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBeanDefinationReader implements BeanDefiniationReader {
    private Map<String, BeanDefiniation> register;
    private ResourceLoader resourceLoader;

    public AbstractBeanDefinationReader(ResourceLoader resourceLoader) {
        this.register = new HashMap<String, BeanDefiniation>();
        this.resourceLoader = resourceLoader;
    }

    public Map<String, BeanDefiniation> getRegister() {
        return register;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

}
