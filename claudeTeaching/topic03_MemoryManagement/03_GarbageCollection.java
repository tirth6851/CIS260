package topic03_MemoryManagement;

import java.lang.ref.*;
import java.util.*;

/**
 * ============================================================================
 * TOPIC 3: MEMORY MANAGEMENT - Garbage Collection and Reference Types
 * ============================================================================
 *
 * This file demonstrates:
 * 1. How Garbage Collection works
 * 2. Different types of references (Strong, Soft, Weak, Phantom)
 * 3. Memory leaks and how to avoid them
 * 4. Best practices for memory management
 *
 * REFERENCE TYPES:
 * 1. Strong Reference (default): Object NOT collected while reference exists
 * 2. Soft Reference: Object collected only when memory needed
 * 3. Weak Reference: Object collected at next GC cycle
 * 4. Phantom Reference: Object collected, used for cleanup notification
 */

// ============================================================================
// DEMONSTRATION CLASS
// ============================================================================
class LargeObject {
    private byte[] data;  // Large array to consume memory
    private String name;

    public LargeObject(String name, int sizeInMB) {
        this.name = name;
        // Allocate memory (1 MB = 1024 * 1024 bytes)
        this.data = new byte[sizeInMB * 1024 * 1024];
        System.out.println("Created LargeObject: " + name + " (" + sizeInMB + " MB)");
    }

    @Override
    protected void finalize() throws Throwable {
        // Called by GC before object is collected
        // NOTE: Not guaranteed to be called!
        System.out.println("  finalize() called for: " + name);
        super.finalize();
    }

    public String getName() { return name; }
}

// ============================================================================
// MAIN CLASS
// ============================================================================
public class GarbageCollection {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║     GARBAGE COLLECTION AND REFERENCE TYPES                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝\n");

        Runtime runtime = Runtime.getRuntime();

        // ========================================================================
        // SECTION 1: STRONG REFERENCES (Default)
        // ========================================================================
        System.out.println("--- SECTION 1: Strong References ---");
        System.out.println("Strong references are the normal references we use.");
        System.out.println("Objects with strong references are NEVER collected.\n");

        LargeObject strongRef = new LargeObject("StrongObject", 1);
        // strongRef points to the object, so it won't be collected
        System.out.println("Object exists: " + (strongRef != null));

        // Even if we run GC, object remains
        System.gc();
        Thread.sleep(100);  // Give GC time to run
        System.out.println("After GC, object still exists: " + (strongRef != null));

        // Only when reference is gone, object becomes eligible
        strongRef = null;
        System.out.println("Reference set to null, object now eligible for GC");

        // ========================================================================
        // SECTION 2: SOFT REFERENCES
        // ========================================================================
        System.out.println("\n--- SECTION 2: Soft References ---");
        System.out.println("Soft references are collected ONLY when memory is needed.");
        System.out.println("Useful for caches - objects stay until memory pressure.\n");

        // Create a soft reference
        SoftReference<LargeObject> softRef = new SoftReference<>(
            new LargeObject("SoftObject", 1)
        );

        System.out.println("Soft reference created");
        System.out.println("Object accessible: " + (softRef.get() != null));

        // Object likely still exists (no memory pressure yet)
        System.out.println("After normal operation, object exists: " + (softRef.get() != null));

        // Simulate memory pressure
        System.out.println("\nSimulating memory pressure...");
        List<byte[]> memoryHog = new ArrayList<>();
        try {
            while (softRef.get() != null) {
                memoryHog.add(new byte[1024 * 1024]);  // Allocate 1MB at a time
            }
        } catch (OutOfMemoryError e) {
            System.out.println("OutOfMemoryError caught!");
        }

        System.out.println("After memory pressure, object exists: " + (softRef.get() != null));
        // Object was cleared to free memory!

        memoryHog.clear();  // Free up memory
        System.gc();

        // ========================================================================
        // SECTION 3: WEAK REFERENCES
        // ========================================================================
        System.out.println("\n--- SECTION 3: Weak References ---");
        System.out.println("Weak references are collected at NEXT GC cycle.");
        System.out.println("Useful for canonicalizing mappings (WeakHashMap).\n");

        WeakReference<LargeObject> weakRef = new WeakReference<>(
            new LargeObject("WeakObject", 1)
        );

        System.out.println("Weak reference created");
        System.out.println("Before GC, object exists: " + (weakRef.get() != null));

        // Suggest garbage collection
        System.out.println("Calling System.gc()...");
        System.gc();
        Thread.sleep(100);

        // Object likely collected (no strong references)
        System.out.println("After GC, object exists: " + (weakRef.get() != null));

        // ========================================================================
        // SECTION 4: WEAKHASHMAP DEMONSTRATION
        // ========================================================================
        System.out.println("\n--- SECTION 4: WeakHashMap ---");
        System.out.println("WeakHashMap uses weak references for keys.");
        System.out.println("When key is no longer strongly referenced, entry is removed.\n");

        WeakHashMap<Object, String> weakMap = new WeakHashMap<>();

        // Create a key object
        Object key1 = new Object();
        weakMap.put(key1, "Value for key1");

        System.out.println("Map size with strong reference: " + weakMap.size());

        // Remove strong reference
        key1 = null;

        // Suggest GC
        System.out.println("Key reference set to null, calling GC...");
        System.gc();
        Thread.sleep(100);

        // Entry should be removed
        System.out.println("Map size after GC: " + weakMap.size());

        // ========================================================================
        // SECTION 5: PHANTOM REFERENCES
        // ========================================================================
        System.out.println("\n--- SECTION 5: Phantom References ---");
        System.out.println("Phantom references are used for cleanup notification.");
        System.out.println("Object is already finalized when phantom reachable.\n");

        ReferenceQueue<LargeObject> queue = new ReferenceQueue<>();
        LargeObject phantomObj = new LargeObject("PhantomObject", 1);
        PhantomReference<LargeObject> phantomRef = new PhantomReference<>(
            phantomObj, queue
        );

        System.out.println("Phantom reference created");
        System.out.println("Phantom ref returns null on get(): " + (phantomRef.get() == null));
        // Phantom references always return null from get()

        // Clear strong reference
        phantomObj = null;

        // Wait for GC and check queue
        System.out.println("Waiting for object to be queued...");
        Reference<? extends LargeObject> refFromQueue = null;
        int attempts = 0;
        while (refFromQueue == null && attempts < 10) {
            System.gc();
            refFromQueue = queue.poll();
            if (refFromQueue == null) {
                Thread.sleep(100);
                attempts++;
            }
        }

        if (refFromQueue != null) {
            System.out.println("Object was queued for cleanup!");
            refFromQueue.clear();  // Clean up the reference
        } else {
            System.out.println("Object not yet queued (GC may not have run)");
        }

        // ========================================================================
        // SECTION 6: MEMORY LEAK EXAMPLE
        // ========================================================================
        System.out.println("\n--- SECTION 6: Memory Leak Example ---");
        System.out.println("Common cause: Static collections that grow indefinitely\n");

        // BAD PRACTICE: Static list that never clears
        staticList.add("Item 1");
        staticList.add("Item 2");
        System.out.println("Static list size: " + staticList.size());
        System.out.println("This list will live for the entire application!");
        System.out.println("If we keep adding items without removing, we get a memory leak.");

        // GOOD PRACTICE: Use WeakReference or clear when done

        // ========================================================================
        // SECTION 7: BEST PRACTICES
        // ========================================================================
        System.out.println("\n--- SECTION 7: Memory Management Best Practices ---");

        System.out.println("\n1. Avoid memory leaks:");
        System.out.println("   • Clear collections when done");
        System.out.println("   • Remove listeners when not needed");
        System.out.println("   • Close resources (use try-with-resources)");

        System.out.println("\n2. Use appropriate references:");
        System.out.println("   • Strong: Normal object usage");
        System.out.println("   • Soft: Caches (survive GC until memory needed)");
        System.out.println("   • Weak: Canonical mappings (collected when not used)");
        System.out.println("   • Phantom: Pre-mortem cleanup");

        System.out.println("\n3. Don't rely on finalize():");
        System.out.println("   • Not guaranteed to run");
        System.out.println("   • Can delay GC");
        System.out.println("   • Use try-finally or try-with-resources instead");

        System.out.println("\n4. Monitor memory usage:");
        long usedMB = (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024);
        System.out.println("   Current memory used: " + usedMB + " MB");

        // ========================================================================
        // SUMMARY
        // ========================================================================
        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║  REFERENCE TYPE COMPARISON                                       ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║  Strong:  Object kept until reference cleared                    ║");
        System.out.println("║  Soft:    Object kept until memory needed                        ║");
        System.out.println("║  Weak:    Object collected at next GC                           ║");
        System.out.println("║  Phantom: Object collected, reference used for notification       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
    }

    // Static list to demonstrate memory leak potential
    private static List<String> staticList = new ArrayList<>();
}
