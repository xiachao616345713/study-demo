package study.bytebuddy.simple;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @author chao
 * @since 2018-11-19
 */
public class Test {

    public static void main(String[] args) throws Exception {
        Class<?> dynamicType = new ByteBuddy()
                .subclass(ByteBuddyInterface.class)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(Test.class.getClassLoader())
                .getLoaded();
        System.out.println(dynamicType.newInstance().toString());

        Class<? extends ByteBuddyInterface> dynamicType1 = new ByteBuddy()
                .subclass(ByteBuddyInterface.class)
                .method(ElementMatchers.named("callableTest"))
                .intercept(MethodCall.call(() -> {
                    int x = 1, y = 2;
                    return x + y;
                }))
                .method(ElementMatchers.named("add").and(ElementMatchers.takesArguments(int.class, int.class)).and(ElementMatchers.returns(int.class)))
                .intercept(MethodDelegation.to(new GreetingInterceptor()))
                .make()
                .load(Test.class.getClassLoader())
                .getLoaded();
        ByteBuddyInterface byteBuddyInterface = dynamicType1.newInstance();
        System.out.println(byteBuddyInterface.callableTest());
        System.out.println(byteBuddyInterface.add(5,6));

        Class<? extends java.util.function.Function> dynamicType2 = new ByteBuddy()
                .subclass(java.util.function.Function.class)
                .method(ElementMatchers.named("apply"))
                .intercept(MethodDelegation.to(new GreetingInterceptor()))
                .make()
                .load(Test.class.getClassLoader())
                .getLoaded();
        System.out.println(dynamicType2.newInstance().apply("test xxx"));
    }
}
