package org.coding.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * 用Queue来实现Josephus问题
 * 在这个古老的问题当中， N个深陷绝境的人一致同意用这种方式减少生存人数：  N个人围成一圈（位置记为0到N-1）， 并且从第一个人报数， 报到M的人会被杀死， 直到最后一个人留下来
 * （已知n个人（以编号1，2，3…n分别表示）围坐在一张圆桌周围。从编号为k的人开始报数，数到m的那个人出列；他的下一个人又从1开始报数，数到m的那个人又出列；依此规律重复下去，直到圆桌周围的人全部出列。）
 * 该方法返回一个List， 包含了被杀死人的次序
 */
public class Josephus {
    
    public static List<Integer> execute(int n, int m){
        CircleQueue<Integer> queue = new CircleQueue<>(n);
        for (int i = 0; i < n; i++) {
            queue.enQueue(i);
        }
        List<Integer> result = new ArrayList<Integer>();
        int index = 0;
        while (!queue.isEmpty()) {
        	Integer item = queue.deQueue();
        	if (++index % m == 0) {	//通过 % 实现报数的循环
        		result.add(item);
        	} else {
        		queue.enQueue(item);	//将其放入队列，继续报数
        	}
        }
        return result;
    }
    
    public static List<Integer> execute1(int n, int m){
        CircleQueue<Integer> queue = new CircleQueue<>(n);
        for (int i = 0; i < n; i++) {
            queue.enQueue(i);
        }
        List<Integer> result = new ArrayList<Integer>();
        int index = 1;	//报数
        doExecute(new CircleQueue<>(n), m, queue, result, index);
        return result;
    }

    private static void doExecute(CircleQueue<Integer> q2, int m, CircleQueue<Integer> queue, List<Integer> result, int index) {
        while (!queue.isEmpty()) {
            if (index == m) {
                result.add(queue.deQueue());
                index = 1;
                continue;
            }
            index++;
            q2.enQueue(queue.deQueue());
        }
        if(!q2.isEmpty()) {
            doExecute(new CircleQueue<>(q2.size()), m, q2, result, index);
        }
    }
}
