package dataStructures;

import java.util.EmptyStackException;

/**
 * ============================================================================
 * DATA STRUCTURES: STACKS AND QUEUES
 * ============================================================================
 *
 * STACK (LIFO - Last In, First Out)
 * - Operations: push, pop, peek
 * - Applications: Function calls, undo operations, expression evaluation
 * - Time Complexity: All O(1)
 *
 * QUEUE (FIFO - First In, First Out)
 * - Operations: enqueue, dequeue, peek
 * - Applications: BFS, task scheduling, print queues
 * - Time Complexity: All O(1)
 *
 * DEQUE (Double Ended Queue)
 * - Operations at both ends: addFirst, addLast, removeFirst, removeLast
 * - Can be used as both stack and queue
 */

public class StacksAndQueuesDS {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║     DATA STRUCTURES: STACKS AND QUEUES                           ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝\n");

        // ========================================================================
        // STACK
        // ========================================================================
        System.out.println("--- Stack (LIFO) ---\n");

        Stack<Integer> stack = new Stack<>();
        System.out.println("Pushing: 10, 20, 30, 40, 50");
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);

        System.out.println("Stack: " + stack);
        System.out.println("Peek: " + stack.peek());
        System.out.println("Size: " + stack.size());

        System.out.println("\nPopping elements:");
        while (!stack.isEmpty()) {
            System.out.println("  Popped: " + stack.pop());
        }

        // ========================================================================
        // QUEUE
        // ========================================================================
        System.out.println("\n--- Queue (FIFO) ---\n");

        Queue<String> queue = new Queue<>();
        System.out.println("Enqueuing: Alice, Bob, Charlie, Diana");
        queue.enqueue("Alice");
        queue.enqueue("Bob");
        queue.enqueue("Charlie");
        queue.enqueue("Diana");

        System.out.println("Queue: " + queue);
        System.out.println("Peek: " + queue.peek());
        System.out.println("Size: " + queue.size());

        System.out.println("\nDequeuing elements:");
        while (!queue.isEmpty()) {
            System.out.println("  Dequeued: " + queue.dequeue());
        }

        // ========================================================================
        // DEQUE
        // ========================================================================
        System.out.println("\n--- Deque (Double Ended Queue) ---\n");

        Deque<Integer> deque = new Deque<>();
        deque.addFirst(10);
        deque.addLast(20);
        deque.addFirst(5);
        deque.addLast(30);

        System.out.println("Deque: " + deque);
        System.out.println("Remove first: " + deque.removeFirst());
        System.out.println("Remove last: " + deque.removeLast());
        System.out.println("Deque after removals: " + deque);

        // ========================================================================
        // APPLICATIONS
        // ========================================================================
        System.out.println("\n--- Applications ---\n");

        // 1. Balanced Parentheses
        System.out.println("1. Balanced Parentheses Check:");
        String[] expressions = {"()", "({[]})", "({[}])", "((())", "}{"};
        for (String expr : expressions) {
            System.out.println("  \"" + expr + "\" is " +
                (isBalanced(expr) ? "balanced" : "not balanced"));
        }

        // 2. Reverse String
        System.out.println("\n2. Reverse String using Stack:");
        String original = "Hello World";
        String reversed = reverseString(original);
        System.out.println("  Original: " + original);
        System.out.println("  Reversed: " + reversed);

        // 3. Infix to Postfix
        System.out.println("\n3. Infix to Postfix Conversion:");
        String infix = "A+B*C-D/E";
        String postfix = infixToPostfix(infix);
        System.out.println("  Infix:   " + infix);
        System.out.println("  Postfix: " + postfix);

        // ========================================================================
        // SUMMARY
        // ========================================================================
        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║  STACKS AND QUEUES SUMMARY:                                      ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║  Stack (LIFO):                                                   ║");
        System.out.println("║    • push(item) - Add to top                                     ║");
        System.out.println("║    • pop() - Remove from top                                     ║");
        System.out.println("║    • peek() - View top                                           ║");
        System.out.println("║    • Applications: Function calls, undo, parsing               ║");
        System.out.println("║                                                                  ║");
        System.out.println("║  Queue (FIFO):                                                   ║");
        System.out.println("║    • enqueue(item) - Add to rear                                 ║");
        System.out.println("║    • dequeue() - Remove from front                               ║");
        System.out.println("║    • peek() - View front                                         ║");
        System.out.println("║    • Applications: BFS, scheduling, buffering                 ║");
        System.out.println("║                                                                  ║");
        System.out.println("║  Deque:                                                          ║");
        System.out.println("║    • Operations at both ends                                     ║");
        System.out.println("║    • Can implement both stack and queue                        ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║  Time Complexity: All O(1)                                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
    }

    // Balanced parentheses check
    public static boolean isBalanced(String expression) {
        Stack<Character> stack = new Stack<>();
        for (char c : expression.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) return false;
                char open = stack.pop();
                if ((c == ')' && open != '(') ||
                    (c == '}' && open != '{') ||
                    (c == ']' && open != '[')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    // Reverse string using stack
    public static String reverseString(String str) {
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            stack.push(c);
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.toString();
    }

    // Infix to postfix conversion
    public static String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char c : infix.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                postfix.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                stack.pop();  // Remove '('
            } else {  // Operator
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    postfix.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }

        return postfix.toString();
    }

    private static int precedence(char op) {
        switch (op) {
            case '+':
            case '-': return 1;
            case '*':
            case '/': return 2;
            case '^': return 3;
        }
        return -1;
    }
}

// ============================================================================
// STACK IMPLEMENTATION (using array)
// ============================================================================
class Stack<T> {
    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public Stack() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public void push(T item) {
        ensureCapacity();
        elements[size++] = item;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) throw new EmptyStackException();
        T item = (T) elements[--size];
        elements[size] = null;  // Prevent memory leak
        return item;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) throw new EmptyStackException();
        return (T) elements[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            elements = java.util.Arrays.copyOf(elements, elements.length * 2);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) sb.append(", ");
        }
        return sb.append("]").toString();
    }
}

// ============================================================================
// QUEUE IMPLEMENTATION (using circular array)
// ============================================================================
class Queue<T> {
    private Object[] elements;
    private int front;
    private int rear;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public Queue() {
        elements = new Object[DEFAULT_CAPACITY];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void enqueue(T item) {
        ensureCapacity();
        rear = (rear + 1) % elements.length;
        elements[rear] = item;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        T item = (T) elements[front];
        elements[front] = null;
        front = (front + 1) % elements.length;
        size--;
        return item;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        return (T) elements[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            resize();
        }
    }

    private void resize() {
        Object[] newElements = new Object[elements.length * 2];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[(front + i) % elements.length];
        }
        elements = newElements;
        front = 0;
        rear = size - 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[(front + i) % elements.length]);
            if (i < size - 1) sb.append(", ");
        }
        return sb.append("]").toString();
    }
}

// ============================================================================
// DEQUE IMPLEMENTATION
// ============================================================================
class Deque<T> {
    private Node<T> front;
    private Node<T> rear;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T data) {
            this.data = data;
        }
    }

    public void addFirst(T item) {
        Node<T> newNode = new Node<>(item);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            newNode.next = front;
            front.prev = newNode;
            front = newNode;
        }
        size++;
    }

    public void addLast(T item) {
        Node<T> newNode = new Node<>(item);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            newNode.prev = rear;
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    public T removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        T data = front.data;
        front = front.next;
        if (front != null) front.prev = null;
        else rear = null;
        size--;
        return data;
    }

    public T removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        T data = rear.data;
        rear = rear.prev;
        if (rear != null) rear.next = null;
        else front = null;
        size--;
        return data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = front;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) sb.append(", ");
            current = current.next;
        }
        return sb.append("]").toString();
    }
}
