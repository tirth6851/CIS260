# Topic 3: Memory Management - Complete Guide

## Understanding Java Memory

Java memory is divided into two main areas:

### 1. Stack Memory
- **What**: Stores local variables, method parameters, and reference variables
- **When**: Memory allocated when a method is called, freed when method returns
- **How**: LIFO (Last In, First Out) structure
- **Size**: Small and fixed per thread
- **Speed**: Very fast allocation/deallocation
- **Thread Safety**: Each thread has its own stack

### 2. Heap Memory
- **What**: Stores objects and instance variables
- **When**: Memory allocated when 'new' is called, freed by Garbage Collector
- **How**: Dynamic memory allocation
- **Size**: Large and expandable
- **Speed**: Slower than stack
- **Thread Safety**: Shared among all threads (needs synchronization)

## Memory Layout

```
┌─────────────────────────────────────┐
│           STACK MEMORY              │
│  ┌─────────────────────────────┐    │
│  │ Local variables             │    │
│  │ Reference variables         │    │
│  │ Method call info            │    │
│  └─────────────────────────────┘    │
├─────────────────────────────────────┤
│           HEAP MEMORY               │
│  ┌─────────────────────────────┐    │
│  │ Young Generation            │    │
│  │   - Eden Space              │    │
│  │   - Survivor Spaces (S0,S1) │    │
│  ├─────────────────────────────┤    │
│  │ Old Generation (Tenured)    │    │
│  └─────────────────────────────┘    │
├─────────────────────────────────────┤
│      METHOD AREA (Metaspace)        │
│  - Class definitions                │
│  - Static variables                 │
│  - Method code                      │
└─────────────────────────────────────┘
```

## Stack vs Heap Comparison

| Aspect | Stack | Heap |
|--------|-------|------|
| Stores | Primitives, references, local vars | Objects, instance variables |
| Lifetime | Method execution | Application lifetime |
| Management | Automatic (LIFO) | Garbage Collector |
| Size | Small (~1MB per thread) | Large (configurable) |
| Speed | Fast | Slower |
| Thread-safe | Yes (per thread) | No (shared) |

## Garbage Collection (GC)

### What is GC?
Automatic memory management that:
1. Identifies objects no longer in use
2. Reclaims their memory
3. Compacts memory to reduce fragmentation

### How GC Works

#### Mark and Sweep Algorithm
1. **Mark**: Traverse all reachable objects from GC roots
2. **Sweep**: Remove unmarked objects
3. **Compact**: Move remaining objects to reduce fragmentation

#### Generational GC
Java uses generational hypothesis: most objects die young

**Young Generation**:
- Eden Space: New objects created here
- Survivor Spaces (S0, S1): Objects that survive GC
- Minor GC: Frequent, fast collection

**Old Generation (Tenured)**:
- Long-lived objects promoted here
- Major GC (Full GC): Infrequent, slower

### GC Roots
Objects that are always considered "alive":
- Local variables in stack frames
- Active Java threads
- Static variables
- JNI references

## Reference Types

### 1. Strong Reference (Default)
```java
Object obj = new Object();  // Strong reference
// Object NOT eligible for GC while obj points to it
```

### 2. Soft Reference
```java
SoftReference<Object> softRef = new SoftReference<>(new Object());
// Cleared before OutOfMemoryError
// Good for caches
```

### 3. Weak Reference
```java
WeakReference<Object> weakRef = new WeakReference<>(new Object());
// Cleared at next GC regardless of memory
// Good for canonicalizing mappings
```

### 4. Phantom Reference
```java
PhantomReference<Object> phantomRef = new PhantomReference<>(obj, referenceQueue);
// Used for cleanup actions before GC
// Object is already finalized when phantom reachable
```

## Memory Leaks in Java

Common causes:
1. Static collections that grow indefinitely
2. Unclosed resources (streams, connections)
3. Listener callbacks not deregistered
4. Inner classes holding outer class reference
5. Improper equals() and hashCode() in cached objects

## Best Practices

1. **Avoid unnecessary object creation**
   - Use StringBuilder for string concatenation in loops
   - Consider object pooling for expensive objects

2. **Nullify references when done**
   - Especially for large objects, long-lived collections

3. **Use try-with-resources**
   - Ensures resources are closed properly

4. **Be careful with static fields**
   - They live for the entire application lifetime

5. **Choose appropriate collection sizes**
   - Avoid frequent resizing

6. **Override finalize() sparingly**
   - Unpredictable when/if it runs
   - Can delay GC

## Monitoring Memory

### Runtime Methods
```java
Runtime runtime = Runtime.getRuntime();
long totalMemory = runtime.totalMemory();
long freeMemory = runtime.freeMemory();
long usedMemory = totalMemory - freeMemory;
```

### JVM Options
- `-Xms512m` - Initial heap size
- `-Xmx2g` - Maximum heap size
- `-XX:+PrintGCDetails` - Print GC details
- `-XX:+HeapDumpOnOutOfMemoryError` - Dump heap on OOM
