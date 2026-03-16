package dataStructures;

/**
 * ============================================================================
 * DATA STRUCTURES: LINKED LISTS
 * ============================================================================
 *
 * A linked list is a linear data structure where elements are stored in nodes,
 * and each node points to the next node in the sequence.
 *
 * TYPES:
 * 1. Singly Linked List - Each node points to next
 * 2. Doubly Linked List - Each node points to next and previous
 * 3. Circular Linked List - Last node points back to first
 *
 * TIME COMPLEXITY:
 * - Access: O(n)
 * - Search: O(n)
 * - Insertion at head: O(1)
 * - Insertion at tail: O(1) with tail pointer, O(n) without
 * - Deletion: O(n)
 *
 * SPACE COMPLEXITY: O(n)
 */

public class LinkedListsDS {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║     DATA STRUCTURE: LINKED LISTS                                 ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝\n");

        // ========================================================================
        // SINGLY LINKED LIST
        // ========================================================================
        System.out.println("--- Singly Linked List ---\n");

        SinglyLinkedList<Integer> sll = new SinglyLinkedList<>();
        System.out.println("Adding elements: 10, 20, 30, 40, 50");
        sll.addLast(10);
        sll.addLast(20);
        sll.addLast(30);
        sll.addFirst(5);
        sll.addLast(40);
        sll.addLast(50);

        System.out.println("List: " + sll);
        System.out.println("Size: " + sll.size());
        System.out.println("Head: " + sll.getFirst());
        System.out.println("Tail: " + sll.getLast());

        // Search
        System.out.println("\nSearching for 30: " + sll.contains(30));
        System.out.println("Searching for 100: " + sll.contains(100));

        // Remove
        System.out.println("\nRemoving first: " + sll.removeFirst());
        System.out.println("List after removeFirst: " + sll);

        System.out.println("Removing last: " + sll.removeLast());
        System.out.println("List after removeLast: " + sll);

        System.out.println("Removing 30: " + sll.remove(30));
        System.out.println("List after remove(30): " + sll);

        // ========================================================================
        // DOUBLY LINKED LIST
        // ========================================================================
        System.out.println("\n--- Doubly Linked List ---\n");

        DoublyLinkedList<String> dll = new DoublyLinkedList<>();
        dll.addLast("Apple");
        dll.addLast("Banana");
        dll.addFirst("Cherry");
        dll.addLast("Date");

        System.out.println("List: " + dll);
        System.out.println("Size: " + dll.size());
        System.out.println("Forward traversal: " + dll);
        System.out.println("Reverse traversal: " + dll.toStringReverse());

        // ========================================================================
        // LINKED LIST ALGORITHMS
        // ========================================================================
        System.out.println("\n--- Linked List Algorithms ---\n");

        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        for (int i = 1; i <= 5; i++) {
            list.addLast(i);
        }
        System.out.println("List: " + list);

        // Reverse
        list.reverse();
        System.out.println("Reversed: " + list);

        // Find middle
        list.reverse();  // Back to original
        System.out.println("Middle element: " + list.findMiddle());

        // Check if palindrome
        SinglyLinkedList<Integer> palindrome = new SinglyLinkedList<>();
        palindrome.addLast(1);
        palindrome.addLast(2);
        palindrome.addLast(3);
        palindrome.addLast(2);
        palindrome.addLast(1);
        System.out.println("\nPalindrome list: " + palindrome);
        System.out.println("Is palindrome: " + palindrome.isPalindrome());

        // ========================================================================
        // COMPARISON WITH ARRAY
        // ========================================================================
        System.out.println("\n--- Linked List vs Array ---\n");

        System.out.println("Linked List:");
        System.out.println("  Pros: Dynamic size, efficient insertion/deletion at ends");
        System.out.println("  Cons: No random access, extra memory for pointers");
        System.out.println("  Use when: Frequent insertions/deletions, unknown size");

        System.out.println("\nArray:");
        System.out.println("  Pros: O(1) access, cache friendly, less memory overhead");
        System.out.println("  Cons: Fixed size, expensive insertion/deletion");
        System.out.println("  Use when: Random access needed, size known");

        // ========================================================================
        // SUMMARY
        // ========================================================================
        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║  LINKED LISTS SUMMARY:                                           ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║  Singly Linked List:                                             ║");
        System.out.println("║    • Each node: data + next pointer                              ║");
        System.out.println("║    • Memory efficient, simple traversal                          ║");
        System.out.println("║                                                                  ║");
        System.out.println("║  Doubly Linked List:                                             ║");
        System.out.println("║    • Each node: data + next + prev pointers                      ║");
        System.out.println("║    • Bidirectional traversal, easier deletion                    ║");
        System.out.println("║                                                                  ║");
        System.out.println("║  Time Complexity:                                                ║");
        System.out.println("║    Access:  O(n)                                                 ║");
        System.out.println("║    Search:  O(n)                                                 ║");
        System.out.println("║    Insert:  O(1) at ends, O(n) at position                       ║");
        System.out.println("║    Delete:  O(1) at ends, O(n) at position                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
    }
}

// ============================================================================
// SINGLY LINKED LIST IMPLEMENTATION
// ============================================================================
class SinglyLinkedList<T> {
    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Add to beginning - O(1)
    public void addFirst(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    // Add to end - O(1)
    public void addLast(T data) {
        Node newNode = new Node(data);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    // Remove from beginning - O(1)
    public T removeFirst() {
        if (head == null) throw new RuntimeException("List is empty");
        T data = head.data;
        head = head.next;
        if (head == null) tail = null;
        size--;
        return data;
    }

    // Remove from end - O(n)
    public T removeLast() {
        if (head == null) throw new RuntimeException("List is empty");
        if (head == tail) return removeFirst();

        Node current = head;
        while (current.next != tail) {
            current = current.next;
        }
        T data = tail.data;
        current.next = null;
        tail = current;
        size--;
        return data;
    }

    // Remove specific element - O(n)
    public boolean remove(T data) {
        if (head == null) return false;
        if (head.data.equals(data)) {
            removeFirst();
            return true;
        }

        Node current = head;
        while (current.next != null && !current.next.data.equals(data)) {
            current = current.next;
        }

        if (current.next != null) {
            if (current.next == tail) tail = current;
            current.next = current.next.next;
            size--;
            return true;
        }
        return false;
    }

    // Search - O(n)
    public boolean contains(T data) {
        Node current = head;
        while (current != null) {
            if (current.data.equals(data)) return true;
            current = current.next;
        }
        return false;
    }

    // Get first - O(1)
    public T getFirst() {
        if (head == null) throw new RuntimeException("List is empty");
        return head.data;
    }

    // Get last - O(1)
    public T getLast() {
        if (tail == null) throw new RuntimeException("List is empty");
        return tail.data;
    }

    // Get size - O(1)
    public int size() {
        return size;
    }

    // Reverse list - O(n)
    public void reverse() {
        Node prev = null;
        Node current = head;
        Node next = null;
        tail = head;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    // Find middle - O(n)
    public T findMiddle() {
        if (head == null) return null;

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.data;
    }

    // Check if palindrome - O(n)
    public boolean isPalindrome() {
        if (head == null || head.next == null) return true;

        // Find middle
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse second half
        Node secondHalf = reverseList(slow.next);
        Node firstHalf = head;

        // Compare
        Node temp = secondHalf;
        boolean result = true;
        while (temp != null) {
            if (!firstHalf.data.equals(temp.data)) {
                result = false;
                break;
            }
            firstHalf = firstHalf.next;
            temp = temp.next;
        }

        // Restore list
        slow.next = reverseList(secondHalf);
        return result;
    }

    private Node reverseList(Node head) {
        Node prev = null, current = head, next;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) sb.append(" -> ");
            current = current.next;
        }
        return sb.append("]").toString();
    }
}

// ============================================================================
// DOUBLY LINKED LIST IMPLEMENTATION
// ============================================================================
class DoublyLinkedList<T> {
    private class Node {
        T data;
        Node next;
        Node prev;

        Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void addFirst(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void addLast(T data) {
        Node newNode = new Node(data);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public T removeFirst() {
        if (head == null) throw new RuntimeException("List is empty");
        T data = head.data;
        head = head.next;
        if (head != null) head.prev = null;
        else tail = null;
        size--;
        return data;
    }

    public T removeLast() {
        if (tail == null) throw new RuntimeException("List is empty");
        T data = tail.data;
        tail = tail.prev;
        if (tail != null) tail.next = null;
        else head = null;
        size--;
        return data;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) sb.append(" <-> ");
            current = current.next;
        }
        return sb.append("]").toString();
    }

    public String toStringReverse() {
        StringBuilder sb = new StringBuilder("[");
        Node current = tail;
        while (current != null) {
            sb.append(current.data);
            if (current.prev != null) sb.append(" <-> ");
            current = current.prev;
        }
        return sb.append("]").toString();
    }
}
