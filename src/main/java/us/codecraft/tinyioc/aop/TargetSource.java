package us.codecraft.tinyioc.aop;

/**
 * 被代理的对象
 */
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
