package lists;

public class MyCircularDeque {

    private int capacity;
    private Node<Integer> root;
    private int currentSz = 0;

    static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;
        Node(T data) {
            this.data = data;
        }
    }

    public MyCircularDeque(int k) {
        capacity = k;
        root = new Node<>(-1);
    }

    public boolean insertFront(int value) {
        if (currentSz == capacity) {
            return false;
        }
        var temp = root.prev;
        root.prev = new Node<>(value);
        root.prev.next = temp;
        if (temp != null) {
            temp.prev = root.prev;
        }
        if (root.next == null) {
            root.next = root.prev;
        }
        currentSz++;
        return true;
    }

    public boolean insertLast(int value) {
        if (currentSz == capacity) {
            return false;
        }
        var temp = root.next;
        root.next = new Node<>(value);
        root.next.prev = temp;
        if (temp != null) {
            temp.next = root.next;
        }
        if (root.prev == null) {
            root.prev = root.next;
        }
        currentSz++;
        return true;
    }

    public boolean deleteFront() {
        if (currentSz == 0) {
            return false;
        }
        else if (currentSz == 1) {
            root.next = null;
            root.prev = null;
            currentSz = 0;
            return true;
        }
        var temp = root.prev;
        root.prev = root.prev.next;
        root.prev.prev = null;
        temp.next = null;
        currentSz--;
        return true;
    }

    public boolean deleteLast() {
        if (currentSz == 0) {
            return false;
        }
        else if (currentSz == 1) {
            root.next = null;
            root.prev = null;
            currentSz = 0;
            return true;
        }
        var temp = root.next;
        root.next = root.next.prev;
        root.next.next = null;
        temp.prev = null;
        currentSz--;
        return true;
    }

    public int getFront() {
        if (currentSz == 0)
            return -1;
        return root.prev.data;
    }

    public int getRear() {
        if (currentSz == 0)
            return -1;
        return root.next.data;
    }

    public boolean isEmpty() {
        return currentSz == 0;
    }

    public boolean isFull() {
        return currentSz == capacity;
    }
}
