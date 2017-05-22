package org.coding.queue;

/**
 * 用数组实现循环队列
 * (http://blog.csdn.net/fansongy/article/details/6784954)
 */
public class CircleQueue<E> {
    private final static int DEFAULT_SIZE = 10;
    
    //用数组来保存循环队列的元素
    private Object[] elementData;
    
    public CircleQueue(int size) {
        super();
        this.elementData = new Object[size + 1] ;
    }
    
    public CircleQueue() {
        this(DEFAULT_SIZE + 1);
    }

    //队头
    private int front = 0;  
    //队尾  
    private int rear = 0;
    
    public boolean isEmpty() {
        return this.front == this.rear;
        
    }

    public int size() {
        if (this.rear < this.front) {
            return this.elementData.length - this.front + this.rear;
        }
        return this.rear - this.front;
    }

    public void enQueue(E data) {
        if (this.isFull()) {
            throw new RuntimeException("queue is full");
        }
        this.elementData[rear++] = data;
        if (this.rear == this.elementData.length) {
            this.rear = 0;
        }
    }

    @SuppressWarnings("unchecked")
    public E deQueue() {
        if (this.isEmpty()) {
            return null;
        }
        Object data = this.elementData[front];
        this.elementData[front] = null;
        this.front = (this.front + 1) % this.elementData.length;
        return (E) data;
    }

    public boolean isFull() {
        return (this.rear + 1) % this.elementData.length == this.front;
    }

}
