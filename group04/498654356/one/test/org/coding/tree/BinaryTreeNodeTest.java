package org.coding.tree;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BinaryTreeNodeTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        BinaryTreeNode<Integer> target = new BinaryTreeNode<Integer>(100);
        target.insert(70);
        target.insert(60);
        target.insert(80);
        
        target.insert(120);
        target.insert(110);
        target.insert(130);
        
        BinaryTreeNode<Integer> left = target.getLeft();
        Assert.assertEquals(70, left.getData().intValue());
        Assert.assertEquals(60, left.getLeft().getData().intValue());
        Assert.assertEquals(80, left.getRight().getData().intValue());
        
        BinaryTreeNode<Integer> right = target.getRight();
        Assert.assertEquals(120, right.getData().intValue());
        Assert.assertEquals(110, right.getLeft().getData().intValue());
        Assert.assertEquals(130, right.getRight().getData().intValue());

    }
    
    @Test
    public void test2() {
        BinaryTreeNode<Integer> target = new BinaryTreeNode<Integer>(1);
        target.insert(2);
        target.insert(3);
        target.insert(4);
        
        
        BinaryTreeNode<Integer> left = target.getLeft();
        Assert.assertNull(left);
        
        BinaryTreeNode<Integer> right = target.getRight();
        Assert.assertEquals(2, right.getData().intValue());
        Assert.assertEquals(3, right.getRight().getData().intValue());
        Assert.assertEquals(4, right.getRight().getRight().getData().intValue());

    }

}
