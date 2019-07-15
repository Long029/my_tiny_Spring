package us.codecraft.tinyioc;

public class BeanDefiniation {
    private Object bean;

    public BeanDefiniation(Object bean) {
        this.bean = bean;
    }
    public Object getBean(){
        return bean;
    }
}
