package org.coding.queue;

/**
 * 使用联表实现队列：FIFO 
 * 队列只允许在后端（称为rear）进行插入操作，在前端（称为front）进行删除操作
 */
public class Queue<E> {

    private Node first;
    private Node last;
    private int size;
    
    private class Node {
        private Node next;
        private E data;
        public Node(E data) {
            super();
            this.data = data;
        }
    }
    
    public boolean isEmpty() {
        return this.first == null;
    }
    
    public int size() {
        return this.size;
    }
    
    public void enQueue(E data) {
        Node node = new Node(data);
        if (this.first == null) {
            this.first = node;
            this.last = node;
        } else {
            this.last.next = node;
            this.last = node;
        }
        size++;
    }
    
    public E deQueue() {
        if(this.first == null) {
           return null;
        }
        Node node = this.first;
        this.first = node.next;
        node.next = null;
        size--;
        return node.data;
    }
}
