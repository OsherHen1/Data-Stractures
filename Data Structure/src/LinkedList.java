public class LinkedList {
    private LinkedNode head;
    private LinkedNode tail;
    private boolean isEmpty;

    public LinkedList(){
        isEmpty = true;

    }
    public LinkedList(LinkedNode head1){
        this.head = head1;
    }

    public boolean isEmpty(){
        return isEmpty;
    }

    public void insertLast (Object data){
        LinkedNode newLast = new LinkedNode(data);
        if (isEmpty){
            head = newLast;
            tail = newLast;
            isEmpty = false;
            return;
        }
        tail.setNext(newLast);
        tail = newLast;
    }

    public void inserFirst (Object data){
        LinkedNode newFirst = new LinkedNode(data);
        if (isEmpty){
            head = newFirst;
            tail = newFirst;
            isEmpty = false;
            return;
        }

        newFirst.setNext(this.head);
        this.head = newFirst;
    }

    public Object removeFirst() {
        LinkedNode pointer = head;
        if (!isEmpty) {
            if (head.getNext() == null) {
                head = null;
                tail = null;
                return pointer;
            }
            head = pointer.getNext();
            pointer.setNext(null);
            return pointer;
        }
        else{
            return null;
        }
    }



    public LinkedNode removeLast(){
        LinkedNode pointer = head;
        if (!isEmpty) {
            if (head.getNext()==null){
                head = null;
                tail = null;
                return pointer;
            }
            while (!pointer.getNext().equals(tail))
                pointer = pointer.getNext();
            LinkedNode returnPointer = tail;
            tail = pointer;
            tail.setNext(null);
            return returnPointer;
        } else{
            return null;
        }
    }

    public void printList (){
        LinkedNode pointer = this.head;
        while (pointer!= null){
            System.out.println(pointer.getData());
            pointer = pointer .getNext();
        }
    }

    public LinkedNode getHead() {
        return head;
    }
}
