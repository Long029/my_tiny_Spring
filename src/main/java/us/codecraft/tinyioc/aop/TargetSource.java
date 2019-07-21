package us.codecraft.tinyioc.aop;

public class TargetSource {
    private Class targetClass;
    private Object target;

    public TargetSource(Object target, Class<?> targetSource) {
        this.targetClass = targetSource;
        this.target = target;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public Object getTarget() {
        return target;
    }
}
