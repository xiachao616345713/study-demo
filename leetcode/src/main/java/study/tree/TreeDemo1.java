package study.tree;

/**
 * 按层输出
 *
 * @author chao
 * @since 2019-04-02
 */
public class TreeDemo1 {

    public static void main(String[] args) {
        TreeStruct treeStruct = new TreeStruct();
//        treeStruct.setValue(1);
//        treeStruct.setLeft(new TreeStruct(2));
//        treeStruct.setRight(new TreeStruct(3));
//        treeStruct.getLeft().setLeft(new TreeStruct(4));
//        treeStruct.getLeft().setRight(new TreeStruct(5));
//        treeStruct.getRight().setLeft(new TreeStruct(6));
//        treeStruct.getRight().setRight(new TreeStruct(7));
//        treeStruct.getRight().getLeft().setLeft(new TreeStruct(11));
//        treeStruct.getRight().getLeft().setRight(new TreeStruct(12));
//
//        treeStruct.printByLayer();

        treeStruct.insertNode(33);
        treeStruct.insertNode(16);
        treeStruct.insertNode(13);
        treeStruct.insertNode(15);
        treeStruct.insertNode(18);
        treeStruct.insertNode(25);
        treeStruct.insertNode(27);
        treeStruct.insertNode(19);
        treeStruct.insertNode(50);
        treeStruct.insertNode(58);
        treeStruct.insertNode(34);
        treeStruct.insertNode(51);
        treeStruct.insertNode(66);
        treeStruct.insertNode(55);
        treeStruct.insertNode(17);

        treeStruct.printByLayer(value -> System.out.print(value + ","));
        System.out.println("==========");
        System.out.println(treeStruct.height());

        treeStruct.deleteNode(13);
        treeStruct.printByLayer(value -> System.out.print(value + ","));
        System.out.println("==========");
        System.out.println(treeStruct.height());

        treeStruct.deleteNode(18);
        treeStruct.printByLayer(value -> System.out.print(value + ","));
        System.out.println("==========");
        System.out.println(treeStruct.height());

        treeStruct.deleteNode(33);
        treeStruct.printByLayer(value -> System.out.print(value + ","));
        System.out.println("==========");
        System.out.println(treeStruct.height());

        treeStruct.deleteNode(27);
        treeStruct.printByLayer(value -> System.out.print(value + ","));
        System.out.println("==========");
        System.out.println(treeStruct.height());

        treeStruct.deleteNode(55);
        treeStruct.printByLayer(value -> System.out.print(value + ","));
        System.out.println("==========");
        System.out.println(treeStruct.height());
    }

}
