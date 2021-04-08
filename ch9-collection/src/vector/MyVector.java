package vector;

import java.util.*;

public class MyVector<E> {

  protected Object[] elementData;
  protected int elementCount;
  protected int capacityIncrement;
  private static final int MAX_CAPACITY = Integer.MAX_VALUE - 8;
  private static final long serialVersionUID = -2767605614048989439L;

  public MyVector(int initialCapacity, int capacityIncrement) {
    if (initialCapacity < 0)
      throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
    this.elementData = new Object[initialCapacity];
    this.capacityIncrement = capacityIncrement;
  }

  public MyVector(int initialCapacity) {
    this(initialCapacity, 0);
  }

  public MyVector() {
    this(10);
  }



  public MyVector(Collection<? extends E> c) {
    elementData = c.toArray();
    elementCount = elementData.length;
    if (elementData.getClass() != Object[].class)
      elementData = Arrays.copyOf(elementData, elementCount, Object[].class);
  }

  //  同步方法对elementData elementCount加锁
  public synchronized boolean add(E e) {
    add(e, elementData, elementCount);
    return true;
  }

  private void add(E e, Object[] elementData, int s) {
    if (s == elementData.length)
      elementData = grow();
    elementData[s] = e;
    elementCount = s + 1;
  }

  private Object[] grow() {
    return grow(elementCount + 1);
  }

  private Object[] grow(int minCapacity) {
    return Arrays.copyOf(elementData, newCapacity(minCapacity));
  }

  private int newCapacity(int minCapacity) {
    int oldCapacity = elementData.length;
    int newCapacity = oldCapacity + ((capacityIncrement > 0) ? capacityIncrement : oldCapacity);
    if (newCapacity - minCapacity <= 0) {
      if (minCapacity < 0)
        throw new OutOfMemoryError();
      return minCapacity;
    }
    return (newCapacity - MAX_CAPACITY <= 0) ?
        newCapacity : hugeCapacity(minCapacity);
  }

  private static int hugeCapacity(int minCapacity) {
    if (minCapacity < 0)
      throw new IllegalArgumentException();
    return (minCapacity > MAX_CAPACITY) ?
        Integer.MAX_VALUE : MAX_CAPACITY;
  }

  public synchronized E remove(int index) {
    checkElementPosition(index);

    E oldElement = (E) elementData[index];
    int numMoved = elementCount - index - 1;
    System.arraycopy(elementData, index+1, elementData, index,  numMoved);
    elementData[--elementCount] = null;
    return oldElement;
  }

  private void checkElementPosition(int index) {
    if (!isElementPosition(index))
      throw new IllegalArgumentException();
  }

  private boolean isElementPosition(int index) {
    return index >= 0 && index < elementCount;
  }
  public int size() {
    return elementCount;
  }

  public E get(int index) {
    return null;
  }


}
