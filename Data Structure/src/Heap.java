public class Heap {
    private int size;
    private int[] heap;
    private int numberOfElements;

    public Heap (int size){
        this.size = size;
        this.heap = new int[size];
        numberOfElements =0;

    }

    public Heap (int[] arr){
        this.heap = arr;
        for (int i = arr.length/2-1; i >= 0; i--) {
            maxHeapify(arr,i);
        }

    }

    public void maxHeapify(int[] arr, int i) {
        int largest = i;
        if (2 * i +2< arr.length && arr[i * 2 +2] != -1) {
            if (arr[2 * i+2] > arr[largest])
                largest = 2 * i +2;
        }
        if ((2 * i + 1) < arr.length && arr[(2 * i + 1)] > arr[largest]) {
            largest = (2 * i + 1);
        }
        if (largest != i) {
            exchange(i, largest);
            maxHeapify(arr,largest);
        }
    }


    public void insert(int a){
        if (numberOfElements < heap.length){
            heap[numberOfElements] = a;
            for (int i =  heap.length/2+1; i >-1; i--) {
                maxHeapify(heap,i);
            }
        }
        numberOfElements++;
    }

    public int extractMax(){
        int a = heap[0];
        heap[0] = heap[heap.length-1];
        heap[heap.length-1] = -1;
        maxHeapify(heap,0);
        numberOfElements--;
        return a;
    }

    public int left (int i){
        return 2*i;
    }

    public int right (int i){
        return ((2*i)+1);
    }

    public void exchange(int i, int j){
        if (i<heap.length && j<heap.length) {
            int a = this.heap[i];
            heap[i] = heap[j];
            heap[j] = a;
        }
    }

    public void printHeap(){
        for (int i = 0; i < heap.length; i++) {
            System.out.println(heap[i]);
        }
    }

    public boolean isEmpty(){
        return (numberOfElements==0);
    }
}
