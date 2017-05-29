package org.coding.tree;
/**
 * 二叉树的每个节点最多只能有2个子节点
 */
public class BinaryTreeNode<T extends Comparable<T>> {
    
    private T data;
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;
    
    public BinaryTreeNode(T data){
        this.data=data;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public BinaryTreeNode<T> getLeft() {
        return left;
    }
    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }
    public BinaryTreeNode<T> getRight() {
        return right;
    }
    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }
    
    public BinaryTreeNode<T> insert(T o) {
        int compareVal = o.compareTo(this.getData());
        if (compareVal < 0) {
            if (this.getLeft() == null) {
                this.setLeft(new BinaryTreeNode<T>(o));
            } else {
                this.getLeft().insert(o);
            }
        } else if (compareVal > 0) {
            if (this.getRight() == null) {
                this.setRight(new BinaryTreeNode<T>(o));
            } else {
                this.getRight().insert(o);
            }
        }
        return this;
    }
    @Override
    public String toString() {
        return "BinaryTreeNode [data=" + data + "]";
    }
}
