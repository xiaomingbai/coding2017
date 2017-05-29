package org.coding.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉搜索树：二叉树加一个额外的条件,每个节点都不比它左子树的任意元素小，而且不比它的右子树的任意元素大。
 *      一句话就是左孩子比父节点小，右孩子比父节点大，还有一个特性就是”中序遍历“可以让结点有序。
 */
public class BinarySearchTree<T extends Comparable<T>>  {
    BinaryTreeNode<T> root;
    public BinarySearchTree(BinaryTreeNode<T> root){
        this.root = root;
    }
    public BinaryTreeNode<T> getRoot(){
        return root;
    }
    /**
     * 查找最小值
     */
    public T findMin(){
        return doFindMin(this.root).getData();
    }
    
    private BinaryTreeNode<T> doFindMin(BinaryTreeNode<T> node) {
        if (node.getLeft() == null) {
            return node;
        }
        return doFindMin(node.getLeft());
    }
    
    /**
     * 查找最大值
     */
    public T findMax(){
        return doFindMax(this.root);
    }
    
    private T doFindMax(BinaryTreeNode<T> node) {
        if (node.getRight() == null) {
            return node.getData();
        }
        return doFindMax(node.getRight());
    }
    
    /**
     * 节点的层次：从根开始定义起，根为第1层，根的子节点为第2层，以此类推；
     * 树的高度或深度：树中节点的最大层次；
     * @return
     */
    public int height() {
        if (this.root == null) {
            return 0;
        }
        return doHeight(this.root, 1);
    }
    
    /**
     * 有一个子节点 height + 1;最后返回的左右子节点的最大值 
     */
    private int doHeight(BinaryTreeNode<T> node, int height) {
        if (node.getLeft() != null && node.getRight() != null) {
            int leftHeight = doHeight(node.getLeft(), height + 1);
            int rightHeight = doHeight(node.getRight(), height + 1);
            return leftHeight > rightHeight ? leftHeight : rightHeight; //TODO 比较返回最大值
        } else if (node.getLeft() != null) {
            return doHeight(node.getLeft(), height + 1);
        } else if (node.getRight() != null) {
            return doHeight(node.getRight() , height + 1);
        }
        return height;
    }
    
    /**
     * 节点个数
     * @return
     */
    public int size() {
        if (this.root == null) {
            return 0;
        }
        return doSize(this.root, 1);
    }
    
    //??
    private int doSize(BinaryTreeNode<T> node, int size) {
        if (node.getLeft() != null && node.getRight() != null) {
            int leftSize = doSize(node.getLeft(), 1);
            int rightSize = doSize(node.getRight(), 1);
            return leftSize + rightSize + 1;    //TODO 相加返回 左 + 右 + 自身[1]
        } else if (node.getLeft() != null) {
            return doSize(node.getLeft(), size + 1);
        } else if (node.getRight() != null) {
            return doSize(node.getRight() , size + 1);
        }
        return size;
    }
    
    /**
     * 删除节点
     */
    public void remove(T e){
        if (this.root == null || e == null) {
            return;
        }
        BinaryTreeNode<T> node = findNode(this.root, e);
        BinaryTreeNode<T> parent = findParent(new Stack<BinaryTreeNode<T>>(), this.root, node);
        if (isLeafNode(node)) { //叶子节点
            doRemoveLeafNode(node, parent);
        } else {
            if (node.getRight() != null) {  //右节点不为空,查找最小节点移动到要移除的节点处
                BinaryTreeNode<T> minNode = doFindMin(node.getRight());
                BinaryTreeNode<T> minNodeParent = findParent(new Stack<BinaryTreeNode<T>>(), node, minNode);
                doRemoveLeafNode(minNode, minNodeParent);
                
                minNode.setLeft(node.getLeft());
                minNode.setRight(node.getRight());
                doRemoveNotLeafNode(node, parent, minNode);
                
                node.setLeft(null);
                node.setRight(null);
            } else {    //右节点为空,左节点不为空.直接将下一个左节点移动到要移除的节点处
                BinaryTreeNode<T> leftNode = node.getLeft();
                doRemoveNotLeafNode(node, parent, leftNode);
                node.setLeft(null);
            }
        }
    }
    private void doRemoveNotLeafNode(BinaryTreeNode<T> node, BinaryTreeNode<T> parent, BinaryTreeNode<T> minNode) {
        if (isLeftNode(node, parent)) { 
            parent.setLeft(minNode);
        } else {
            parent.setRight(minNode);
        }
    }
    
    private void doRemoveLeafNode(BinaryTreeNode<T> node, BinaryTreeNode<T> parent) {
        if (isLeftNode(node, parent)) {   //左节点
            parent.setLeft(null);
        } else {
            parent.setRight(null);
        }
    }
    private boolean isLeftNode(BinaryTreeNode<T> node, BinaryTreeNode<T> parent) {
        return node.getData().compareTo(parent.getData()) < 0;
    }
    
    private boolean isLeafNode(BinaryTreeNode<T> node) {
        return node.getLeft() == null && node.getRight() == null;
    }
    
    private BinaryTreeNode<T> findParent(Stack<BinaryTreeNode<T>> stack, BinaryTreeNode<T> node,
            BinaryTreeNode<T> child) {
        int result = child.getData().compareTo(node.getData());
        if (result == 0) {
            return stack.pop();
        } else {
            stack.push(node);
            node = result < 0 ? node.getLeft() : node.getRight();
            return findParent(stack, node, child);
        }
    }
    private BinaryTreeNode<T> findNode(BinaryTreeNode<T> node, T e) {
        int result = e.compareTo(node.getData());
        if (result < 0) {
            return findNode(node.getLeft(), e);
        } else if (result > 0) {
            return findNode(node.getRight(), e);
        }
        return node;
    }
    
    /**
     * 分层次遍历
     */
    public List<T> levelVisit(){
        List<T> result = new ArrayList<>();
        Queue<BinaryTreeNode<T>> queue = new ArrayDeque<BinaryTreeNode<T>>();
        queue.add(this.root);
        doLevelVisit(queue, result);
        return result;
    }
    
    private void doLevelVisit(Queue<BinaryTreeNode<T>> queue, List<T> result) {
    	if (queue.isEmpty()) {
    		return ;
    	}
        Queue<BinaryTreeNode<T>> q = new ArrayDeque<BinaryTreeNode<T>>();
        BinaryTreeNode<T> currentNode = null;
        while ((currentNode = queue.poll()) != null) {
        	result.add(currentNode.getData());
        	if (currentNode.getLeft() != null) {
        		q.add(currentNode.getLeft());
        	}
        	if (currentNode.getRight() != null) {
        		q.add(currentNode.getRight());
        	}
        }
		doLevelVisit(q, result);
	}
	/**
     * 是否是一个有效的"二叉搜索树"
     * @return
     */
    public boolean isValid(){
        return doIsValid(this.root);
    }
    
    /**
     * 根节点 > 左子树的所有节点值； 根节点 < 右子数的所有节点值
     * @param node
     * @return
     */
    private boolean doIsValid(BinaryTreeNode<T> node) {
    	if (node == null) {
    		return true;
    	}
    	BinaryTreeNode<T> left = node.getLeft();
    	boolean leftValid = false;
    	if (left == null) {
    		leftValid = true;
    	} else if (node.getData().compareTo(left.getData()) > 0 
    				&& isValidForRoot(left, node)) {	//
    		leftValid = doIsValid(left);
    	}
    	BinaryTreeNode<T> right = node.getRight();
    	boolean rightValid = false;
    	if (right == null) {
    		rightValid = true;
    	} else if (node.getData().compareTo(right.getData()) < 0
    				&& isValidForRoot(right, node)) {
    		rightValid = doIsValid(right);
    	}
		return leftValid && rightValid;
	}
	private boolean isValidForRoot(BinaryTreeNode<T> child, BinaryTreeNode<T> node) {
		int compareTo = this.root.getData().compareTo(node.getData());
		if (compareTo == 0) {
			return true;
		}
		boolean isLeftChild = compareTo > 0;	// 传递性判断是否为左子树	TODO
		int compareVal = this.root.getData().compareTo(child.getData());
		if (isLeftChild) {
			return compareVal > 0;
		} 
		return compareVal < 0;
	}
	/**
     * 找出最小祖先节点
     * @param n1
     * @param n2
     * @return
     */
    public T getLowestCommonAncestor(T n1, T n2){
    	
    	if(n1.compareTo(n2) == 0) {
    		return findParent(new Stack<BinaryTreeNode<T>>(), this.root, findNode(this.root, n1)).getData();
    	}
    	
    	int compareVal = this.root.getData().compareTo(n1);
    	int compareVal2 = this.root.getData().compareTo(n2);
    	if (compareVal * compareVal2 <= 0) {	//在根节点左右或者就是根节点
    		return this.root.getData();
    	}
    	int level = getLevel(this.root, n1, 1);
    	int level2 = getLevel(this.root, n2, 1);
    	if (level == 0 || level2 == 0) {
    		return null;
    	}
    	
        BinaryTreeNode<T> parent1 = findParent(new Stack<BinaryTreeNode<T>>(), this.root, findNode(this.root, n1));
        BinaryTreeNode<T> parent2 = findParent(new Stack<BinaryTreeNode<T>>(), this.root, findNode(this.root, n2));
        
		if (parent1.getData().compareTo(parent2.getData()) == 0) {
			return parent1.getData();
		}
		
    	if (level == level2) {
	        return getLowestCommonAncestor(parent1.getData(), parent2.getData());
    	} else if (level < level2) {
	        return getLowestCommonAncestor(n1, parent2.getData());
    	} else {
	        return getLowestCommonAncestor(parent1.getData(), n2);
    	}
    }
    
    /**
     * 获取所在层
     * @return
     */
    private int getLevel(BinaryTreeNode<T> node, T data, int level) {
    	int compareVal = node.getData().compareTo(data);
    	if (compareVal == 0) {
    		return level;
    	} else if (compareVal > 0 && node.getLeft() != null) {
    		return getLevel(node.getLeft(), data, level + 1);
    	} else if (compareVal < 0 && node.getRight() != null) {
    		return getLevel(node.getRight(), data, level + 1);
    	}
		return 0;
	}
    
	/**
     * 查找值在该区间的节点链表(n1 < x < n2) 
     * 遍历比较
     * @param n1
     * @param n2
     * @return
     */
    public List<T> getNodesBetween(T n1, T n2){
    	List<T> result = new ArrayList<T>();
    	getNodesBetween(n1, n2, result, this.root);
        return result;
    }
    
    /**
     * 中序遍历，比较
     */
	private void getNodesBetween(T n1, T n2, List<T> result, BinaryTreeNode<T> node) {
		if (node == null) {
			return;
		}
		if (isBetween(n1, n2, node.getData())) {
			result.add(node.getData());
		}
		getNodesBetween(n1, n2, result, node.getLeft());
		getNodesBetween(n1, n2, result, node.getRight());
	}

	private boolean isBetween(T n1, T n2, T data) {
		return data.compareTo(n1) * data.compareTo(n2) < 0;
	}

}
