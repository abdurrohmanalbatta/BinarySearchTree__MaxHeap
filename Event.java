
public class Event {
    public int priority;
    public int lifespan;
    
    public Event(int priority, int lifespan){
        this.priority = priority;
        this.lifespan = lifespan;
    }
    
    @Override
    public String toString(){
        return "pri:" + priority + " (L:" + lifespan + ")";
    }
}
