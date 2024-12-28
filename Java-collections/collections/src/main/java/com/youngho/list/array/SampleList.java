package com.youngho.list.array;

public interface SampleList<T> {

    boolean isFull(); // 가득 찼는 지 체크
    boolean isEmpty(); // 빈 리스트 체크
    /* add */
    boolean add(T obj);
    boolean add(int index, T obj);
    void addAll(int index, T[] data);
    /* remove */
    T remove();
    T remove(int index);
    void removeAll(T[] data);
    boolean remove(T obj);
    /* clear */
    void clear();
    /* contains */
    boolean contains(T obj);
    /* index Of */
    int indexOf(T obj);
    /* get */
    T get(int index);
    /* subArray */
    T[] subArray(int fromIndex, int toIndex);
    /* size */
    int size();
}
