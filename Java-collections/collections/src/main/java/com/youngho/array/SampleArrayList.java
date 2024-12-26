package com.youngho.array;

import java.util.Arrays;
import java.util.Objects;

/**
 * 배열로 arrayList 구현하기
 */
public class SampleArrayList {

    /**
     * ArrayList 특징
     * - 연속적인 데이터 리스트(중간에 빈 공간이 존재하면 안된다.)
     * - ArrayList 클래스는 내부적으로 Object[] 배열로 데이터를 저장
     * - 배열을 이용하기 때문에 인덱스로 요소에 빠르게 접근 가능
     * - 크기가 고정되어 있는 배열과 달리 데이터 적재량에 따라 가변적으로 공간을 늘리거나 줄인다.
     * - 배열 공간이 꽉 찰 때마다 copy하는 방식으로 늘리므로 지연이 발생하게 된다.
     * - 데이터를 리스트 중간에 삽입/삭제 하는 경우, 중간에 빈 공간이 생기지 않도록 요소들의 위치를 옮겨야 하기 때문에 삽입/삭제는 느리다.
     * - 조회를 많이 하는 경우에 사용하는 것이 좋다.
     */

    private int current = 0;
    private final static int DEFAULT_SIZE = 5;
    private Object[] arrays;

    public SampleArrayList() {
        arrays = new Object[DEFAULT_SIZE];
    }

    public SampleArrayList(final int size) {
        arrays = new Object[size];
    }

    private boolean isFull() {
        return current == arrays.length;
    }

    public boolean add(Object object) {

        if (isFull()) {
            arrays = Arrays.copyOf(arrays, arrays.length + DEFAULT_SIZE);
        }

        arrays[current++] = object;
        return true;

    }

    public boolean add(int index, Object object) {
        if (index > current || index < 0) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        if (Objects.isNull(object)) {
            throw new IllegalArgumentException("Object cannot be null");
        }

        if (isFull()) {
            arrays = Arrays.copyOf(arrays, arrays.length + DEFAULT_SIZE);
        }

        for (int j = current; j > index; j--) {
            arrays[j] = arrays[j - 1];
        }
        arrays[index] = object;
        current++;
        return true;
    }

    public void addAll(int index, Object[] data) {
        if (index > current || index < 0) {
            throw new IllegalArgumentException("Index is out of bounds");
        }

        if (Objects.isNull(data)) {
            throw new IllegalArgumentException("Object cannot be null");
        }

        if (current + data.length > arrays.length) {
            int newCapacity = Math.max(arrays.length * 2, current + data.length);
            arrays = Arrays.copyOf(arrays, newCapacity);
        }

        System.arraycopy(arrays, index, arrays, index + data.length, current - index);
        System.arraycopy(data, 0, arrays, index, data.length);
        current += data.length;
    }

    public void remove() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("List is Empty");
        }

        if (current <= 0) {
            current = 0;
            throw new IndexOutOfBoundsException("List is Empty");
        }

        arrays[-- current] = null;
    }

    public void remove(int index) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("List is Empty");
        }

        for (int i = index; i < current; i++) {
            arrays[i] = arrays[i + 1];
        }

        arrays[--current] = null;
    }

    public boolean removeAll(Object[] data) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("List is Empty");
        }

        if (Objects.isNull(data)) {
            throw new IllegalArgumentException("Input array cannot be null");
        }

        boolean removedAtLeastOne = false;

        for (Object object : data) {
            if (Objects.nonNull(object)) {
                try {
                    boolean removed = remove(object);
                    if (removed) {
                        removedAtLeastOne = true;
                    }
                } catch (IllegalArgumentException e) {
                    //
                }
            }
        }

        return removedAtLeastOne;
    }

    public boolean remove(Object object) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("List is Empty");
        }

        if (Objects.isNull(object)) {
            throw new IllegalArgumentException("This data is Invalid");
        }

        for (int i = 0; i < current; i++) {
            if (arrays[i].equals(object)) {
                for (int j = i; j < current - 1; j++) {
                    arrays[j] = arrays[j + 1];
                }
                arrays[--current] = null;
                return true;
            }
        }
        throw new IllegalArgumentException("This Object doesn't exists");
    }

    public void clear() {
        arrays = new Object[DEFAULT_SIZE];
        current = 0;
    }

    public boolean isEmpty() {
        return current == 0;
    }

    public boolean contains(Object object) {
        if (Objects.isNull(object)) {
            throw new IllegalArgumentException("This data is Invalid data");
        }

        for (Object obj : arrays) {
            if (Objects.isNull(obj)) {
                continue;
            }

            if (obj.equals(object)) {
                return true;
            }
        }
        return false;
    }

    public int indexOf(Object object) {
        if (Objects.isNull(object)) {
            throw new IllegalArgumentException("This data is Invalid data");
        }

        for (int i = 0; i < current; i++) {
            if (arrays[i].equals(object)) {
                return i;
            }
        }

        return -1;
    }

    public Object get(int index) {
        if (index < 0 || index >= current) {
            throw new IllegalArgumentException("Index Out of Bounds");
        }
        return arrays[index];
    }

    public Object[] subArray(int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("Start Index Invalid");
        }

        if (fromIndex < 0 || toIndex >= current) {
            throw new IllegalArgumentException("Index Out of Bounds");
        }

        return Arrays.copyOfRange(arrays, fromIndex, toIndex);
    }

    public int size() {
        return current;
    }

    public static void main(String[] args) {
        SampleArrayList list = new SampleArrayList();

        System.out.println("현재 리스트의 사이즈 : " + list.size());
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("현재 리스트의 사이즈 : " + list.size());
        list.remove();
        System.out.println("현재 리스트의 사이즈 : " + list.size());
        System.out.println("현재 리스트의 값 : " + Arrays.toString(list.arrays));
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        System.out.println("현재 리스트의 사이즈 : " + list.size());
        System.out.println("현재 리스트의 값 : " + Arrays.toString(list.arrays));
        list.add(0, 0);
        System.out.println("현재 리스트의 값 : " + Arrays.toString(list.arrays));
        list.remove();
        list.remove(0);
        System.out.println("현재 리스트의 값 : " + Arrays.toString(list.arrays));
        System.out.println("값 포함여부 : " + list.contains(5));
        System.out.println("값 포함여부 : " + list.contains(6));
        System.out.println("요소의 값 가져오기 : " + list.get(0));
        list.add("test");
        System.out.println("값의 인덱스 : " + list.indexOf("test"));
        System.out.println("sub Array : " + Arrays.toString(list.subArray(0, 2)));
        System.out.println("요소 값 삭제 : " + list.remove("test"));
        list.clear();
        System.out.println("현재 리스트의 사이즈 : " + list.size());
        list.add("1");
        list.add("2");
        list.add("3");
        list.addAll(3, new Object[]{"p1", "p2", "p3"});
        System.out.println("현재 리스트의 값 : " + Arrays.toString(list.arrays));
        list.removeAll(new Object[]{"p1", "p2", "p3"});
        System.out.println("현재 리스트의 값 : " + Arrays.toString(list.arrays));
    }
}