package us.codecraft.tinyioc.aop;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher {
    private PointcutParser pointcutParser;
    private String expression;
    private PointcutExpression pointcutExpression;
    private static final Set<PointcutPrimitive> DEAFULT_SUPPORTED_PRIMITIVES = new HashSet<PointcutPrimitive>();
    static{
        DEAFULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
        DEAFULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.ARGS);
        DEAFULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.REFERENCE);
        DEAFULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.THIS);
        DEAFULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.TARGET);
        DEAFULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.WITHIN);
        DEAFULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ANNOTATION);
        DEAFULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_WITHIN);
        DEAFULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ARGS);
        DEAFULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_TARGET);
    }

    public AspectJExpressionPointcut() {
        this(DEAFULT_SUPPORTED_PRIMITIVES);
    }

    public AspectJExpressionPointcut(Set<PointcutPrimitive> deafultSupportedPrimitives) {
        pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(deafultSupportedPrimitives);
    }

    protected void checkReadyToMatch(){
        if(pointcutExpression == null){
            pointcutExpression = buildPointcutExpression();
        }
    }

    private PointcutExpression buildPointcutExpression() {
        return pointcutParser.parsePointcutExpression(expression);
    }

    @Override
    public ClassFilter getClassFliter() {
        return this;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public boolean matches(Class clazz) {
        checkReadyToMatch();
        return pointcutExpression.couldMatchJoinPointsInType(clazz);
    }

    @Override
    public boolean matches(Method method, Class targetClass) {
        checkReadyToMatch();
        ShadowMatch shadowMatch =pointcutExpression.matchesMethodExecution(method);
        if (shadowMatch.alwaysMatches()){
            return true;
        }else if(shadowMatch.neverMatches()){
            return false;
        }
        //其他的不判断
        return false;
    }
}
