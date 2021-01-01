import java.util.Queue;
import java.util.Stack;

public class queueUsingStacks {
    public Stack<Integer> q1;
    public Stack<Integer> q2;
    public boolean isEmpty;

    public queueUsingStacks(){
        q1 = new Stack<>();
        q2 = new Stack<>();
        isEmpty = true;
    }

    public void enqueue(Integer i ){
        while (!q2.isEmpty())
            q1.push(q2.pop());
        q1.push(i);
        while (!q1.isEmpty())
            q2.push(q1.pop());

    }
    public Integer dequeue() {
        Integer toReturn = null;

        if (!q2.isEmpty()) {
            toReturn = q2.pop();
            if (q2.isEmpty()) {
                isEmpty = true;
            }
        }
        return toReturn;
    }

    public boolean isEmpty(){
        return isEmpty;
    }
}
