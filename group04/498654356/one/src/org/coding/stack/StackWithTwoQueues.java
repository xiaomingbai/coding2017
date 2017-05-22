package org.coding.stack;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 使用两个队列实现栈 
 * 两个队列间的移动，可以保证元素的原始顺序。
 */
public class StackWithTwoQueues<E> {
	private Queue<E> queue1;
	private Queue<E> queue2;

	public StackWithTwoQueues() {
		super();
		this.queue1 = new ArrayDeque<>();
		this.queue2 = new ArrayDeque<>();
	}

	public void push(E data) {
		if (this.queue1.isEmpty() && this.queue2.isEmpty()) {
			this.queue1.add(data);
			return;
		}
		if (this.queue1.isEmpty()) {
			this.queue2.add(data);
			return;
		}
		if (this.queue2.isEmpty()) {
			this.queue1.add(data);
			return;
		}
	}

	//每次取队列尾部的元素
	public E pop() {
		if (this.queue1.isEmpty() && this.queue2.isEmpty()) {
			throw new RuntimeException("The stack is empty");
		}
		if (this.queue2.isEmpty()) {
			while (this.queue1.size() > 1) {
				this.queue2.add(this.queue1.poll());
			}
			return this.queue1.poll();
		}
		
		if (this.queue1.isEmpty()) {
			while (this.queue2.size() > 1) {
				this.queue1.add(this.queue2.poll());
			}
			return this.queue2.poll();
		}
		return null;
	}
}
