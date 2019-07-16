package us.codecraft.tinyioc;

public class BeanDefiniation {
    private Object bean;
    private Class beanClass;
    private String beanClassName;
    private PropertyValues propertyValues;

    public BeanDefiniation() {
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
        try {
        this.beanClass = Class.forName(beanClassName);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getStackTrace());
        }
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
