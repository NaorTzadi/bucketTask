public class Bucket {
    private int current;
    private int capacity;
    public Bucket(int capacity){
        this.current=0;
        this.capacity=capacity;
    }

    void setCapacity(int capacityAmount){
        capacity=capacityAmount;
    }
    void fill(Bucket other){
        if (isFull()){
            return;
        }
        if (other.current+current>capacity){
            other.spillAmount(capacity-current);
            fill();
            return;
        }else {
            addLiters(other.current);
            other.spill();
        }
        current+=other.current;
    }
    void fill(){
        current=capacity;
    }
    boolean spillAmount(int amount){
        if (amount>current){
            return false;
        }
        current-=amount;
        return true;
    }
    boolean spill(){
        if (isEmpty()){
            return false;
        }
        current=0;
        return true;
    }
    int addLiters(int amount){
        if (isFull()){
            return amount;
        }
        if (current+amount<=capacity){
            current+=amount;
            return 0;
        }
        int leftOver=(current+amount)-capacity;
        fill();
        return leftOver;
    }
    boolean addLiter(){
        if (!isFull()){
            current++;
            return true;
        }else {
            return false;
        }
    }
    float percentage() {
        return ((float) current / capacity) * 100;
    }
    boolean isFull(){
        return current==capacity;
    }
    boolean isEmpty(){
        return current==0;
    }
    @Override
    public String toString() {
        return "The current state of the bucket:"+current+" of "+capacity;
    }
}