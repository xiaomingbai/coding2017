package org.coding.tree;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest {
BinarySearchTree<Integer> tree = null;
    
    @Before
    public void setUp() throws Exception {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(6);
        root.setLeft(new BinaryTreeNode<Integer>(2));
        root.setRight(new BinaryTreeNode<Integer>(8));
        root.getLeft().setLeft(new BinaryTreeNode<Integer>(1));
        root.getLeft().setRight(new BinaryTreeNode<Integer>(4));
        root.getLeft().getRight().setLeft(new BinaryTreeNode<Integer>(3));
        tree = new BinarySearchTree<Integer>(root);
    }

    @After
    public void tearDown() throws Exception {
        tree = null;
    }

    @Test
    public void testFindMin() {
        Assert.assertEquals(1, tree.findMin().intValue());
        
    }

    @Test
    public void testFindMax() {
        Assert.assertEquals(8, tree.findMax().intValue());
    }

    @Test
    public void testHeight() {
        Assert.assertEquals(4, tree.height());
    }

    @Test
    public void testSize() {
        Assert.assertEquals(6, tree.size());
    }

    @Test
    public void testRemoveLeaf() {
        tree.remove(4);
        BinaryTreeNode<Integer> root= tree.getRoot();
        Assert.assertEquals(3, root.getLeft().getRight().getData().intValue());
        
    }
    @Test
    public void testRemoveMiddleNode() {
        tree.remove(2);
        BinaryTreeNode<Integer> root= tree.getRoot();
        Assert.assertEquals(3, root.getLeft().getData().intValue());
        Assert.assertEquals(4, root.getLeft().getRight().getData().intValue());
    }

    @Test
    public void testLevelVisit() {
    	Assert.assertEquals("[6, 2, 8, 1, 4, 3]", tree.levelVisit().toString());
    }
    
    @Test
    public void testIsValid() {
    	Assert.assertTrue(tree.isValid());
    	tree.getRoot().getRight().setLeft(new BinaryTreeNode<Integer>(10));
    	Assert.assertFalse(tree.isValid());
    }
    
    @Test
    public void testIsValid2() {
    	Assert.assertTrue(tree.isValid());
    	tree.getRoot().getRight().setLeft(new BinaryTreeNode<Integer>(1));
    	Assert.assertFalse(tree.isValid());
    }
    
    @Test
    public void testGetLowestCommonAncestor() {
    	// root
    	Assert.assertEquals(6, (int)tree.getLowestCommonAncestor(1, 8));
    	Assert.assertEquals(6, (int)tree.getLowestCommonAncestor(6, 8));
    	Assert.assertEquals(6, (int)tree.getLowestCommonAncestor(8, 6));
    	Assert.assertEquals(6, (int)tree.getLowestCommonAncestor(8, 1));
    	
    	// other
    	Assert.assertEquals(2, (int)tree.getLowestCommonAncestor(1, 3));
    	Assert.assertEquals(2, (int)tree.getLowestCommonAncestor(1, 4));
    	Assert.assertEquals(2, (int)tree.getLowestCommonAncestor(3, 1));
    	
    	Assert.assertEquals(2, (int)tree.getLowestCommonAncestor(1, 1));
    	
    	tree.getRoot().getLeft().getRight().setRight(new BinaryTreeNode<Integer>(5));
    	Assert.assertEquals(2, (int)tree.getLowestCommonAncestor(5, 1));
    	Assert.assertEquals(4, (int)tree.getLowestCommonAncestor(3, 5));
    }
    
    @Test
    public void testGetNodesBetween() {
    	Assert.assertEquals("[6, 2, 1, 4, 3]", tree.getNodesBetween(0, 7).toString());
    }
}
