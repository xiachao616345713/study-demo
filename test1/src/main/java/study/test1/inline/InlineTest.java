package study.test1.inline;

/**
 * @author xiachao
 * @date 2020/04/29
 */
public class InlineTest {

    //-XX:+PrintCompilation // 在控制台打印编译过程信息
    //-XX:+UnlockDiagnosticVMOptions // 解锁对 JVM 进行诊断的选项参数。默认是关闭的，开启后支持一些特定参数对 JVM 进行诊断
    //-XX:+PrintInlining // 将内联方法打印出来

    //-XX:+DoEscapeAnalysis 开启逃逸分析（jdk1.8 默认开启，其它版本未测试）
    //-XX:-DoEscapeAnalysis 关闭逃逸分析
    //
    //-XX:+EliminateLocks 开启锁消除（jdk1.8 默认开启，其它版本未测试）
    //-XX:-EliminateLocks 关闭锁消除
    //
    //-XX:+EliminateAllocations 开启标量替换（jdk1.8 默认开启，其它版本未测试）
    //-XX:-EliminateAllocations 关闭就可以了

    public static void main(String[] args) {
        // 方法调用计数器的默认阈值在 C1 模式下是 1500 次，在 C2 模式在是 10000 次，我们循环遍历超过需要阈值
        for (int i = 0; i < 1000000; i++) {
            add1(1, 2, 3, 4);
        }
    }

    private static int add1(int a, int b, int c, int d) {
        return a + b + c + d;
    }
}
