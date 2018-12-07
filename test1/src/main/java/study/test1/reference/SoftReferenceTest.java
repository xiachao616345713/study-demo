package study.test1.reference;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.ref.SoftReference;
import java.util.LinkedList;
import java.util.List;

/**
 * @author chao
 * @since 2018-12-04
 */
public class SoftReferenceTest {

    public static void main(String[] args) throws Exception {
        //String path = "D:\\Temp\\tp-client.log";
        String path = "E:\\download\\CentOS-7-x86_64-DVD-1708.iso";
        FileInputStream fileInputStream = new FileInputStream(path);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
//        StringBuilder sb = new StringBuilder();
//        String temp;
//        while ((temp = bufferedReader.readLine()) != null) {
//            sb.append(temp).append('\n');
//        }
//        System.out.println(sb.toString());

        SoftReference<List<String>> reference = new SoftReference<>(new LinkedList<>());
        String temp;
        while ((temp = bufferedReader.readLine()) != null) {
            List<String> tempList = reference.get();
            if (tempList != null) {
                tempList.add(temp);
            } else {
                throw new Exception("文件太大");
            }
            //tempList = null;
        }
        List<String> tempList = reference.get();
        if (tempList != null) {
            tempList.forEach(System.out::println);
        } else {
            System.out.println("null");
        }
    }
}
