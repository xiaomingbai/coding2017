package org.coding.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeUtil {
    /**
     * 用递归的方式实现对二叉树的前序遍历， 需要通过BinaryTreeUtilTest测试
     * 
     * 前序遍历按照“根结点-左孩子-右孩子”的顺序进行访问。
     *      即对于任一结点，其可看做是根结点，因此可以直接访问，访问完之后，若其左孩子不为空，按相同规则访问它的左子树；当访问其左子树为空时，再访问它的右子树。
     * @param root
     * @return
     */
    public static <T extends Comparable<T>> List<T> preOrderVisit(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<T>();
        result.add(root.getData());
        if (root.getLeft() != null) {
            result.addAll(preOrderVisit(root.getLeft()));
        }
        if (root.getRight() != null) {
            result.addAll(preOrderVisit(root.getRight()));
        }
        return result;
    }

    /**
     * 用递归的方式实现对二叉树的中遍历
     * 中序遍历按照“左孩子-根结点-右孩子”的顺序进行访问。
     *      根据中序遍历的顺序，对于任一结点，优先访问其左孩子，而左孩子结点又可以看做一根结点，然后继续访问其左孩子结点，直到遇到左孩子结点为空的结点才进行访问根结点，然后按相同的规则访问其右子树。
     * @param root
     * @return
     */
    public static <T extends Comparable<T>> List<T> inOrderVisit(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<T>();
        BinaryTreeNode<T> left = root.getLeft();
        if (left == null) {
            result.add(root.getData());
        } else {
            result.addAll(inOrderVisit(left));
            //左节点遍历完成之后添加根结点.
            result.add(root.getData());
        }
        BinaryTreeNode<T> right = root.getRight();
        if (right != null) {
            result.addAll(inOrderVisit(right));
        }
        return result;
    }

    /**
     * 用递归的方式实现对二叉树的后遍历
     * 后序遍历按照“左孩子-右孩子-根结点”的顺序进行访问。
     *      根据中序遍历的顺序，对于任一结点，优先访问其左孩子，而左孩子结点又可以看做一根结点，然后继续访问其左孩子结点，直到遇到左孩子结点为空的结点，然后按相同的规则访问其右子树，直到叶子结点，才进行访问根结点。
     * @param root
     * @return
     */
    public static <T extends Comparable<T>> List<T> postOrderVisit(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<T>();
        BinaryTreeNode<T> left = root.getLeft();
        if (left != null) {
            result.addAll(postOrderVisit(left));
        }
        BinaryTreeNode<T> right = root.getRight();
        if (right != null) {
            result.addAll(postOrderVisit(right));
        }
        result.add(root.getData());
        return result;
    }
    /**
     * 用非递归的方式实现对二叉树的前序遍历
     * @param root
     * @return
     */
    public static <T extends Comparable<T>> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<T>();        
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        BinaryTreeNode<T> temp = root;
        //  开始遍历整个二叉树
        while (temp != null || !stack.isEmpty()) {
            // 输出当前子树的根节点，然后递归直至最左
            while (temp != null) {
                addAndPush(result, stack, temp);
                temp = temp.getLeft();
            }
            //  此时循环结束时，当前栈顶节点已经是最左节点
            //  此时递归开始返回，开始出栈，并输出节点的右节点
            if (!stack.isEmpty()) {
                temp = stack.peek();
                stack.pop();
                temp = temp.getRight();
            }
        }
        return result;
    }
    
    /**
     * 用非递归的方式实现对二叉树的前序遍历
     * @param root
     * @return
     */
    public static <T extends Comparable<T>> List<T> preOrderWithoutRecursion2(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<T>();        
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        
        //记录当前节点(即栈顶节点)
        BinaryTreeNode<T> temp = root;
        addAndPush(result, stack, root);
        
        while (!stack.isEmpty()) {
            while (temp.getLeft() != null) {
                temp = temp.getLeft();
                addAndPush(result, stack, temp);
            }
            //TODO ??
            if (temp.getRight() == null) {   // temp 是个叶子结点
                if (!stack.isEmpty()) {
                    stack.pop();    //从 stack 中 弹出 temp    
                }
                if (!stack.isEmpty()) { //弹出上一个节点并赋值给 temp
                    temp = stack.pop();
                } 
            }
            if (temp.getRight() != null) {
                temp = temp.getRight();
                addAndPush(result, stack, temp);
            }
        }
        return result;
    }

    private static <T extends Comparable<T>> void addAndPush(List<T> result, Stack<BinaryTreeNode<T>> stack, BinaryTreeNode<T> temp) {
        result.add(temp.getData());
        stack.push(temp);
    }
    
    /**
     * 用非递归的方式实现对二叉树的中序遍历
     * @param root
     * @return
     */
    public static <T extends Comparable<T>> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<T>();        
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        BinaryTreeNode<T> temp = root;
        //  开始遍历整个二叉树
        while (temp != null || !stack.isEmpty()) {
            // 不输出当前根节点，但是递归直至当前根节点node的最左端
            while (temp != null) {
                stack.push(temp);
                temp = temp.getLeft();
            }
            //  此时栈顶的元素是当前最左元素
            //  它应该被输出
            if (!stack.isEmpty()) {
                temp = stack.peek();
                result.add(temp.getData());
                stack.pop();
                temp = temp.getRight();
            }
        }
        return result;
    }
    /**
     * 用非递归的方式实现对二叉树的中序遍历
     * @param root
     * @return
     */
    public static <T extends Comparable<T>> List<T> inOrderWithoutRecursion2(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<T>();        
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        
        //记录当前节点(即栈顶节点)
        BinaryTreeNode<T> temp = root;
        stack.add(temp);
        
        while (!stack.isEmpty()) {
            while (temp.getLeft() != null) {
                temp = temp.getLeft();
                stack.add(temp);
            }
            //TODO ??
            result.add(temp.getData()); //左
            stack.pop();    //从 stack 中 弹出 temp
            if (!stack.isEmpty()) {
                temp = stack.pop(); //弹出根节点并赋值给 temp
                result.add(temp.getData()); //根
            }
            if (temp.getRight() == null) {   // temp 是个叶子结点
                if (!stack.isEmpty()) { //弹出上一个节点并赋值给 temp
                    temp = stack.pop();
                } 
            }
            if (temp.getRight() != null) {
                temp = temp.getRight();
                stack.add(temp);
            }
        }
        return result;
    }

}
