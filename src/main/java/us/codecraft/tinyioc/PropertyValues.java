package us.codecraft.tinyioc;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {

    private final List<PropertyValue> propertyValues = new ArrayList<PropertyValue>();

    public PropertyValues() {
    }

    public List<PropertyValue> getPropertyValues() {
        return propertyValues;
    }

    public void addPropertyValue(PropertyValue propertyValue){
        //TODO 据说是可以处理重复数据 所以不直接用List
        this.propertyValues.add(propertyValue);
    }
}
