package Main;
import java.util.ArrayList;

public class MaxHeap {
    ArrayList<Event> events = new ArrayList<Event>();
    private int heapSize;
    
    public MaxHeap(){
        this.heapSize = 0;
    }
    
    
    private int getParentIndex(int i){
        return (i-1)/2;
    }
    
    private int getLeftChildIndex(int i){
        return 2*i+1;
    }
    
    private int getRightChildIndex(int i){
        return 2*i + 2;
    }  
    
    
    public void swap(int i, int j){
        Event temp = events.get(i);
        events.set(i,events.get(j));
        events.set(j, temp);
    }
    
    private void heapifyUp(int i){
        Event current = events.get(i);

        int parentIndex = getParentIndex(i);
        if (parentIndex >= 0) {
            Event parent = events.get(parentIndex);

            if (current.priority > parent.priority) {
                swap(i, parentIndex);
                heapifyUp(parentIndex);
            }
        }
    }
    
    private void heapifyDown(int i){
        Event current = events.get(i);
        int childIndex = -1;
        
        int right_childIndex = getRightChildIndex(i);
        int left_childIndex = getLeftChildIndex(i);

        if(right_childIndex < heapSize){
            if(events.get(right_childIndex).priority < events.get(left_childIndex).priority) {
                childIndex = left_childIndex;
            }else{
                childIndex = right_childIndex;
            }
        }else if(left_childIndex < heapSize ){
            childIndex = left_childIndex;    
        }
        if (childIndex != -1 && current.priority < events.get(childIndex).priority) {
                swap(i, childIndex);
                heapifyDown(childIndex);
        }
    }
    
    public void insert(int priority, int lifespan){
        events.add(heapSize,new Event(priority,lifespan));
        heapSize++;
        heapifyUp(heapSize-1);
        
        
    }
    
    public Event pickMax(){
        if(events.isEmpty()){
            return null;
        }
        return events.get(0);
    }
    
    public Event extractMax(){
        if(events.isEmpty()){
            return null;
        }
        Event max = pickMax();
        swap(0,heapSize-1);
        events.remove(heapSize-1);
        heapSize--;
        if(events.isEmpty()){
            return max;
        }
        heapifyDown(0);
        for (int i = 0; i < 10; i++) {
            
        }
        events.forEach((e) -> --e.lifespan);
        removeExpiredEvents();
        return max;
    }
    
    private void removeExpiredEvents() {
    for (int i = heapSize - 1; i >= 0; i--) {
        if (events.get(i).lifespan <= 0) {
            swap(i, heapSize - 1);
            events.remove(heapSize - 1);
            heapSize--;
            
            if (i < heapSize) {
                heapifyDown(i);
                heapifyUp(i);
            }
        }
    }
}
  
    
    public void displayHeap(){
        if(events.isEmpty()){
            System.out.println("heap is empty");;
        }
        events.forEach(e -> System.out.print(e + "  "));
        System.out.println();
    }
    
    
}
