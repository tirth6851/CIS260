# Topic 6: Recursion - Complete Guide

## What is Recursion?

Recursion is a programming technique where a method calls itself to solve a problem by breaking it down into smaller, similar subproblems.

## Key Components of Recursion

### 1. Base Case (Termination Condition)
- The condition that stops the recursion
- Without it, infinite recursion occurs (StackOverflowError)
- Every recursive method must have at least one base case

### 2. Recursive Case
- The method calls itself with a smaller/different input
- Moves toward the base case
- Combines results from recursive calls

## How Recursion Works

```
Recursive call stack:

factorial(5)
    └── factorial(4)
            └── factorial(3)
                    └── factorial(2)
                            └── factorial(1)  [BASE CASE]
                            └── returns 1
                    └── returns 2 * 1 = 2
            └── returns 3 * 2 = 6
    └── returns 4 * 6 = 24
└── returns 5 * 24 = 120
```

## Types of Recursion

### 1. Direct Recursion
Method calls itself directly.
```java
void method() {
    method();  // Direct call
}
```

### 2. Indirect Recursion
Method A calls method B, which calls method A.
```java
void methodA() {
    methodB();
}
void methodB() {
    methodA();
}
```

### 3. Tail Recursion
Recursive call is the last operation in the method.
- Can be optimized by some compilers
- Java does NOT optimize tail recursion

### 4. Head Recursion
Recursive call happens before processing.

## Common Recursive Problems

### Mathematical
- Factorial
- Fibonacci
- Power calculation
- Greatest Common Divisor (GCD)

### Data Structure Traversal
- Linked List operations
- Tree traversals
- Graph traversals

### Divide and Conquer
- Binary Search
- Merge Sort
- Quick Sort

### Backtracking
- N-Queens
- Sudoku solver
- Maze solving
- Permutations/Combinations

## Recursion vs Iteration

| Aspect | Recursion | Iteration |
|--------|-----------|-----------|
| Code | Often cleaner, more readable | Can be more verbose |
| Memory | Uses call stack (O(n) space) | Usually O(1) space |
| Speed | Slower (function call overhead) | Faster |
| Stack overflow | Risk with deep recursion | No risk |
| Best for | Tree/graph problems, divide & conquer | Simple loops, performance-critical |

## When to Use Recursion

**Use recursion when:**
- Problem naturally breaks into similar subproblems
- Working with recursive data structures (trees)
- Backtracking is needed
- Code clarity is more important than performance

**Avoid recursion when:**
- Performance is critical
- Recursion depth could be very large
- Simple iteration works just as well

## Stack Overflow

Each recursive call adds a frame to the call stack.
- Java default stack size: ~1MB
- Deep recursion can cause `StackOverflowError`
- Solution: Convert to iteration or increase stack size

## Memoization

Store results of expensive function calls to avoid redundant calculations.

```java
// Without memoization: O(2^n)
// With memoization: O(n)
int fib(int n, int[] memo) {
    if (n <= 1) return n;
    if (memo[n] != 0) return memo[n];
    memo[n] = fib(n-1, memo) + fib(n-2, memo);
    return memo[n];
}
```

## Best Practices

1. **Always define base case first**
2. **Ensure progress toward base case**
3. **Don't mix recursion with global state**
4. **Consider memoization for overlapping subproblems**
5. **Test with small inputs first**
6. **Consider stack overflow risk**
