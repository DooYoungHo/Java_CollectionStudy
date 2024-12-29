package com.youngho.list.linked;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SampleDoublyLinkedList<E> {
    /**
     * node 연결해서 리스트처럼 만든 컬렉션
     * 용량 제한이 없다.
     * 데이터 저장순서가 유지되고 중복을 허용
     * node 가 증가하면 할수록 메모리 사용량이 증가하게 된다.
     */
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public SampleDoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private Node<E> search(int index) {

        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        Node<E> node;

        if ((size / 2) > index) {
            node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = tail;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }

        return node;
    }

    public void addFirst(E e) {
        if (Objects.isNull(e)) {
            throw new IllegalArgumentException("Null element");
        }

        Node<E> newNode = new Node<>(e, head, null);
        if (Objects.nonNull(head)) {
            head.prev = newNode;
        }
        head = newNode;

        if (Objects.isNull(tail)) {
            tail = newNode;
        }

        size++;
    }

    public void addLast(E e) {
        if (Objects.isNull(e)) {
            throw new IllegalArgumentException("Null element");
        }

        Node<E> newNode = new Node<>(e, null, tail);
        if (Objects.nonNull(tail)) {
            tail.next = newNode;
        }
        tail = newNode;

        if (Objects.isNull(head)) {
            head = newNode;
        }

        size++;
    }

    public boolean add(E e) {
        addLast(e);
        return true;
    }

    public void add(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        if (index == 0) {
            addFirst(e);
            return;
        }

        Node<E> nextNode = search(index);
        Node<E> prevNode = nextNode.prev;
        Node<E> newNode = new Node<>(e, nextNode, prevNode);

        prevNode.next = newNode;
        nextNode.prev = newNode;

        size++;
    }

    public E remove() {
        if (Objects.isNull(head)) {
            throw new NoSuchElementException();
        }

        E value = head.item;

        Node<E> nextNode = head.next;

        head.next = null;
        head.item = null;

        head = nextNode;

        if (Objects.isNull(nextNode)) {
            tail = null;
        } else {
            nextNode.prev = null;
        }

        size--;

        return value;
    }

    public E removeLast() {
        if (Objects.isNull(tail)) {
            throw new NoSuchElementException();
        }

        E value = tail.item;
        Node<E> prevNode = tail.prev;

        tail.item = null;
        tail.prev = null;

        tail = prevNode;

        if (Objects.isNull(prevNode)) {
            head = null;
        } else {
            prevNode.next = null;
        }
        size --;

        return value;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        if (index == 0) {
            return remove();
        }

        if (index == size - 1) {
            return removeLast();
        }

        Node<E> deleteNode = search(index);
        Node<E> prevNode = deleteNode.prev;
        Node<E> nextNode = deleteNode.next;

        E value = deleteNode.item;

        deleteNode.prev = null;
        deleteNode.next = null;
        deleteNode.item = null;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;

        size--;

        return value;
    }

    private static class Node<E> {
        private Node<E> next;
        private Node<E> prev;
        private E item;

        public Node(E item, Node<E> next, Node<E> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    public static void main(String[] args) {
        SampleDoublyLinkedList<Integer> list = new SampleDoublyLinkedList<>();

        list.addFirst(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(0);
    }
}
