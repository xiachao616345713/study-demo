package study.bytebuddy.agent;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

/**
 * @author chao
 * @since 2018-11-20
 */
public class TimingInterceptor {

    @RuntimeType
    public static Object intercept(@Origin Method method,
            @SuperCall Callable<?> callable) throws Exception {
        long start = System.currentTimeMillis();
        try {
            System.out.println("intercept");
            return callable.call();
        } finally {
            System.out.println(method + " took " + (System.currentTimeMillis() - start));
        }
    }
}
