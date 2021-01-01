public class stack {
    private LinkedList list;
    private boolean isEmpty;

    public stack(){
        this.list = new LinkedList();
        isEmpty = this.list.isEmpty();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
    public void push(Object data){
        list.inserFirst(data);
    }

    public Object pop (){
        return list.removeFirst();
    }

    public void printStack(){
        list.printList();
    }
}
