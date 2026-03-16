package topic03_MemoryManagement;

/**
 * ============================================================================
 * TOPIC 3: MEMORY MANAGEMENT - Stack vs Heap Demonstration
 * ============================================================================
 *
 * This file demonstrates the difference between Stack and Heap memory in Java.
 *
 * STACK MEMORY:
 * - Stores: Local variables, method parameters, reference variables
 * - Lifetime: Method execution duration
 * - Management: Automatic (LIFO - Last In, First Out)
 * - Size: Small (typically 1MB per thread)
 * - Speed: Very fast
 * - Thread-safe: Yes (each thread has its own stack)
 *
 * HEAP MEMORY:
 * - Stores: Objects, instance variables
 * - Lifetime: Until garbage collected
 * - Management: Garbage Collector
 * - Size: Large (configurable, grows as needed)
 * - Speed: Slower than stack
 * - Thread-safe: No (shared among threads)
 *
 * VISUALIZATION:
 * ┌─────────────────────────────────────┐
 * │           STACK                     │
 * │  ┌─────────────────────────────┐    │
 * │  │ main():                     │    │
 * │  │   num = 10 (primitive)      │    │
 * │  │   ref ─────┐                │    │
 * │  └────────────│────────────────┘    │
 * │               │                     │
 * │  ┌────────────┘─────────────────┐   │
 * │  │           HEAP                │   │
 * │  │  ┌─────────────────────┐      │   │
 * │  │  │ Person Object       │      │   │
 * │  │  │   name = "Alice"    │◄─────┘   │
 * │  │  │   age = 25          │          │
 * │  │  └─────────────────────┘          │
 * │  └───────────────────────────────────┘
 * └─────────────────────────────────────┘
 */

// ============================================================================
// SIMPLE CLASS TO DEMONSTRATE HEAP STORAGE
// ============================================================================
class Person {
    // These instance variables are stored in HEAP memory
    // because they belong to the object
    String name;    // Reference stored in heap (points to String in heap)
    int age;        // Primitive stored directly in heap (inside object)

    // Constructor
    public Person(String name, int age) {
        // 'name' and 'age' parameters are in STACK (local variables)
        // The new Person object is created in HEAP
        this.name = name;
        this.age = age;
        System.out.println("  [HEAP] Created Person object: " + this.name);
    }

    // Method to show memory usage
    public void displayInfo() {
        // 'this' reference is passed implicitly (in stack)
        System.out.println("    Person: " + name + ", Age: " + age);
    }
}

// ============================================================================
// MAIN DEMONSTRATION CLASS
// ============================================================================
public class StackVsHeap {

    // Static variable - stored in Method Area (part of heap)
    private static String className = "StackVsHeap";

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║     TOPIC 3: MEMORY MANAGEMENT - Stack vs Heap                   ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝\n");

        // ========================================================================
        // SECTION 1: PRIMITIVE VARIABLES (STACK)
        // ========================================================================
        System.out.println("--- SECTION 1: Primitive Variables (Stored in STACK) ---");

        // These primitives are stored directly in STACK memory
        // They hold their actual values
        int primitiveInt = 42;           // 4 bytes on stack
        double primitiveDouble = 3.14;     // 8 bytes on stack
        boolean primitiveBool = true;      // 1 byte on stack
        char primitiveChar = 'A';          // 2 bytes on stack

        System.out.println("Primitive int: " + primitiveInt + " (stored in stack)");
        System.out.println("Primitive double: " + primitiveDouble + " (stored in stack)");
        System.out.println("Primitive boolean: " + primitiveBool + " (stored in stack)");
        System.out.println("Primitive char: " + primitiveChar + " (stored in stack)");

        // When primitives are passed to methods, a COPY is made
        System.out.println("\nPassing primitives to method:");
        modifyPrimitive(primitiveInt);
        System.out.println("After method call, original is unchanged: " + primitiveInt);
        // Output is still 42 because we passed a copy!

        // ========================================================================
        // SECTION 2: OBJECT REFERENCES (STACK) vs OBJECTS (HEAP)
        // ========================================================================
        System.out.println("\n--- SECTION 2: References (Stack) vs Objects (Heap) ---");

        // Step 1: 'person1' is a REFERENCE variable stored in STACK
        // Step 2: 'new Person(...)' creates an OBJECT in HEAP
        // Step 3: The reference in stack points to the object in heap
        Person person1 = new Person("Alice", 25);

        // Memory layout at this point:
        // STACK                    HEAP
        // ┌─────────────┐          ┌──────────────────┐
        // │ person1     │─────────▶│ Person Object    │
        // │  (4/8 bytes)│          │  name: "Alice"   │
        // └─────────────┘          │  age: 25         │
        //                          └──────────────────┘

        // Both person1 and person2 are references in STACK
        // They both point to the SAME object in HEAP
        Person person2 = person1;  // Copy the reference, not the object!

        System.out.println("\nperson1 == person2: " + (person1 == person2));
        // true - they point to the same object in memory

        // Modifying through person2 affects person1 (same object!)
        person2.age = 30;
        System.out.println("After person2.age = 30:");
        System.out.println("  person1.age = " + person1.age);  // 30!
        System.out.println("  person2.age = " + person2.age);  // 30!

        // ========================================================================
        // SECTION 3: METHOD CALL STACK
        // ========================================================================
        System.out.println("\n--- SECTION 3: Method Call Stack ---");
        System.out.println("Calling methodA()...");
        methodA();
        // When methodA() is called, a new stack frame is created
        // When it returns, that frame is popped off the stack
        System.out.println("Returned to main()");

        // ========================================================================
        // SECTION 4: NULL REFERENCES
        // ========================================================================
        System.out.println("\n--- SECTION 4: Null References ---");

        Person person3 = new Person("Bob", 30);  // Object created in heap
        System.out.println("person3 points to: " + person3);

        person3 = null;  // Reference now points to nothing
        System.out.println("After null assignment, person3 = " + person3);

        // The Person object ("Bob", 30) is now unreachable
        // It becomes eligible for Garbage Collection
        System.out.println("The Bob object is now eligible for Garbage Collection!");

        // person3.displayInfo();  // This would throw NullPointerException!

        // ========================================================================
        // SECTION 5: MEMORY VISUALIZATION
        // ========================================================================
        System.out.println("\n--- SECTION 5: Complete Memory Visualization ---");

        // Create multiple objects to show heap usage
        Person p1 = new Person("Charlie", 20);
        Person p2 = new Person("Diana", 22);
        Person p3 = p1;  // Same reference as p1

        // Current memory state:
        // STACK:                    HEAP:
        // ┌──────────┐              ┌──────────────────┐
        // │ p1       │─────────────▶│ Person (Charlie) │
        // ├──────────┤         ┌────│  age: 20         │
        // │ p2       │────────┘    └──────────────────┘
        // ├──────────┤              ┌──────────────────┐
        // │ p3       │─────────────▶│ Person (Diana)   │
        // └──────────┘              │  age: 22         │
        //                           └──────────────────┘

        System.out.println("\nMemory State:");
        System.out.println("  p1 and p3 both point to Charlie");
        System.out.println("  p2 points to Diana");
        System.out.println("  p1 == p3: " + (p1 == p3));  // true
        System.out.println("  p1 == p2: " + (p1 == p2));  // false

        // ========================================================================
        // SECTION 6: MEMORY MONITORING
        // ========================================================================
        System.out.println("\n--- SECTION 6: Runtime Memory Information ---");

        // Get the Runtime object to query memory
        Runtime runtime = Runtime.getRuntime();

        // Memory values are in bytes, convert to MB for readability
        long maxMemory = runtime.maxMemory() / (1024 * 1024);
        long totalMemory = runtime.totalMemory() / (1024 * 1024);
        long freeMemory = runtime.freeMemory() / (1024 * 1024);
        long usedMemory = totalMemory - freeMemory;

        System.out.println("JVM Memory Information:");
        System.out.println("  Maximum Memory: " + maxMemory + " MB");
        System.out.println("  Total Memory: " + totalMemory + " MB");
        System.out.println("  Free Memory: " + freeMemory + " MB");
        System.out.println("  Used Memory: " + usedMemory + " MB");

        // ========================================================================
        // SECTION 7: SUGGESTING GARBAGE COLLECTION
        // ========================================================================
        System.out.println("\n--- SECTION 7: Garbage Collection ---");

        // Create objects and then make them eligible for GC
        for (int i = 0; i < 1000; i++) {
            // These objects are created but immediately become unreachable
            // because we don't store references to them
            new Person("Temp" + i, i);  // Eligible for GC immediately
        }

        System.out.println("Created 1000 temporary objects");
        System.out.println("All are eligible for Garbage Collection");

        // Suggest GC (not guaranteed to run immediately)
        System.out.println("Calling System.gc() to suggest garbage collection...");
        System.gc();

        // Check memory after GC suggestion
        long freeAfterGC = runtime.freeMemory() / (1024 * 1024);
        System.out.println("Free memory after GC suggestion: " + freeAfterGC + " MB");

        // ========================================================================
        // SUMMARY
        // ========================================================================
        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║                      SUMMARY                                     ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║ STACK MEMORY:                                                    ║");
        System.out.println("║   • Stores: Primitives, local variables, references              ║");
        System.out.println("║   • Fast, automatic, thread-safe                                 ║");
        System.out.println("║   • Cleared when method returns                                  ║");
        System.out.println("║                                                                  ║");
        System.out.println("║ HEAP MEMORY:                                                     ║");
        System.out.println("║   • Stores: Objects, instance variables                          ║");
        System.out.println("║   • Managed by Garbage Collector                                 ║");
        System.out.println("║   • Shared among all threads                                     ║");
        System.out.println("║   • Objects live until no references point to them               ║");
        System.out.println("║                                                                  ║");
        System.out.println("║ KEY INSIGHT:                                                     ║");
        System.out.println("║   Person p = new Person();                                       ║");
        System.out.println("║   └── p is in STACK, Person object is in HEAP                   ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
    }

    // ========================================================================
    // HELPER METHODS
    // ========================================================================

    /**
     * Demonstrates that primitives are passed by value (copy)
     */
    private static void modifyPrimitive(int num) {
        // 'num' is a COPY of the original value
        // Changes here don't affect the original
        System.out.println("  Inside method, before: " + num);
        num = 100;  // Only changes the local copy
        System.out.println("  Inside method, after: " + num);
    }

    /**
     * Demonstrates stack frame creation
     */
    private static void methodA() {
        // New stack frame created for methodA
        int localVarA = 10;  // Stored in methodA's stack frame
        System.out.println("  Inside methodA(), localVarA = " + localVarA);

        System.out.println("  Calling methodB()...");
        methodB();
        System.out.println("  Back in methodA()");

        // methodA's stack frame is popped when it returns
    }

    private static void methodB() {
        // Another stack frame created on top of methodA's
        int localVarB = 20;
        System.out.println("    Inside methodB(), localVarB = " + localVarB);

        System.out.println("    Calling methodC()...");
        methodC();
        System.out.println("    Back in methodB()");

        // methodB's frame is popped
    }

    private static void methodC() {
        // Deepest stack frame
        int localVarC = 30;
        System.out.println("      Inside methodC(), localVarC = " + localVarC);
        // methodC's frame is popped
    }
}
