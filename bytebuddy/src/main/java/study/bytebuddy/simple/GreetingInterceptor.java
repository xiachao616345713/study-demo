package study.bytebuddy.simple;

/**
 * @author chao
 * @since 2018-11-20
 */
public class GreetingInterceptor {

    public Object greet(Object argument) {
        return "greet:Hello from " + argument;
    }

    public int add(int argument, int argument2) {
        return argument + argument2;
    }


}
