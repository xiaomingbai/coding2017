package org.coding.queue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QueueWithTwoStacksTest {
    private QueueWithTwoStacks<Integer> queue;
    
    @Before
    public void init() {
        queue = new QueueWithTwoStacks<>();
    }
    
    @After
    public void teardown() {
        queue = null;
    }

    @Test
    public void testEnQueue() {
        Assert.assertTrue(queue.isEmpty());
        Assert.assertEquals(0, queue.size());
        
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        
        Assert.assertFalse(queue.isEmpty());
        Assert.assertEquals(3, queue.size());
    }

    @Test
    public void testDeQueue() {
        Assert.assertTrue(queue.isEmpty());
        Assert.assertEquals(0, queue.size());
        
        queue.enQueue(1);
        queue.enQueue(2);
        Assert.assertEquals(1, (int)queue.deQueue());
        queue.enQueue(3);
        
        Assert.assertFalse(queue.isEmpty());
        Assert.assertEquals(2, queue.size());
        
        
        Assert.assertEquals(2, (int)queue.deQueue());
        Assert.assertEquals(3, (int)queue.deQueue());
        Assert.assertNull(queue.deQueue());
        
        Assert.assertTrue(queue.isEmpty());
        Assert.assertEquals(0, queue.size());
        
    }

}
