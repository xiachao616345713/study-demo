package study.test1.sharedsecretss;

import sun.misc.SharedSecrets;
import sun.reflect.ConstantPool;

/**
 * SharedSecretsTest
 *
 * @author xiachao
 * @date 2023/6/23
 */
public class SharedSecretsTest {

    public static void main(String[] args) {
        ConstantPool constantPool = SharedSecrets.getJavaLangAccess().getConstantPool(String.class);
        System.out.println(constantPool);
    }

}
