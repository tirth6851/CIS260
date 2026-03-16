package topic08_Generics;

import java.util.*;

/**
 * ============================================================================
 * TOPIC 8: GENERICS - Complete Guide
 * ============================================================================
 *
 * Generics enable types (classes and interfaces) to be parameters when defining
 * classes, interfaces, and methods. They provide compile-time type safety.
 *
 * BENEFITS:
 * 1. Type Safety: Catch type errors at compile time, not runtime
 * 2. Eliminate Casting: No need to cast when retrieving elements
 * 3. Code Reusability: Write once, use with different types
 *
 * SYNTAX:
 * - Generic class: class Box<T> { }
 * - Generic method: <T> void method(T item) { }
 * - Bounded type: <T extends Number>
 * - Wildcard: ?, ? extends T, ? super T
 */

// ============================================================================
// GENERIC CLASS
// ============================================================================
// T is a type parameter (stands for "Type")
// Can use any identifier: T, E, K, V, N, etc.
class Box<T> {
    // T will be replaced with actual type when used
    private T content;

    public void setContent(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public void displayType() {
        System.out.println("Type of T: " + content.getClass().getSimpleName());
    }
}

// ============================================================================
// GENERIC CLASS WITH MULTIPLE TYPE PARAMETERS
// ============================================================================
// K = Key, V = Value (common convention)
class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() { return key; }
    public V getValue() { return value; }

    public void setKey(K key) { this.key = key; }
    public void setValue(V value) { this.value = value; }

    @Override
    public String toString() {
        return "Pair{" + key + "=" + value + "}";
    }
}

// ============================================================================
// BOUNDED TYPE PARAMETER
// ============================================================================
// T must be a subclass of Number (or Number itself)
class NumberBox<T extends Number> {
    private T number;

    public NumberBox(T number) {
        this.number = number;
    }

    public double doubleValue() {
        // Can call Number methods because T extends Number
        return number.doubleValue();
    }

    public boolean isInteger() {
        return number instanceof Integer;
    }
}

// ============================================================================
// GENERIC METHOD
// ============================================================================
class GenericMethods {
    // Generic method with type parameter T
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    // Generic method returning T
    public static <T> T getFirst(T[] array) {
        return array.length > 0 ? array[0] : null;
    }

    // Bounded generic method
    public static <T extends Comparable<T>> T findMax(T a, T b) {
        return a.compareTo(b) > 0 ? a : b;
    }

    // Generic method with multiple bounds
    public static <T extends Number & Comparable<T>> boolean isGreaterThan(T a, T b) {
        return a.compareTo(b) > 0;
    }
}

// ============================================================================
// WILDCARD EXAMPLES
// ============================================================================
class WildcardExamples {
    // Unbounded wildcard - accepts any type
    public static void printList(List<?> list) {
        for (Object elem : list) {
            System.out.print(elem + " ");
        }
        System.out.println();
    }

    // Upper bounded wildcard - accepts T or subclasses of T
    public static double sumOfList(List<? extends Number> list) {
        double sum = 0.0;
        for (Number n : list) {
            sum += n.doubleValue();
        }
        return sum;
    }

    // Lower bounded wildcard - accepts T or superclasses of T
    public static void addNumbers(List<? super Integer> list) {
        for (int i = 1; i <= 5; i++) {
            list.add(i);  // Can add Integer or its subclasses
        }
    }
}

// ============================================================================
// MAIN CLASS
// ============================================================================
public class GenericsComplete {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║     TOPIC 8: GENERICS - Complete Guide                           ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝\n");

        // ========================================================================
        // SECTION 1: GENERIC CLASS
        // ========================================================================
        System.out.println("--- SECTION 1: Generic Class ---");
        System.out.println("Type parameter T is replaced with actual type\n");

        // Box of Strings
        Box<String> stringBox = new Box<>();  // Diamond operator <> infers type
        stringBox.setContent("Hello Generics!");
        System.out.println("String box content: " + stringBox.getContent());
        stringBox.displayType();

        // Box of Integers
        Box<Integer> intBox = new Box<>();
        intBox.setContent(42);
        System.out.println("\nInteger box content: " + intBox.getContent());
        intBox.displayType();

        // ========================================================================
        // SECTION 2: MULTIPLE TYPE PARAMETERS
        // ========================================================================
        System.out.println("\n--- SECTION 2: Multiple Type Parameters ---");

        Pair<String, Integer> person = new Pair<>("Alice", 25);
        System.out.println("Person: " + person);
        System.out.println("Name: " + person.getKey() + ", Age: " + person.getValue());

        Pair<Integer, Double> coordinates = new Pair<>(10, 20.5);
        System.out.println("\nCoordinates: " + coordinates);

        // ========================================================================
        // SECTION 3: BOUNDED TYPES
        // ========================================================================
        System.out.println("\n--- SECTION 3: Bounded Types ---");
        System.out.println("Restrict type parameters to specific types or subtypes\n");

        NumberBox<Integer> intNumberBox = new NumberBox<>(100);
        NumberBox<Double> doubleNumberBox = new NumberBox<>(3.14);

        System.out.println("Integer as double: " + intNumberBox.doubleValue());
        System.out.println("Double as double: " + doubleNumberBox.doubleValue());
        System.out.println("Is integer? " + intNumberBox.isInteger());

        // NumberBox<String> stringNumberBox;  // ERROR! String doesn't extend Number

        // ========================================================================
        // SECTION 4: GENERIC METHODS
        // ========================================================================
        System.out.println("\n--- SECTION 4: Generic Methods ---");

        // Method works with any array type
        String[] strings = {"Hello", "World"};
        Integer[] integers = {1, 2, 3, 4, 5};
        Double[] doubles = {1.1, 2.2, 3.3};

        System.out.print("String array: ");
        GenericMethods.printArray(strings);

        System.out.print("Integer array: ");
        GenericMethods.printArray(integers);

        System.out.print("Double array: ");
        GenericMethods.printArray(doubles);

        // Generic method with return type
        String firstString = GenericMethods.getFirst(strings);
        System.out.println("\nFirst string: " + firstString);

        // Bounded generic method
        String maxString = GenericMethods.findMax("Apple", "Banana");
        Integer maxInt = GenericMethods.findMax(10, 20);
        System.out.println("Max string: " + maxString);
        System.out.println("Max integer: " + maxInt);

        // ========================================================================
        // SECTION 5: WILDCARDS
        // ========================================================================
        System.out.println("\n--- SECTION 5: Wildcards ---");

        List<Integer> intList = Arrays.asList(1, 2, 3);
        List<String> strList = Arrays.asList("A", "B", "C");
        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);

        System.out.println("Unbounded wildcard:");
        WildcardExamples.printList(intList);
        WildcardExamples.printList(strList);

        System.out.println("\nUpper bounded wildcard (Number or subclasses):");
        System.out.println("Sum of integers: " + WildcardExamples.sumOfList(intList));
        System.out.println("Sum of doubles: " + WildcardExamples.sumOfList(doubleList));
        // WildcardExamples.sumOfList(strList);  // ERROR! String not a Number

        System.out.println("\nLower bounded wildcard (Integer or superclasses):");
        List<Number> numberList = new ArrayList<>();
        WildcardExamples.addNumbers(numberList);
        System.out.println("Added to Number list: " + numberList);

        // ========================================================================
        // SECTION 6: TYPE INFERENCE
        // ========================================================================
        System.out.println("\n--- SECTION 6: Type Inference ---");

        // Java can often infer the type
        Box<String> box1 = new Box<>();  // Diamond operator
        var box2 = new Box<String>();     // var keyword (Java 10+)

        box1.setContent("Inferred");
        System.out.println("Box 1: " + box1.getContent());

        // ========================================================================
        // SECTION 7: RAW TYPES (AVOID!)
        // ========================================================================
        System.out.println("\n--- SECTION 7: Raw Types (Legacy Code) ---");
        System.out.println("Raw types exist for backward compatibility only!\n");

        // Raw type - no type parameter (AVOID THIS!)
        Box rawBox = new Box();  // Raw type
        rawBox.setContent("String");  // Can put anything
        // String s = rawBox.getContent();  // ERROR! Returns Object

        // Proper generic usage
        Box<String> properBox = new Box<>();
        properBox.setContent("String");
        String s = properBox.getContent();  // No cast needed!

        // ========================================================================
        // SECTION 8: GENERICS WITH COLLECTIONS
        // ========================================================================
        System.out.println("\n--- SECTION 8: Generics with Collections ---");

        // Without generics (raw type) - DANGEROUS!
        List oldStyleList = new ArrayList();
        oldStyleList.add("String");
        oldStyleList.add(123);  // Can add anything!
        // String item = (String) oldStyleList.get(1);  // ClassCastException at runtime!

        // With generics - TYPE SAFE!
        List<String> newStyleList = new ArrayList<>();
        newStyleList.add("String");
        // newStyleList.add(123);  // COMPILE ERROR! Can't add Integer to List<String>

        String item = newStyleList.get(0);  // No cast needed!
        System.out.println("Type-safe retrieval: " + item);

        // ========================================================================
        // SUMMARY
        // ========================================================================
        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║  GENERICS SUMMARY:                                               ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║  <T>           Type parameter                                     ║");
        System.out.println("║  <T extends X> Bounded type (T must be X or subclass)          ║");
        System.out.println("║  <?>            Unbounded wildcard                               ║");
        System.out.println("║  <? extends T> Upper bounded (T or subclass)                     ║");
        System.out.println("║  <? super T>   Lower bounded (T or superclass)                  ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║  BENEFITS:                                                       ║");
        System.out.println("║  • Compile-time type safety                                    ║");
        System.out.println("║  • No casting required                                           ║");
        System.out.println("║  • Code reusability                                              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
    }
}
