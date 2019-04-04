package study.test1.exception;

/**
 * @author chao
 * @since 2019-04-04
 */
public class Foo {
    private int tryBlock;
    private int catchBlock;
    private int finallyBlock;
    private int methodExit;

    public void test() {
        for (int i = 0; i < 100; i++) {
            try {
                tryBlock = 0;
                if (i < 50) {
                    continue;
                } else if (i < 80) {
                    break;
                } else {
                    return;
                }
            } catch (Exception e) {
                catchBlock = 1;
            } finally {
                finallyBlock = 2;
            }
        }
        methodExit = 3;
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        Foo foo = new Foo();
        foo.printStackTrace();
        System.out.println(System.currentTimeMillis());
    }

    private void printStackTrace() {
        Exception e = new Exception();
        e.printStackTrace();
    }
}

