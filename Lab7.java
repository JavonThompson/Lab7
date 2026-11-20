import java.util.*;

/**
 * COSC 214 Lab 7: Priority Queues and Binary Heaps
 */
public class Lab7 {

    // ===================== Exercise 2: Insert and Observe =====================
    public static void exercise2() {
        int[] arr = {10, 4, 15, 2};
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int x : arr) {
            pq.add(x);
            System.out.println(pq + "  root = " + pq.peek());
        }
    }

    // ===================== Problem 1: Sorted Output (Min Heap) =====================
    public static List<Integer> sortedOutput(int[] nums) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int x : nums) {
            minHeap.add(x);
        }

        List<Integer> sorted = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            sorted.add(minHeap.poll()); // poll always removes the smallest
        }
        return sorted;
    }

    // ===================== Problem 2: Find Maximum (Max Heap) =====================
    public static int findMax(int[] nums) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int x : nums) {
            maxHeap.add(x);
        }
        return maxHeap.peek(); // root of a max heap is the largest element
    }

    // ===================== Problem 3: K Smallest Elements =====================
    // Insert all, poll K times. O(n log n)
    public static List<Integer> kSmallest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int x : nums) {
            minHeap.add(x);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k && !minHeap.isEmpty(); i++) {
            result.add(minHeap.poll());
        }
        return result;
    }

    // ===================== Problem 4: K Largest Elements (Optimized) =====================
    // Min heap of size K. O(n log k)
    public static List<Integer> kLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int x : nums) {
            minHeap.add(x);
            if (minHeap.size() > k) {
                minHeap.poll(); // remove the smallest so only the k largest remain
            }
        }
        List<Integer> result = new ArrayList<>(minHeap);
        Collections.sort(result, Collections.reverseOrder());
        return result;
    }

    // ===================== Problem 5: Kth Largest Element in a Stream =====================
    static class KthLargest {
        PriorityQueue<Integer> minHeap;
        int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            minHeap = new PriorityQueue<>();

            for (int num : nums) {
                add(num);
            }
        }

        public int add(int val) {
            minHeap.add(val);
            if (minHeap.size() > k) {
                minHeap.poll(); // keep only the k largest seen so far
            }
            return minHeap.peek(); // smallest of the k largest = kth largest
        }
    }

    // ===================== Problem 6: Last Stone Weight =====================
    public static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int s : stones) {
            maxHeap.add(s);
        }

        while (maxHeap.size() > 1) {
            int y = maxHeap.poll(); // heaviest
            int x = maxHeap.poll(); // second heaviest
            if (y != x) {
                maxHeap.add(y - x);
            }
        }

        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }

    // ===================== Optional Challenge: Kth Smallest in a Stream =====================
    static class KthSmallest {
        PriorityQueue<Integer> maxHeap;
        int k;

        public KthSmallest(int k, int[] nums) {
            this.k = k;
            maxHeap = new PriorityQueue<>(Collections.reverseOrder());

            for (int num : nums) {
                add(num);
            }
        }

        public int add(int val) {
            maxHeap.add(val);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // drop the largest so only the k smallest remain
            }
            return maxHeap.peek(); // largest of the k smallest = kth smallest
        }
    }

    // ===================== Main: run all tests =====================
    public static void main(String[] args) {
        System.out.println("=== Exercise 2: Insert and Observe ===");
        exercise2();

        System.out.println("\n=== Problem 1: Sorted Output ===");
        System.out.println(sortedOutput(new int[]{4, 2, 7, 1}));

        System.out.println("\n=== Problem 2: Find Maximum ===");
        System.out.println(findMax(new int[]{4, 2, 7, 1}));

        int[] data = {7, 10, 4, 3, 20, 15};
        System.out.println("\n=== Problem 3: K Smallest (k = 3) of " + Arrays.toString(data) + " ===");
        System.out.println(kSmallest(data, 3));

        System.out.println("\n=== Problem 4: K Largest (k = 3) of " + Arrays.toString(data) + " ===");
        System.out.println(kLargest(data, 3));

        System.out.println("\n=== Problem 5: Kth Largest in a Stream (k = 3, nums = [4, 5, 8, 2]) ===");
        KthLargest kl = new KthLargest(3, new int[]{4, 5, 8, 2});
        int[] adds = {3, 5, 10, 9, 4};
        for (int v : adds) {
            System.out.println("add(" + v + ") -> " + kl.add(v));
        }

        System.out.println("\n=== Problem 6: Last Stone Weight ===");
        System.out.println("[2, 7, 4, 1, 8, 1] -> " + lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1}));
        System.out.println("[1] -> " + lastStoneWeight(new int[]{1}));


        }
    }
}
