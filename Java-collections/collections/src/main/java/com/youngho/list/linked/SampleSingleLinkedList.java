package com.youngho.list.linked;

import java.util.Objects;

public class SampleSingleLinkedList<E> {

    /**
     * node 끼리 연결하여 리스트처럼 만든 Collection
     * 데이터의 중간 삽입/삭제가 빠르게 처리된다.
     * 임의의 요소에 대한 접근 성능은 좋지않다.
     * 특히 단일 연결 LinkedList 는 끝 요소를 찾으려면, 처음부터 끝까지 순회하며 탐색해야함
     *
     * 성능이 좋지 않기도 하고, 실제로도 Doubly LinkedList 로 구현이 되어있기 때문에
     * add, remove, get 정도만 구현해 볼 예정
     */
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public SampleSingleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean add(E data) {
        Node<E> newNode = new Node<>(data);

        if (Objects.isNull(head)) {
            head = newNode;
        } else {
            tail.next = newNode;
        }

        tail = newNode;

        size++;

        return true;
    }

    public boolean add(int index, E data) {

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        Node<E> newNode = new Node<>(data);

        if (index == 0) {
            newNode.next = head;
            head = newNode;

            if (Objects.isNull(tail)) {
                tail = newNode;
            }
        } else if (index == size) {
            tail.next = newNode;
            tail = newNode;
        } else {
            Node<E> prevNode = head;
            for (int i = 1; i < index; i++) {
                prevNode = prevNode.next;
            }
            newNode.next = prevNode.next;
            prevNode.next = newNode;
        }

        size++;
        return true;
    }

    public void addFirst(E data) {

        Node<E> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;

        if (Objects.isNull(tail)) {
            tail = newNode;
        }

        size++;
    }

    public void addLast(E data) {

        Node<E> newNode = new Node<>(data);

        if (Objects.isNull(head)) {
            head = newNode;
        } else {
            tail.next = newNode;
        }

        tail = newNode;

        size++;
    }

    public E remove() {

        if (size == 0) {
            return null;
        }
        Node<E> removeNode = head;
        head = removeNode.next;

        if (Objects.isNull(head)) {
            tail = null;
        }

        --size;

        return removeNode.data;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        Node<E> removeNode = head;
        if (index == 0) {
            head = removeNode.next;
            if (Objects.isNull(head)) {
                tail = null;
            }
        } else {
            Node<E> prevNode = null;
            for (int i = 0; i < index; i++) {
                prevNode = removeNode;
                removeNode = removeNode.next;
            }
            prevNode.next = removeNode.next;

            if (removeNode.next == null) {
                tail = prevNode;
            }
        }

        --size;

        return removeNode.data;
    }

    public E getFirst() {
        return head.data;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        if (index == 0) {
            return head.data;
        }

        Node<E> getNode = head;
        for (int i = 0; i < index; i++) {
            getNode = getNode.next;
        }

        return getNode.data;
    }

    public E getLast() {
        return tail.data;
    }

    private static class Node<T> {

        Node<T> next;
        T data;

        Node(T data) {
            next = null;
            this.data = data;
        }
    }

    public static void main(String[] args) {
        SampleSingleLinkedList<Integer> linkedList = new SampleSingleLinkedList<>();

        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);

        System.out.println("Linked List Size : " + linkedList.size);
        System.out.println("Linked List First Data : " + linkedList.getFirst());
        System.out.println("Linked List Data : " + linkedList.get(1));
        System.out.println("Linked List Last Data : " + linkedList.getLast());
    }
}
