import java.util.LinkedList;
import java.util.Queue;

public class stackUsingSingleQueue {
    private Queue<Integer> q;
    private int numOfElements;

    public stackUsingSingleQueue(){
        q = new LinkedList<Integer>();
        numOfElements =0;
    }

    public Integer poll(){
        if (numOfElements>0) {
            numOfElements--;
            return this.q.poll();
        }
        return null;
    }

    public void push(Integer num){
        q.add(num);
        numOfElements++;
            for (int i = 0; i < numOfElements -1; i++) {
                q.add(q.poll());
            }
    }

    public boolean isEmpty(){
        return numOfElements==0;
    }
}
