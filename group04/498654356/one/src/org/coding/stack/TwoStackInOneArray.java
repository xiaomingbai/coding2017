package org.coding.stack;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 */
public class TwoStackInOneArray {
    
    private static final int DEFAULT_CAPACITY = 10;
    
    private Object[] data;
    //相当于 size
    private int top1;
    private int top2;
    
    public TwoStackInOneArray(int capacity) {
        this.data = new Object[capacity];
        this.top1 = 0;
        this.top2 = capacity - 1;
    }
    
    public TwoStackInOneArray() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 向第一个栈中压入元素
     * @param o
     */
    public void push1(Object o){
        ensureCapacity();
        this.data[this.top1++] = o;
    }
    
    private void ensureCapacity() {
        if (this.top2 - this.top1 == 0) {
            Object[] arr = new Object[this.data.length * 2];
            System.arraycopy(this.data, 0, arr, 0, this.top1);
            int stack2Count = this.data.length - this.top2 - 1;
            int newTop2Index = arr.length - stack2Count;
            if(stack2Count == 0) {
                this.top2 = arr.length - 1;
            } else {
                System.arraycopy(this.data, this.top2 + 1, arr, newTop2Index, stack2Count);
                this.top2 = newTop2Index - 1;
            }
            this.data = arr;
        }
    }

    /**
     * 从第一个栈中弹出元素
     * @return
     */
    public Object pop1(){
        if (this.top1 == 0) {
            throw new RuntimeException("The stack1 is empty");
        }
        this.top1--;
        Object data = this.data[this.top1];
        this.data[this.top1] = null;
        return data;
    }
    
    /**
     * 获取第一个栈的栈顶元素
     * @return
     */
    
    public Object peek1(){
        if (this.top1 == 0) {
            throw new RuntimeException("The stack1 is empty");
        }
        this.top1--;
        Object data = this.data[this.top1];
        this.top1++;
        return data;
    }
    
    /**
     * 向第二个栈压入元素
     */
    public void push2(Object o){
        ensureCapacity();
        this.data[this.top2--] = o;
    }
    
    /**
     * 从第二个栈弹出元素
     * @return
     */
    public Object pop2(){
        if (this.top2  ==  this.data.length - 1) {
            throw new RuntimeException("The stack2 is empty");
        }
        this.top2++;
        Object data = this.data[this.top2];
        this.data[this.top2] = null;
        return data;
    }
    /**
     * 获取第二个栈的栈顶元素
     * @return
     */
    
    public Object peek2(){
        if (this.top2  ==  this.data.length - 1) {
            throw new RuntimeException("The stack2 is empty");
        }
        this.top2++;
        Object data = this.data[this.top2];
        this.top2--;
        return data;
    }

    public Object[] stack1ToArray() {
        Object[] arr = new Object[this.top1];
        for(int i = 0; i < this.top1; i++) {
            arr[i] = this.data[i];
        }
        return arr;
    }

    public Object[] stack2ToArray() {
        int stack2Count = this.data.length - this.top2 - 1;
        Object[] arr = new Object[stack2Count];
        for(int i = this.data.length - 1, j = 0; i > this.top2; i--, j++) {
            arr[j] = this.data[i];
        }
        return arr;
    }

}
