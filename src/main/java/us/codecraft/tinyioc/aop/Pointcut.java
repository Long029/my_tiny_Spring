package us.codecraft.tinyioc.aop;

public interface Pointcut {
    ClassFilter getClassFliter();
    MethodMatcher getMethodMatcher();
}
