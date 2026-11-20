# COSC 214 Lab 7: Priority Queues and Binary Heaps

All code is in `Lab7.java`

```
javac Lab7.java
java Lab7
```

---

## Pre-Lab Warm-Up

**Question:** What data structure best models a hospital emergency room?

Priority Queue. Patients are treated by how critical they are, not by when they arrived.

---

## Exercise 1 (Manual)

Given array: `[2, 6, 9, 12, 15]`

**1. Binary tree:**

```
        2
       / \
      6   9
     / \
    12  15
```

**2. Root:** 2

**3. Leaf nodes:** 9, 12, 15

**4. Parent of 12:** 12 is at index 3. Parent index = (3 - 1) / 2 = 1, so the parent is **6**.

---

## Guided Example: Reflection Questions

**1. Why did 3 move up?**
3 was smaller than its parent (5), so it had to move above it.

**2. Which heap rule was violated?**
The min heap property, which says Parent â‰¤ Children.

**3. What operation fixed it?**
Heapify-up. 3 was swapped with its parent until the heap property was restored.

---

## Exercise 2: Insert and Observe

**Output:**

```
[10]            root = 10
[4, 10]         root = 4
[4, 10, 15]     root = 4
[2, 4, 15, 10]  root = 2
```

**1. What is the root after each insertion?**
10, 4, 4, 2

**2. Is the array fully sorted?**
No. For example, 15 comes before 10 in the final array. A heap only guarantees that each parent is â‰¤ its children, so only the root is guaranteed to be the smallest element.

---

## Lab Problem Outputs

| Problem | Input | Output |
|---|---|---|
| 1. Sorted Output | [4, 2, 7, 1] | [1, 2, 4, 7] |
| 2. Find Maximum | [4, 2, 7, 1] | 7 |
| 3. K Smallest (k = 3) | [7, 10, 4, 3, 20, 15] | [3, 4, 7] |
| 4. K Largest (k = 3) | [7, 10, 4, 3, 20, 15] | [20, 15, 10] |
| 5. Kth Largest in Stream (k = 3, nums = [4, 5, 8, 2]) | add 3, 5, 10, 9, 4 | 4, 5, 5, 8, 8 |
| 6. Last Stone Weight | [2, 7, 4, 1, 8, 1] | 1 |

---

## Heap Type Used

| Problem | Heap Type | Why |
| 1. Sorted Output | Min Heap | poll() always returns the smallest, giving ascending order |
| 2. Find Maximum | Max Heap | The root is the largest element |
| 3. K Smallest | Min Heap | Polling K times gives the K smallest |
| 4. K Largest | Min Heap (size K) | The smallest of the K largest sits at the root and gets removed when a bigger value arrives |
| 5. Kth Largest in Stream | Min Heap (size K) | The root is always the Kth largest |
| 6. Last Stone Weight | Max Heap | Need the two heaviest stones each round |

---

## Complexity Answers

| Problem | Complexity |
|---|---|
| Insert | O(log n) |
| Remove | O(log n) |
| Build Heap | O(n) |
| 1. Sorted Output | O(n log n) |
| 2. Find Maximum | O(n log n) to insert all, O(1) to peek |
| 3. K Smallest | O(n log n) |
| 4. K Largest (optimized) | O(n log k) |
| 5. Kth Largest in Stream | O(n log k) to build, O(log k) per add |
| 6. Last Stone Weight | O(n log n) |

---

## Conceptual Checkpoint

**1. Why is heap better than sorting repeatedly?**
Sorting again every time a new element is added costs O(n log n) each time. A heap handles each insert or remove in O(log n) and always keeps the highest priority element at the root, so there is no need to re-sort.

**2. When do we use Min Heap vs Max Heap?**
Use a Min Heap when the smallest value is the highest priority, such as getting ascending order or keeping the K largest with a size-K heap. Use a Max Heap when the largest value is the highest priority, such as finding the maximum or Last Stone Weight.

**3. Why does O(n log k) matter in large datasets?**
When k is much smaller than n, log k is much smaller than log n, so the work is much faster. The heap also only holds k elements, so memory stays small. This makes it practical for huge datasets or data streams that cannot all fit in memory.
