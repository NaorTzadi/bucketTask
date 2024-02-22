import java.util.*;
public class Main {
    public static void main(String[] args) {
        Bucket[] buckets=new Bucket[5];
        buckets[0]=new Bucket(4);
        buckets[0].addLiters(2);
        buckets[1]=new Bucket(5);
        buckets[1].addLiters(3);
        buckets[2]=new Bucket(6);
        buckets[2].addLiters(3);
        buckets[3]=new Bucket(7);
        buckets[3].addLiters(4);
        buckets[4]=new Bucket(8);
        buckets[4].addLiters(4);
        int[] indexes=fillExactAmount(buckets,16);
        for (int i=0;i<indexes.length;i++){
            System.out.println(indexes[i]);
        }
        reduceAmountInBuckets(buckets,50);
        magic();
        playBucketsGame();
    }
    static void magic() {
        Bucket b1 = new Bucket(7);
        Bucket b2 = new Bucket(5);
        b1.fill();
        b2.fill(b1);
        b2.spill();
        b2.fill(b1);
        b1.fill();
        b2.fill(b1);
        b2.spill();
        b2.fill(b1);
        b1.fill();
        b2.fill(b1);
        System.out.println(b1);
    }
    static Bucket reduceAmountInBuckets (Bucket[] buckets, float max){
        for(int i=0;i<buckets.length;i++){
            if (buckets[i].percentage()>=max){
                System.out.println("before: "+buckets[i].percentage());
                new Bucket(Integer.parseInt(buckets[i].toString().split(":")[1].trim().split(" ")[2])).fill(buckets[i]);
                System.out.println("after: "+buckets[i].percentage());
            }
        }
        return null;
    }
    static int[] fillExactAmount(Bucket[] buckets, int amount){
        int[] indices = new int[buckets.length];
        for (int i = 0; i < indices.length; i++) {
            indices[i] = -1;
        }
        if (hasSubsetSum(buckets, amount, 0, 0, indices, 0)) {
            int validCount = 0;
            for (int index : indices) {
                if (index != -1) {
                    validCount++;
                }
            }
            int[] result = new int[validCount];
            int resultIndex = 0;
            for (int index : indices) {
                if (index != -1) {
                    result[resultIndex++] = index;
                }
            }
            return result;
        }
        return new int[0];
    }
    static boolean hasSubsetSum(Bucket[] buckets, int targetAmount, int currentSum, int index, int[] indices, int indicesCount) {
        if (currentSum == targetAmount) {
            return true;
        }
        if (currentSum > targetAmount || index >= buckets.length) {
            return false;
        }
        if (buckets[index].percentage() < 50) {
            return hasSubsetSum(buckets, targetAmount, currentSum, index + 1, indices, indicesCount);
        }
        indices[indicesCount] = index;
        if (hasSubsetSum(buckets, targetAmount, currentSum + Integer.parseInt(buckets[index].toString().split(":")[1].trim().split(" ")[0]), index + 1, indices, indicesCount + 1)) {
            return true;
        }
        indices[indicesCount] = -1;
        return hasSubsetSum(buckets, targetAmount, currentSum, index + 1, indices, indicesCount);
    }
    static void playBucketsGame(){
        /** cant use:
         * addLiter
         * fill(Bucket other)
         * percentage
         * spill */
        Scanner scanner=new Scanner(System.in);
        Random random=new Random();
        Bucket bucket1=new Bucket(1+random.nextInt(20));
        bucket1.addLiters(1+random.nextInt(20));
        Bucket bucket2=new Bucket(1+random.nextInt(20));
        bucket2.addLiters(1+random.nextInt(20));
        Bucket bucket3=new Bucket(1+random.nextInt(20));
        bucket3.addLiters(1+random.nextInt(20));
        Bucket specialBucket=new Bucket(1+random.nextInt(20));
        int decision;
        int counter=1;
        while (true){
            System.out.println("bucket1: "+bucket1);
            System.out.println("bucket2: "+bucket2);
            System.out.println("bucket3: "+bucket3);
            System.out.println("special bucket: "+specialBucket);

            System.out.println("make your move:");
            System.out.println("press 1 to fill special bucket with bucket1");
            System.out.println("press 2 to fill special bucket with bucket2");
            System.out.println("press 3 to fill special bucket with bucket3");

            System.out.println("press 4 to fill bucket1 with bucket2");
            System.out.println("press 5 to fill bucket1 with bucket3");
            System.out.println("press 6 to fill bucket2 with bucket1");
            System.out.println("press 7 to fill bucket2 with bucket3");
            System.out.println("press 8 to fill bucket3 with bucket1");
            System.out.println("press 9 to fill bucket3 with bucket2");

            decision=scanner.nextInt();
            if (decision==1){
                specialBucket.fill(bucket1);
            }else if (decision==2){
                specialBucket.fill(bucket2);
            }else if (decision==3){
                specialBucket.fill(bucket3);
            }else if (decision==4){
                bucket1.fill(bucket2);
            }else if (decision==5){
                bucket1.fill(bucket3);
            }else if (decision==6){
                bucket2.fill(bucket1);
            }else if (decision==7){
                bucket2.fill(bucket3);
            }else if (decision==8){
                bucket3.fill(bucket1);
            }else if (decision==9){
                bucket3.fill(bucket2);
            }
            if (specialBucket.isFull()){
                System.out.println("GAME OVER");
                System.out.println("special bucket is full after "+counter+" moves!");
                break;
            }else {
                counter++;
            }
        }

    }
}
