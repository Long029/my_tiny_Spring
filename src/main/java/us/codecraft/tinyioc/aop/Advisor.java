package us.codecraft.tinyioc.aop;

import org.aopalliance.aop.Advice;

/**
 * 切面
 */
public interface Advisor {
    //切点  包含横切代码和连接点信息
    //本身就是一个简单的切面, 但是因为连接点是类的所有方法, 切面太大, 所以用Advisor辅助
    Advice getAdvice();
}
