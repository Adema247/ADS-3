import java.util.Random;

public class TestMyHashTable {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>(97);
        Random rand = new Random();

        for (int i = 0; i < 10000; i++) {
            int x = rand.nextInt(10000);
            int y = rand.nextInt(10000);
            MyTestingClass key = new MyTestingClass(x, y);
            Student value = new Student("Student" + i, i);
            table.put(key, value);
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int total = 0;
        int emptyBuckets = 0;

        System.out.println("Elements in buckets:");
        for (int i = 0; i < table.numBuckets(); i++) {
            int count = 0;
            for (var node = table.getChainAt(i); node != null; node = node.next) {
                count++;
            }
            total += count;
            if (count == 0) {
                emptyBuckets++;
            }
            min = Math.min(min, count);
            max = Math.max(max, count);

            System.out.print("Bucket " + i + ": ");
            for (int j = 0; j < count; j++) {
                System.out.print("*");
            }
            System.out.println(" (" + count + ")");
        }

        double average = total * 1.0 / table.numBuckets();

        System.out.println("\n--- Statistics ---");
        System.out.println("Min elements: " + min);
        System.out.println("Max elements: " + max);
        System.out.println("Average elements: " + String.format("%.2f", average));
        System.out.println("Empty buckets: " + emptyBuckets);
        System.out.println("Non-empty buckets: " + (table.numBuckets() - emptyBuckets));
    }
}
