import java.util.Arrays;
//Written By Ketim Lamessa (lames005) Jose Hernandez (hern0492)
public class ArrayList<T extends Comparable<T>> implements List<T> {
    private T[] array;

    private T[] revArray;
    private int size;
    private boolean isSorted;

    public ArrayList() {
        array = (T[]) new Comparable[2];
        size = 0;
        isSorted = true;
    }

    @Override
    public boolean add(T element) {
        if (element == null) {
            return false;
        }
        if (size == array.length) {
            expandArray(array);
        }
        checkSorted();
        array[size] = element;
        size++;
        return true;
    }

    @Override
    public boolean add(int index, T element) {
        if (index < 0 || index >= size || element == null) {
            return false;
        }
        if (size == array.length) {
            expandArray(array);
        }
        if (array[index] != null) {
            for (int i = size; i > index; i--) {
                array[i] = array[i - 1];
            }
        }
        array[index] = element;
        checkSorted();
        size++;
        return true;
    }

    @Override
    public boolean clear() {
        size = 0;
        isSorted = false;
        return true;
    }

    @Override
    public T get(int index) {

        if (index < 0 || index >= size) {
            return null;
        }

        return array[index];
    }

    @Override
    public int indexOf(T element) {
        if (element == null) {       // returns -1 if null
            return -1;
        }
        for (int i = 0; i < size; i++) {
            if (isSorted && array[i] == element) {   // shortcuts if array is already sorted
                return i;
            } else if (array[i] == element) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {   // return size
        return size;
    }


    @Override
    public void sort() { // Selection sort
        if (isSorted == false) {
            for (int i = 0; i < size; i++) {
                if (array[i] != null) {
                    int smallest = i;
                    for (int j = i+1; j < size; j++) {
                        if (array[j] != null) {
                            if (array[smallest].compareTo(array[j]) > 0) {
                                smallest = j;
                            }
                        }
                    }
                    T temp = array[smallest];
                    array[i] = array[smallest];
                    array[smallest] = temp;
                }
            }
            isSorted = true;
        }
    }


    @Override
    public T remove(int index) {

        if (index < 0 || index >= size) { // index is out of bounds
            return null;
        }
        if (index == size - 1) { // index is last in the array
            T temp = array[index];
            array[index] = null;
            size--;
            return temp;
        }
        T temp = array[index];
        for (int i = index; i < size - 1; i++) { // index is in bounds within elements
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;
        isSorted = true;
    return temp;
    }

    @Override   // TODO make this more efficient utilize isSorted
    public void equalTo(T element) {
        if (element == null) {  // return nothing if nul
            return;
        }
        for (int i = 0; i < size; i++) {
            if (!array[i].equals(element)) {   // element is not equal to ideal element "selected"
                remove(i);       // remove function called instead of setting to null
                i--;
             }
        }
        isSorted = true;
    }


    @Override
    public void reverse() {
        T temp;
        for (int i = 0; i < size/2; i++) {     // loops through  until midway
           temp = array[i];
            array[i] = array[size - i - 1];      // array is assigned "mirror" value
            array[size - i - 1] = temp;
        }
    }

    @Override  //TODO merge to lists together
    public void merge(List<T> otherList) {
       int i=0, j=0, k=0;
       T temp;
       int[] mergedArray = (T[]) new Comparable)[array.length + otherList.size()];
       while (i < array.length && j < otherList.size()) {
           if (array[i] < otherList[j])
           {
               mergedArray[k].array[i];
               i++;
               k++;
           }
           else
           {
               mergedArray[k] = otherList[j];
               j++;
               k++;
           }
       }

       while (i < array.length)
       {
           mergedArray[k] = array[i];
           i++;
           k++;
       }

       while (j < array.length)
          mergedArray[k] = otherList[j];
          j++;
          k++;
    }

}


    @Override
    public void pairSwap() {
        T temp;
        if (size % 2 == 0) { // if amount of elements in array is even
            for (int i = 0; i < size; i += 2) { // i increments in terms of 2 AT A TIME
                temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
            }
        }
        if (size % 2 == 1) { // if amount of elements in array is odd
            for (int i = 0; i < size; i += 2) {
                temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
            }
        }
    }

    public String toString() {
        String arrayAsString = "";
        for (int i = 0; i < size; i++) {
            arrayAsString += array[i] + "\n";
        }
        return arrayAsString;
    }


    @Override
    public boolean isSorted() {
        return isSorted;
    }

    private void expandArray(T[] origArray) {
        T[] newArray = (T[]) new Comparable[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    private void checkSorted() {     // checks the status of sorted
        boolean sortedStatus = true;
        for (int i = 0; i < size - 1; i++) {
            if (array[i].compareTo(array[i + 1]) > 0) {
                sortedStatus = false;
                break;
            }
        }
        isSorted = sortedStatus;
    }

  private void printElements(revArray array) {
        for (int i = 0; i < array.size(); i++) {
            System.out.print(array.get(i) + " ");
       }
   }
}
