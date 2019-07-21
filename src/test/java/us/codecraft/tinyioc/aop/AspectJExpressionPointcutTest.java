package us.codecraft.tinyioc.aop;

import org.junit.Assert;
import org.junit.Test;
import us.codecraft.tinyioc.HelloWorldService;
import us.codecraft.tinyioc.HelloWorldServiceImpl;

import static org.junit.Assert.*;

public class AspectJExpressionPointcutTest {
    @Test
    public void test(){
        String expression = "execution(* us.codecraft.tinyioc.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matech = aspectJExpressionPointcut.getClassFliter().matches(HelloWorldService.class);
        Assert.assertTrue(matech);
    }
    @Test
    public void testMethodInterceptor() throws Exception {
        String expression = "execution(* us.codecraft.tinyioc.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getMethodMatcher().matches(HelloWorldServiceImpl.class.getDeclaredMethod("helloPrint"),HelloWorldServiceImpl.class);
        Assert.assertTrue(matches);
    }
}