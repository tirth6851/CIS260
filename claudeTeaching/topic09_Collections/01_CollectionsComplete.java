package topic09_Collections;

import java.util.*;

/**
 * ============================================================================
 * TOPIC 9: COLLECTIONS FRAMEWORK - Complete Guide
 * ============================================================================
 *
 * The Java Collections Framework provides a unified architecture for
 * representing and manipulating collections of objects.
 *
 * HIERARCHY:
 * Iterable
 * └── Collection
 *     ├── List (ordered, allows duplicates)
 *     │   ├── ArrayList
 *     │   ├── LinkedList
 *     │   └── Vector (legacy)
 *     ├── Set (no duplicates)
 *     │   ├── HashSet (unordered)
 *     │   ├── LinkedHashSet (insertion order)
 *     │   └── TreeSet (sorted)
 *     └── Queue (FIFO)
 *         ├── PriorityQueue
 *         └── LinkedList (also implements Queue)
 *
 * Map (not a Collection, but part of framework)
 * ├── HashMap
 * ├── LinkedHashMap
 * ├── TreeMap
 * └── Hashtable (legacy)
 */

public class CollectionsComplete {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║     TOPIC 9: COLLECTIONS FRAMEWORK                                 ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝\n");

        // ========================================================================
        // SECTION 1: LIST INTERFACE
        // ========================================================================
        System.out.println("--- SECTION 1: List Interface ---");
        System.out.println("Ordered collection, allows duplicates, indexed access\n");

        // ArrayList - Dynamic array, fast random access, slow insertion/deletion
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Apple");
        arrayList.add("Banana");
        arrayList.add("Cherry");
        arrayList.add(1, "Blueberry");  // Insert at index

        System.out.println("ArrayList: " + arrayList);
        System.out.println("Element at index 2: " + arrayList.get(2));
        System.out.println("Size: " + arrayList.size());
        System.out.println("Contains 'Apple'? " + arrayList.contains("Apple"));

        // LinkedList - Doubly linked list, fast insertion/deletion
        List<String> linkedList = new LinkedList<>();
        linkedList.add("First");
        linkedList.add("Second");
        linkedList.add("Third");

        System.out.println("\nLinkedList: " + linkedList);

        // Iterating over lists
        System.out.println("\nIterating with for-each:");
        for (String fruit : arrayList) {
            System.out.print(fruit + " ");
        }
        System.out.println();

        // ========================================================================
        // SECTION 2: SET INTERFACE
        // ========================================================================
        System.out.println("\n--- SECTION 2: Set Interface ---");
        System.out.println("No duplicates, unordered (generally)\n");

        // HashSet - Fast, unordered
        Set<String> hashSet = new HashSet<>();
        hashSet.add("Dog");
        hashSet.add("Cat");
        hashSet.add("Bird");
        hashSet.add("Dog");  // Duplicate - ignored

        System.out.println("HashSet: " + hashSet);
        System.out.println("Size (duplicates not counted): " + hashSet.size());

        // LinkedHashSet - Maintains insertion order
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("Zebra");
        linkedHashSet.add("Apple");
        linkedHashSet.add("Mango");

        System.out.println("\nLinkedHashSet (insertion order): " + linkedHashSet);

        // TreeSet - Sorted order
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("Charlie");
        treeSet.add("Alice");
        treeSet.add("Bob");

        System.out.println("TreeSet (sorted): " + treeSet);

        // ========================================================================
        // SECTION 3: MAP INTERFACE
        // ========================================================================
        System.out.println("\n--- SECTION 3: Map Interface ---");
        System.out.println("Key-value pairs, keys must be unique\n");

        // HashMap - Fast, unordered
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Alice", 25);
        hashMap.put("Bob", 30);
        hashMap.put("Charlie", 35);
        hashMap.put("Alice", 26);  // Updates existing key

        System.out.println("HashMap: " + hashMap);
        System.out.println("Alice's age: " + hashMap.get("Alice"));
        System.out.println("Contains key 'Bob'? " + hashMap.containsKey("Bob"));
        System.out.println("Contains value 30? " + hashMap.containsValue(30));

        // Iterating over map entries
        System.out.println("\nIterating over entries:");
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            System.out.println("  " + entry.getKey() + " = " + entry.getValue());
        }

        // TreeMap - Sorted by key
        Map<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("Zebra", 1);
        treeMap.put("Apple", 2);
        treeMap.put("Mango", 3);

        System.out.println("\nTreeMap (sorted by key): " + treeMap);

        // ========================================================================
        // SECTION 4: QUEUE INTERFACE
        // ========================================================================
        System.out.println("\n--- SECTION 4: Queue Interface ---");
        System.out.println("FIFO - First In, First Out\n");

        // LinkedList as Queue
        Queue<String> queue = new LinkedList<>();
        queue.offer("First");   // Add to tail
        queue.offer("Second");
        queue.offer("Third");

        System.out.println("Queue: " + queue);
        System.out.println("Peek (head): " + queue.peek());  // View head
        System.out.println("Poll (remove head): " + queue.poll());  // Remove and return head
        System.out.println("Queue after poll: " + queue);

        // PriorityQueue - Ordered by priority (natural order or Comparator)
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(30);
        priorityQueue.offer(10);
        priorityQueue.offer(20);

        System.out.println("\nPriorityQueue: " + priorityQueue);
        System.out.println("Poll (highest priority): " + priorityQueue.poll());  // 10
        System.out.println("Poll next: " + priorityQueue.poll());  // 20

        // ========================================================================
        // SECTION 5: STACK (Legacy, but still used)
        // ========================================================================
        System.out.println("\n--- SECTION 5: Stack ---");
        System.out.println("LIFO - Last In, First Out\n");

        Deque<String> stack = new ArrayDeque<>();  // Preferred over Stack class
        stack.push("First");
        stack.push("Second");
        stack.push("Third");

        System.out.println("Stack: " + stack);
        System.out.println("Peek (top): " + stack.peek());
        System.out.println("Pop (remove top): " + stack.pop());
        System.out.println("Stack after pop: " + stack);

        // ========================================================================
        // SECTION 6: UTILITY METHODS
        // ========================================================================
        System.out.println("\n--- SECTION 6: Collections Utility Methods ---");

        List<Integer> numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3));
        System.out.println("Original list: " + numbers);

        // Sort
        Collections.sort(numbers);
        System.out.println("Sorted: " + numbers);

        // Binary search (list must be sorted)
        int index = Collections.binarySearch(numbers, 8);
        System.out.println("Index of 8: " + index);

        // Reverse
        Collections.reverse(numbers);
        System.out.println("Reversed: " + numbers);

        // Shuffle
        Collections.shuffle(numbers);
        System.out.println("Shuffled: " + numbers);

        // Min and Max
        System.out.println("Min: " + Collections.min(numbers));
        System.out.println("Max: " + Collections.max(numbers));

        // Frequency
        numbers.add(5);
        numbers.add(5);
        System.out.println("Frequency of 5: " + Collections.frequency(numbers, 5));

        // ========================================================================
        // SECTION 7: ITERATORS
        // ========================================================================
        System.out.println("\n--- SECTION 7: Iterators ---");

        List<String> fruits = new ArrayList<>(Arrays.asList("Apple", "Banana", "Cherry"));

        // Using Iterator
        Iterator<String> iterator = fruits.iterator();
        System.out.println("Using Iterator:");
        while (iterator.hasNext()) {
            String fruit = iterator.next();
            System.out.print(fruit + " ");
            if (fruit.equals("Banana")) {
                iterator.remove();  // Safe removal during iteration
            }
        }
        System.out.println("\nAfter removal: " + fruits);

        // ListIterator (for Lists only) - can traverse both directions
        ListIterator<String> listIterator = fruits.listIterator();
        System.out.println("\nUsing ListIterator (forward):");
        while (listIterator.hasNext()) {
            System.out.print(listIterator.next() + " ");
        }

        // ========================================================================
        // SECTION 8: COMPARABLE AND COMPARATOR
        // ========================================================================
        System.out.println("\n--- SECTION 8: Sorting with Comparable and Comparator ---");

        List<Student> students = new ArrayList<>();
        students.add(new Student("Charlie", 20));
        students.add(new Student("Alice", 22));
        students.add(new Student("Bob", 19));

        System.out.println("Original: " + students);

        // Sort by name (using Comparable)
        Collections.sort(students);
        System.out.println("By name (Comparable): " + students);

        // Sort by age (using Comparator)
        students.sort(new AgeComparator());
        System.out.println("By age (Comparator): " + students);

        // Lambda expression for sorting (Java 8+)
        students.sort((s1, s2) -> s2.getName().compareTo(s1.getName()));
        System.out.println("By name descending (lambda): " + students);

        // ========================================================================
        // SUMMARY
        // ========================================================================
        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║  COLLECTIONS SUMMARY:                                              ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║  List:    Ordered, allows duplicates, indexed access             ║");
        System.out.println("║  Set:     No duplicates, unordered (use TreeSet for sorted)      ║");
        System.out.println("║  Map:     Key-value pairs, keys unique                           ║");
        System.out.println("║  Queue:   FIFO, use PriorityQueue for priority ordering          ║");
        System.out.println("║  Stack:   LIFO, use Deque (ArrayDeque)                           ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║  WHEN TO USE:                                                    ║");
        System.out.println("║  • ArrayList: Fast random access, few insertions/deletions       ║");
        System.out.println("║  • LinkedList: Frequent insertions/deletions                     ║");
        System.out.println("║  • HashSet/HashMap: Fast lookup, no ordering requirement         ║");
        System.out.println("║  • TreeSet/TreeMap: Sorted order needed                          ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
    }
}

// ============================================================================
// STUDENT CLASS FOR SORTING DEMONSTRATION
// ============================================================================
class Student implements Comparable<Student> {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }

    // Natural ordering - by name
    @Override
    public int compareTo(Student other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return name + "(" + age + ")";
    }
}

// Comparator for sorting by age
class AgeComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return Integer.compare(s1.getAge(), s2.getAge());
    }
}
