package study.bytebuddy.agent;

import java.lang.instrument.Instrumentation;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @author chao
 * @since 2018-11-20
 */
public class TimerAgent {

    public static void premain(String arguments, Instrumentation instrumentation) {
        new AgentBuilder.Default()
                .type(ElementMatchers.nameEndsWith("Timed")) // class type
                .transform((builder, type, classLoader, module) ->
                        builder.method(ElementMatchers.nameEndsWith("Timed")) // method name
                                .intercept(MethodDelegation.to(TimingInterceptor.class))
                ).installOn(instrumentation);
    }
}
