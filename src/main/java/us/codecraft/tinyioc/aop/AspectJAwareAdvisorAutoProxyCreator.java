package us.codecraft.tinyioc.aop;

import org.aopalliance.intercept.MethodInterceptor;
import us.codecraft.tinyioc.beans.BeanPostProcessor;
import us.codecraft.tinyioc.beans.factory.AbstractBeanFactory;
import us.codecraft.tinyioc.beans.factory.BeanFactory;

import java.util.List;

public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {

    private AbstractBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws Exception {
        this.beanFactory = (AbstractBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        if (bean instanceof AspectJExpressionPointcutAdvisor) {
            return bean;
        }
        if (bean instanceof MethodInterceptor) {
            return bean;
        }
        List<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansForType(AspectJExpressionPointcutAdvisor.class);
        AdvisedSupport support;
        TargetSource targetSource;
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            if (advisor.getPrintcut().getClassFliter().matches(bean.getClass())) {
                support = new AdvisedSupport();
                support.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
                support.setMethodMatcher(advisor.getPrintcut().getMethodMatcher());

                targetSource = new TargetSource(bean, bean.getClass().getInterfaces());
                support.setTargetSource(targetSource);

                return new JdkDynamicAopProxy(support).getProxy();
            }
        }
        return bean;
    }
}
