package study.test1.bytebuddy;

/**
 * @author chao
 * @since 2018-11-22
 */
public class TestClassTimed {

    public void helloWorld() {
        System.out.println("hello world !");
    }

    public void helloWorldTimed() throws InterruptedException {
        Thread.sleep(500);
        System.out.println("hello world timed !");
    }
}
