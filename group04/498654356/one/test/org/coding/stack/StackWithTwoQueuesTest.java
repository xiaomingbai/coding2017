package org.coding.stack;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StackWithTwoQueuesTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		StackWithTwoQueues<Integer> stack = new StackWithTwoQueues<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        Assert.assertEquals(4, (int)stack.pop());
        Assert.assertEquals(3, (int)stack.pop());
      
        stack.push(5);
        Assert.assertEquals(5, (int)stack.pop());
        Assert.assertEquals(2, (int)stack.pop());
		Assert.assertEquals(1, (int)stack.pop());
	}

}
