package org.coding.stack;

import java.util.Stack;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 */
public class QuickMinStack {
	private Stack<Integer> stack = new Stack<>();
	//记录多个值,保证最小值是一个连续的. 弹出栈顶元素之后,下一个元素依然是当前的最小值.
	private Stack<Integer> minStack = new Stack<>();
		
	public void push(int data){
		stack.push(data);
		if (minStack.isEmpty()) {
			minStack.push(data);
		} else {
			if (minStack.peek() >= data) {	//栈顶记录当前的最小值
				minStack.push(data);
			}
		}
	}
		
	public int pop(){
		int val = stack.pop();
		if (minStack.peek().intValue() == val) {
			minStack.pop();
		}
		return val;
	}
		
	public int findMin(){
		return minStack.peek();
	}
}
