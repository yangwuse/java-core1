package arraylist;

// add index range : [0, size]
// remove index range : [0, size)
public class MyArrayList<E> {

  private E[] elems;
  private int size;

  public MyArrayList(int cap) {
    elems = (E[]) new Object[cap];
  }

  public MyArrayList() {
    elems = (E[]) new Object[2];
  }

  public void addLast(E e) {
    if (size == elems.length) resize(size << 1);
    elems[size++] = e;
  }

  public void addAtIndex(int i, E e) {
    checkAddIndex(i);
    if (size == elems.length)
      resize(size << 1);

    if (i < size)
      shiftR(i);
    elems[i] = e;
    size++;
  }

  public E removeLast() {
    checkRemoveIndex(size - 1);
    E e = elems[--size];
    elems[size] = null;
    if (size == (elems.length << 2))
      resize(size << 2);
    return e;
  }

  public E removeAtIndex(int i) {
    checkRemoveIndex(i);
    E elem = elems[i];
    if (i < size - 1)
      shiftL(i);
    elems[--size] = null;
    if (size == (elems.length << 2))
      resize(size << 2);
    return elem;
  }

  public void resize(int newSize) {
    E[] newElems = (E[]) new Object[newSize];
    for (int i = 0; i < size; i++)
      newElems[i] = elems[i];
    elems = newElems;
  }

  public void shiftR(int end) {
    for (int i = size; i > end; i--)
      elems[i] = elems[i-1];
  }

  public void shiftL(int begin) {
    for (int i = begin; i < size - 1; i++)
      elems[i] = elems[i+1];
  }

  private void checkAddIndex(int index) {
    if (index < 0 || index > size)
      throw new IndexOutOfBoundsException("Add index must be in [0, size]");
  }

  private void checkRemoveIndex(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("Remove index must be in [0, size)");
  }

  public String toString() {
    StringBuilder sbuilder = new StringBuilder();
    sbuilder.append("[");
    for (int i = 0; i < size; i++) {
      if (i == size - 1)
        sbuilder.append(elems[i] + "]");
      else
        sbuilder.append(elems[i] + ", ");
    }

    if (size == 0)
      sbuilder.append("]");

    return sbuilder.toString();
  }

  public static void main(String[] args) {
//    addLastTest();
//    addAtIndexTest();
//    removeLastTest();
//    removeAtIndexTest();
  }

  public static void addLastTest() {
    MyArrayList<Integer> arrList = new MyArrayList();
    arrList.addLast(1);
    arrList.addLast(2);
    arrList.addLast(3);
    System.out.println(arrList);
  }

  public static void addAtIndexTest() {
    MyArrayList<Integer> arrList = new MyArrayList<>();
    arrList.addAtIndex(0, 1);
    arrList.addAtIndex(1, 2);
    arrList.addAtIndex(2, 3);
    System.out.println(arrList);
  }

  public static void removeLastTest() {
    MyArrayList<Integer> arrList = new MyArrayList<>();
    arrList.addLast(1);
    arrList.addLast(2);
    arrList.removeLast();
    arrList.removeLast();
    System.out.println(arrList);
//    arrList.removeLast();

  }

  public static void removeAtIndexTest() {
    MyArrayList<Integer> arrList = new MyArrayList<>();
    arrList.addLast(1);
    arrList.addLast(2);
    arrList.removeAtIndex(1);
    System.out.println(arrList);
    arrList.removeAtIndex(0);
    System.out.println(arrList);
  }

}
