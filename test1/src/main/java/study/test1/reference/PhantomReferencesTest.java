package study.test1.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chao
 * @since 2018-12-06
 */
public class PhantomReferencesTest {

    static class Test {
        private int num;
        private String str;
        private PhantomReference reference;

        public void setReference(PhantomReference reference) {
            this.reference = reference;
        }

        public PhantomReference getReference() {
            return reference;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 常量不会被回收，使用new String("abc")可以
//        String abc = "abc";
//        String abc = new String("abc");
//        Test abc = new Test();
//        ReferenceQueue referenceQueue = new ReferenceQueue<>();
//        // 需要持有对象，不然会被回收
//        PhantomReference phantomReference = new PhantomReference<>(abc, referenceQueue);
//
//        Thread.sleep(1000);
////        abc = null;
//        System.gc();
//        Thread.sleep(1000);
//
//        int i = 0;
//        while (i < Integer.MAX_VALUE) {
//            Reference reference = referenceQueue.poll();
//            if (reference != null){
//                System.out.println(reference.toString());
//                return;
//            }
//            i++;
//            if (i == 100000) {
//                abc = null;
//                System.gc();
//                Thread.sleep(1000);
//                System.out.println("test = null");
//            }
//        }

//        // 使用list持有phantomReference对象
//        ReferenceQueue referenceQueue = new ReferenceQueue<>();
//        List<PhantomReference> list = new ArrayList<>();
//        new Thread(() -> {
//            Test t = new Test();
//            // 需要持有对象
//            PhantomReference phantomReference = new PhantomReference<>(t, referenceQueue);
//            list.add(phantomReference);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//
//        Thread.sleep(1500);
//        System.gc();
//
//        Thread.sleep(1000);
//
//        Reference reference = referenceQueue.poll();
//        if (reference != null) {
//            System.out.println(reference.toString());
//        }

        // 使用对象持有phantomReference对象
        ReferenceQueue referenceQueue = new ReferenceQueue<>();
        Test abc = new Test();
        new Thread(() -> {
            Test t = new Test();
            abc.setReference(new PhantomReference<>(t, referenceQueue));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(1500);
        System.gc();

        Thread.sleep(1000);

        Reference reference = referenceQueue.poll();
        if (reference != null) {
            System.out.println(reference.toString());
        }
    }
}
