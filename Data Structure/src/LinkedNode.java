public class LinkedNode {

    private LinkedNode next;
    private Object data;

    public LinkedNode getNext() {
        return next;
    }

    public Object getData() {
        return data;
    }

    public LinkedNode(Object data){
        this.data = data;
        next = null;
    }

    public void setNext(LinkedNode next) {
        this.next = next;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void printList(){
        LinkedNode pointer = this;
        while (pointer !=null) {
            System.out.print(pointer.data.toString()+" ");
            pointer = pointer.next;
        }

    }
}
