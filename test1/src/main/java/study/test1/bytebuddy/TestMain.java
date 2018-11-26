package study.test1.bytebuddy;

/**
 * @author chao
 * @since 2018-11-22
 */
public class TestMain {

    public static void main(String[] args) throws Exception {
        TestClassTimed testClass = new TestClassTimed();
        testClass.helloWorld();
        System.out.println("====");
        testClass.helloWorldTimed();
    }
}
