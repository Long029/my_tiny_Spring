package us.codecraft.tinyioc.aop;

import org.aopalliance.aop.Advice;

public class AspectJExpressionPointcutAdvisor implements PrintcutAdvisor {
    private Advice advice;
    private AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setExpression(String expression){
        pointcut.setExpression(expression);
    };
    @Override
    public Pointcut getPrintcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }
}
