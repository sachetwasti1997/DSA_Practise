package lists;

public class CustomStack {

    private class Node {
        Node front;
        Node back;
        int data;
        Node(int data) {
            this.data = data;
        }
    }

    private Node front;
    private Node back;
    private final int capacity;
    private int currentSize;

    public CustomStack(int maxSize) {
        this.capacity = maxSize;
        currentSize = 0;
    }

    public void push(int x) {
        if (currentSize == capacity) {
            return;
        }
        if (front == null) {
            front = new Node(x);
            back = front;
            currentSize ++;
            return;
        }
        var temp = front;
        front = new Node(x);
        temp.front = front;
        front.back = temp;
        currentSize++;
    }

    public int pop() {
        if (front == null)
            return -1;
        var temp = front;
        front = front.back;
        currentSize--;
        return temp.data;
    }

    public void increment(int k, int val) {
        if (front == null)
            return;
        var temp = back;
        for (int i=0; i< k && temp != null; i++, temp=temp.front) {
            temp.data += val;
        }
    }
}
