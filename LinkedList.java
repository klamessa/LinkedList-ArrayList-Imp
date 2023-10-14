// Written hern0492 and lames005
public class LinkedList<T extends Comparable<T>> implements List<T> {
    private boolean isSorted;
    private int length;
    private Node<T> head;
    private Node<T> tail;

    public LinkedList() {
        this.head = new Node<T>(null);// set the head to an empty node and makes is sorted true
        this.isSorted = true;
    }

    @Override
    public boolean add(T element) {
        if (element != null) {// checks if the element is null and if it is it returns false
            Node<T> newNode = new Node<T>(element);
            if (this.head.getData() == null) {// if the head is null then it updates the head to be the element
                this.head = newNode;
                this.tail = this.head;
                length++;
                return true;
            } else {
                tail.setNext(newNode);// if the head is not null it adds it to the back of the list
                tail = tail.getNext();
                length++;
                isSorted = false;
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean add(int index, T element) {
        if (index < length && index >= 0 && element != null) {// checks if the index is between the bounds and if its not null
            Node<T> newNode = new Node<T>(element);
            newNode.setNext(null);
            int count = 0;
            Node temp = head;
            if (index != 0) {// if it is adding it to the start then it just updates the head
                while (count < index - 1) {// goes through the entire array until it reach the index point
                    temp = temp.getNext();
                    count++;
                }
                newNode.setNext(temp.getNext());// once it reaches it updates that array
                temp.setNext(newNode);
                length++;
                isSorted = false;
                return true;
            } else {
                head = new Node(element, head);
                length++;//add length
                return true;

            }
        }
        return false;
    }

    @Override
    public void clear() {
        this.head = new Node<>(null);// head is set to null
        this.tail = this.head;// the end of the list will point to the beginning
        this.length = 0;//resets length
        isSorted = true;// updates is Sorted
    }

    @Override
    public T get(int index) {
        if (0 <= index && index < length) {// checks if index is between the bounds
            Node<T> temp = head;
            for (int i = 0; i < index; i++) {// goes through the list until it reaches the index
                temp = temp.getNext();
            }
            return temp.getData();// returns data of the temp
        }
        return null;
    }

    /**
     * Return the first index of element in the list. If element
     * is null or not found in the list, return -1. If isSorted is
     * true, uses the ordering of the list to increase the efficiency
     * of the search.
     
     */
    @Override
    public int indexOf(T element) {
        Node temp = head;
        for (int i = 0; i < length; i++) {// goes through the entire array
            if (temp.getData() == element) {//checks if the data of each index is equal to element
                return i;// if it is it returns the index i
            } else {
                temp = temp.getNext();// otherwise goes foward in the index
            }
        }
        return -1;// if it cannot find the element it returns  -1
    }

    @Override
    public boolean isEmpty() {
        if (length == 0) {// if the length is equal to 0 then the array is empty
            return true;
        }
        return false;
    }

    /**
     * size() return the number of elements in the list. Be careful
     * not to confuse this for the length of a list like for an ArrayList.
     * For example, if 4 elements are added to a list, size will return
     * 4, while the last index in the list will be 3. Another example
     * is that an ArrayList like [5, 2, 3, null, null] would have a size
     * of 3 for an ArrayList.
     * ArrayList and LinkedList hint: create a class variable in both ArrayList
     * and LinkedList to keep track of the sizes of the respective lists.
     *
     * @return size of the list.
     */
    @Override
    public int size() {
        return length;// returns the length of the array
    }

  
    @Override
    public void sort() {
        if (this.isSorted == false) {// if it already sorted it does not run
            Node temp = head;//used the week four insertion sort to organize
            Node temp2 = head;
            Node temp3 = head;
            for (int i = 0; i < length; i++) {// TA really helped me with this sort method
                temp = temp2;
                temp3 = temp;
                while (temp != null) {
                    if (temp.getData().compareTo(temp3.getData()) < 0) {
                        temp3 = temp;
                    }
                    temp = temp.getNext();
                }
                T data = (T) temp3.getData();
                temp3.setData(temp2.getData());
                temp2.setData(data);
                temp2 = temp2.getNext();
            }
            isSorted = true;
        }
    }
// size and clear next and do sort last;

    /**
     * Remove whatever is at index 'index' in the list and return
     * it. If index is out-of-bounds, return null. For the ArrayList,
     * elements to the right of index should be shifted over to maintain
     * contiguous storage. Must check to see if the list is sorted after removal
     * of the element at the given index and updates isSorted accordingly.
     *
     * @param index position to remove from the list.
     * @return the removed element.
     */
    @Override
    public T remove(int index) {
        if (0 <= index && index < length) {// checks if its between the bounds
            if (index == 0) {// if it's the first one it stores data from head and then moves the head forward once
                Node temp = head;
                head = head.getNext();
                length--;
                isSorted = false;
                return (T) temp.getData();
            } else {
                Node<T> temp = head;
                Node temp2 = temp;
                for (int i = 0; i < length; i++) {// otherwise it will loop through the index and
                    if (i == index) {
                        temp2.setNext(temp.getNext());
                        length--;
                        isSorted = false;
                        return temp.getData();
                    }
                    temp2 = temp;
                    temp = temp.getNext();
                }
            }
        }
        return null;
    }

    /**
     * Removes all elements of the list that are not equal to 'element'. If element is null, don't do anything.
     * When this function returns, the only elements that should be left in this list
     * are equal to 'element'. This method should not change the ordering of the list.
     * If the list is sorted, use this fact to increase the efficiency of this method.
     * This method should be done IN PLACE. Do not use any extra data structures to
     * solve this problem. (You are NOT allowed to create a new array for this function).
     * Updates isSorted accordingly.
     *
     * @param element type of element to be kept in the list.
     */
    @Override
    public void equalTo(T element) {
        int tempSize = 0;
        if (element != null) {
            Node temp = head;
            Node newHead = null;
            while (temp != null) {
                if (temp.getData().compareTo(element) == 0 && tempSize > 0) {
                    newHead.setNext(temp);
                    newHead = newHead.getNext();
                    tempSize++;
                }
                if (temp.getData().compareTo(element) == 0 && tempSize == 0) {
                    this.head = temp;
                    newHead = head;
                    tempSize++;
                }
                temp = temp.getNext();
            }
        }
        this.length = tempSize;
        this.isSorted = true;

    }

    @Override
    public void reverse() {
        if (length >= 2) {
            Node tempo = head;
            T start = (T) tempo.getData();//gets the data from the first head
            Node temp = head.getNext();
            Node prev = null;
            Node after = null;
            while (temp != null) {
                after = temp.getNext();// goes through the entire array except the head and changes them
                // so they are reversed
                temp.setNext(prev);
                prev = temp;
                temp = after;
            }
            head.setNext(prev);
            Node last = head;
            for (int i = 0; i < length - 1; i++) {// goes through the array and makes values go back once in the index
                last.setData(last.getNext().getData());
                last = last.getNext();
            }
            last.setData(start);// sets the last index to the inital head
        }
    }


    @Override
    public void merge(List<T> otherList) {
        if (otherList != null) {
            LinkedList<T> other = (LinkedList<T>) otherList;
            sort();
            other.sort();

            for (int i = 0; i < otherList.size(); i++) {
                add(otherList.get(i));
//                for (int j = 0; j < length; j++) {
//                    if(get(j).compareTo(otherList.get(i))>=0) {
//                        add(j,otherList.get(i));
//                    }
//                    if(get(j).compareTo(otherList.get(i))<0) {
//                        add(j+1,otherList.get(i));
//                    }
//                }
            }
        }
    }

    @Override
    public void pairSwap() {
        Node temp = head;
        int count= 0;
        while (temp != null && temp.getNext() != null) {
            T data = (T) temp.getData();
            System.out.println("Element"+ count + String.valueOf(data));
            count ++;
            System.out.println("Element" + count + String.valueOf(temp.getNext().getData()));
            count ++;
            temp.setData(temp.getNext().getData());
            temp.getNext().setData(data);
            temp = temp.getNext().getNext();
        }

    }

    @Override
    public boolean isSorted() {
        return isSorted;
    }

    public String toString() {
        String result = "";
        Node currNode = head;
        while (currNode != null) {
            result += currNode.getData();
            result += ",";
            currNode = currNode.getNext();
        }
        return result;
    }

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        LinkedList<String> list2 = new LinkedList<>();
        String[] test = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};
        list.add(test[0]);
        list.add(test[2]);
        list.add(test[3]);
        list.add(test[2]);
        list2.add(test[1]);
        list2.add(test[2]);
        list2.add(test[5]);
        list2.add(test[3]);
        System.out.println(list);
        list.equalTo("2");
        System.out.println(list);
        System.out.println(list.length);

        //0,1,2,3
    }
}
