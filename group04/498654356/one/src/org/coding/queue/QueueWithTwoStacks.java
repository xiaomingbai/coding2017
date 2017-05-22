package org.coding.queue;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列
 *一个用来放入数据；一个用来取出数据.
 */
public class QueueWithTwoStacks<E> {
    private Stack<E> stack1;    
    private Stack<E> stack2;    

    public QueueWithTwoStacks() {
        stack1 = new Stack<E>();
        stack2 = new Stack<E>();
    }

    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    public int size() {
        return (stack1.size() + stack2.size());   
    }

    public void enQueue(E item) {
        this.stack1.push(item);
    }

    private void moveItem(Stack<E> source, Stack<E> dest) {
        while(!source.isEmpty()) {
            dest.push(source.pop());
        }
    }

    public E deQueue() {
        if(this.isEmpty()) {
            return null;
        }
        if (this.stack2.isEmpty()) {
            moveItem(this.stack1, this.stack2);
        }
        return this.stack2.pop();
//        //颠倒
//        moveItem(this.stack1, this.stack2);
//        //取出栈底元素
//        E item = this.stack2.pop();
//        //还原
//        moveItem(this.stack2, this.stack1);
//        return item;
    }
    
}
