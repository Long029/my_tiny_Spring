package us.codecraft.tinyioc.beans;

/**
 * 用于bean的属性注入
 */
public class PropertyValue {
    private String name;
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

}
