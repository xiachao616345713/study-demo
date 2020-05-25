package data.tree;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;
import lombok.Getter;
import lombok.Setter;

/**
 * @author chao
 * @since 2019-04-02
 */
@Setter
@Getter
public class TreeStruct {

    public TreeStruct() {
    }

    public TreeStruct(Integer value) {
        this.value = value;
    }

    private Integer value;
    private TreeStruct left;
    private TreeStruct right;

    /**
     * 按层输出
     */
    public void printByLayer() {
        Queue<TreeStruct> queue = new LinkedList<>();
        queue.offer(this);
        TreeStruct temp;
        while (!queue.isEmpty()) {
            temp = queue.poll();
            assert temp != null;
            System.out.println(temp.value);
            if (temp.left != null) {
                queue.offer(temp.left);
            }
            if (temp.right != null) {
                queue.offer(temp.right);
            }
        }
    }

    public void printByLayer(Consumer<Integer> consumer) {
        Queue<TreeStruct> queue = new LinkedList<>();
        queue.offer(this);
        TreeStruct temp;
        while (!queue.isEmpty()) {
            temp = queue.poll();
            assert temp != null;
            consumer.accept(temp.value);
            if (temp.left != null) {
                queue.offer(temp.left);
            }
            if (temp.right != null) {
                queue.offer(temp.right);
            }
        }
    }

    /**
     * 遍历
     */
    public void travel() {
        travelRecursion(this);
    }
    /**
     * 前序遍历
     */
    private void travelRecursion(TreeStruct node) {
        if (node != null) {
            System.out.print(node.value + ",");
            travelRecursion(node.getLeft());
            travelRecursion(node.getRight());
        }
    }

    public void travelNoRecursion() {
        travelNoRecursion(this);
    }

    /**
     * 前序遍历无递归
     * @param node
     */
    private void travelNoRecursion(TreeStruct node) {
        Deque<TreeStruct> stack = new ArrayDeque<>();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                System.out.print(node.value + ",");
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop().right;
            }
        }
    }


    // ====== 后续遍历 start =====

    public void travelAfter() {
        travelRecursionAfter(this);
    }

    private void travelRecursionAfter(TreeStruct node) {
        if (node != null) {
            travelRecursionAfter(node.getLeft());
            travelRecursionAfter(node.getRight());
            System.out.print(node.value + ",");
        }
    }

    public void travelAfterNoRecursion() {
        travelAfterNoRecursion(this);
    }

    /**
     * 前序遍历无递归
     * @param node
     */
    private void travelAfterNoRecursion(TreeStruct node) {
        Deque<TreeStruct> stack = new ArrayDeque<>();
        stack.push(node);
        Deque<TreeStruct> ret = new ArrayDeque<>();
        TreeStruct tmp;
        while (!stack.isEmpty()) {
            tmp = stack.pop();

            ret.push(tmp);
            // 先入后出，stack左节先入，后压入结果栈，最后输出先输出(模拟递归)
            if (tmp.getLeft() != null) {
                stack.push(tmp.getLeft());
            }
            if (tmp.getRight() != null) {
                stack.push(tmp.getRight());
            }
        }
        while (!ret.isEmpty()) {
            System.out.print(ret.pop().value + ",");
        }
    }


    // ====== 后续遍历 end =====

    /**
     * 查找
     */
    public TreeStruct find(Integer value) {
        TreeStruct node = this;
        while (node != null) {
            if (node.getValue() > value) {
                node = node.getLeft();
            } else if (node.getValue() < value) {
                node = node.getRight();
            } else {
                return node;
            }
        }
        return null;
    }

    /**
     * 插入node，大于等于在右叶子，小于根节点在左叶子
     */
    public void insertNode(Integer value) {
        TreeStruct node = this;
        while (node != null) {
            if (node.getValue() == null) {
                node.setValue(value);
                break;
            }
            if (node.getValue() < value) {
                if (node.getRight() == null) {
                    node.setRight(new TreeStruct(value));
                    node = null;
                } else {
                    node = node.getRight();
                }
            } else {
                if (node.getLeft() == null) {
                    node.setLeft(new TreeStruct(value));
                    node = null;
                } else {
                    node = node.getLeft();
                }
            }
        }
    }

    /**
     * 删除1个节点，不删除重复数据
     */
    public void deleteNode(Integer value) {
        TreeStruct pre = null;
        TreeStruct node = this;
        while (node != null) {
            if (node.getValue() > value) {
                pre = node;
                node = node.getLeft();
            } else if(node.getValue() < value) {
                pre = node;
                node = node.getRight();
            } else {
                if (node.getLeft() == null) {
                    if (node.getRight() == null) {
                        if (pre == null) {
                            this.value = null;
                        } else {
                            if (pre.getLeft() == node) {
                                pre.setLeft(null);
                            } else if (pre.getRight() == node){
                                pre.setRight(null);
                            }
                        }
                    } else {
                        // left == null && right != null
                        // find right minimum element
                        TreeStruct tempPre = null;
                        TreeStruct temp = node.getRight();
                        while (temp.getLeft() != null) {
                            tempPre = temp;
                            temp = temp.getLeft();
                        }
                        // remove temp
                        if (tempPre != null) {
                            tempPre.setLeft(null);
                        } else {
                            node.setRight(null);
                        }
                        // move temp to node
                        temp.setLeft(node.getLeft());
                        temp.setRight(node.getRight());
                        if (pre != null) {
                            if (pre.getLeft() == node) {
                                pre.setLeft(temp);
                            } else if (pre.getRight() == node) {
                                pre.setRight(temp);
                            }
                        }
                    }
                } else {
                    // left != null && right == null || left != null && right != null
                    // find left maximum element
                    TreeStruct tempPre = null;
                    TreeStruct temp = node.getLeft();
                    while (temp.getRight() != null) {
                        tempPre = temp;
                        temp = temp.getRight();
                    }
                    // remove temp
                    if (tempPre != null) {
                        tempPre.setRight(null);
                    } else {
                        node.setLeft(null);
                    }
                    // move temp to node
                    temp.setLeft(node.getLeft());
                    temp.setRight(node.getRight());
                    if (pre != null) {
                        if (pre.getLeft() == node) {
                            pre.setLeft(temp);
                        } else if (pre.getRight() == node) {
                            pre.setRight(temp);
                        }
                    } else {
                        this.value = temp.getValue();
                    }
                }
                break;
            }
        }
    }

    /**
     * 树高
     */
    public Integer height() {
        return height(this);
    }

    private Integer height(TreeStruct treeStruct) {
        if (treeStruct == null) {
            return 0;
        } else {
            Integer leftHeight = height(treeStruct.getLeft());
            Integer rightHeight = height(treeStruct.getRight());
            return leftHeight >= rightHeight ? leftHeight + 1 : rightHeight + 1;
        }
    }

    private Integer heightNoRecursion(TreeStruct treeStruct) {
        Queue<TreeStruct> queue = new ArrayDeque<>();
        queue.add(treeStruct);

        int height = 0;

        TreeStruct tmp;
        while (!queue.isEmpty()) {
            height++;
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                tmp = queue.remove();

                if (tmp.getLeft() != null) {
                    queue.add(tmp.getLeft());
                }
                if (tmp.getRight() != null) {
                    queue.add(tmp.getRight());
                }
            }
        }

        return height;
    }

    // 给定n个数字多少种组合?
    /*
     * <a href="https://en.wikipedia.org/wiki/Catalan_number">卡特兰数</a>
     *
     * f(0)=1;f(1) = 1;
     * n个数字，1个在根节点其他可能情况。0个在左，n-1个在右；1个在左，n-2个在右；2个在左，n-3个在右。。。
     * f(n) = f(0)f(n-1) + f(1)f(n-2) + ... + f(n-2)f(1) + f(n-1)f(0)
     *
     * Cn = (2n)!/((n+1)!*n!)
     */

}
