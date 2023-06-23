//package study.test1.xml;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//import org.dom4j.Attribute;
//import org.dom4j.Document;
//import org.dom4j.Element;
//import org.dom4j.io.SAXReader;
//
///**
// * xml
// *
// * @author xiachao
// * @date 2021/01/26
// */
//public class XmlUtils {
//
//    private static List<String> ret = new ArrayList<>();
//
//    public static void main(String[] args) throws Exception {
//        String path = "/xxxx.xml";
//        // 创建一个XML解析器对象
//        SAXReader reader = new SAXReader();
//        // 读取XML文档，返回Document对象
//        Document document = reader.read(new File(path));
//
//        // 获取根元素节点
//        Element root = document.getRootElement();
//        StringBuilder sb = new StringBuilder();
//        recursion(root, sb);
//        //System.out.println(sb.toString());
//
//        System.out.println(ret.toString());
//    }
//
//    private static void recursion(Element ele, StringBuilder sb) {
//        boolean needAttr = false;
//        // 解析属性节点
//        List<Attribute> attributes = ele.attributes();
//        for(Attribute attribute : attributes) {
//            if ("SPFLBM".equals(attribute.getName()) && attribute.getValue() != null && attribute.getValue().length() > 0) {
//                needAttr = true;
//                break;
//            }
//        }
//
//        if (needAttr) {
//            String tmp = "";
//            for(Attribute attribute : attributes) {
//                if ("MC".equals(attribute.getName())) {
//                    tmp = tmp + attribute.getValue();
//                }
//                if ("SPFLBM".equals(attribute.getName())) {
//                    tmp = tmp +  "-" + attribute.getValue();
//                }
//            }
//            System.out.println(tmp);
//        }
////        sb.append("<");
////        // 解析元素节点
////        sb.append(ele.getName());
////
////        for(Attribute attribute : attributes) {
////            sb.append(" ");
////            sb.append(attribute.getName());
////            sb.append("=");
////            sb.append(attribute.getValue());
////        }
////
////        sb.append(">");
////
////        // 解析文本节点
////        sb.append(ele.getText());
//
//        // 递归解析元素节点
//        List<Element> elements = ele.elements();
//        for(Element element : elements) {
//            recursion(element, sb);
//        }
//
//        sb.append("<" + ele.getName() + "/>\n");
//    }
//
//}
