package topic06_Recursion;

/**
 * ============================================================================
 * TOPIC 6: RECURSION - Advanced Examples
 * ============================================================================
 *
 * This file demonstrates advanced recursive algorithms:
 * 1. Binary Search
 * 2. Merge Sort
 * 3. Tower of Hanoi
 * 4. Permutations
 * 5. File System Traversal
 */

import java.io.File;
import java.util.*;

public class RecursionAdvanced {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║     TOPIC 6: RECURSION - Advanced Examples                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝\n");

        // ========================================================================
        // SECTION 1: BINARY SEARCH
        // ========================================================================
        System.out.println("--- SECTION 1: Binary Search (Recursive) ---");
        System.out.println("Divide array in half, search in appropriate half\n");

        int[] sortedArray = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        System.out.println("Array: " + Arrays.toString(sortedArray));

        int[] targets = {23, 2, 91, 100};
        for (int target : targets) {
            int result = binarySearch(sortedArray, target, 0, sortedArray.length - 1);
            System.out.println("Searching for " + target + ": " +
                (result != -1 ? "Found at index " + result : "Not found"));
        }

        // ========================================================================
        // SECTION 2: MERGE SORT
        // ========================================================================
        System.out.println("\n--- SECTION 2: Merge Sort ---");
        System.out.println("Divide array into halves, sort each half, merge results\n");

        int[] unsorted = {64, 34, 25, 12, 22, 11, 90, 5};
        System.out.println("Original: " + Arrays.toString(unsorted));
        mergeSort(unsorted, 0, unsorted.length - 1);
        System.out.println("Sorted:   " + Arrays.toString(unsorted));

        // ========================================================================
        // SECTION 3: TOWER OF HANOI
        // ========================================================================
        System.out.println("\n--- SECTION 3: Tower of Hanoi ---");
        System.out.println("Move n disks from source to destination using auxiliary peg\n");
        System.out.println("Rules: Move one disk at a time, never place larger on smaller\n");

        int numDisks = 3;
        System.out.println("Solving Tower of Hanoi with " + numDisks + " disks:");
        towerOfHanoi(numDisks, 'A', 'C', 'B');

        // ========================================================================
        // SECTION 4: PERMUTATIONS
        // ========================================================================
        System.out.println("\n--- SECTION 4: String Permutations ---");
        System.out.println("Generate all possible arrangements of characters\n");

        String input = "ABC";
        System.out.println("All permutations of \"" + input + "\":");
        generatePermutations(input, "");

        // ========================================================================
        // SECTION 5: SUBSET GENERATION
        // ========================================================================
        System.out.println("\n--- SECTION 5: Subset Generation (Power Set) ---");
        System.out.println("Generate all possible subsets of a set\n");

        int[] set = {1, 2, 3};
        System.out.println("All subsets of " + Arrays.toString(set) + ":");
        List<List<Integer>> subsets = new ArrayList<>();
        generateSubsets(set, 0, new ArrayList<>(), subsets);
        for (List<Integer> subset : subsets) {
            System.out.println("  " + subset);
        }

        // ========================================================================
        // SECTION 6: FILE SYSTEM TRAVERSAL
        // ========================================================================
        System.out.println("\n--- SECTION 6: File System Traversal ---");
        System.out.println("Recursively list all files in a directory\n");

        // Use current directory
        File currentDir = new File(".");
        System.out.println("Files in current directory:");
        listFilesRecursive(currentDir, 0);

        // ========================================================================
        // SECTION 7: MEMOIZATION EXAMPLE
        // ========================================================================
        System.out.println("\n--- SECTION 7: Fibonacci with Memoization ---");
        System.out.println("Store computed values to avoid redundant calculations\n");

        int n = 40;
        long startTime = System.currentTimeMillis();
        long result = fibonacciMemo(n, new HashMap<>());
        long endTime = System.currentTimeMillis();
        System.out.println("fibonacciMemo(" + n + ") = " + result);
        System.out.println("Time: " + (endTime - startTime) + " ms");

        // Compare with naive version (commented out because it's slow)
        // startTime = System.currentTimeMillis();
        // result = fibonacciNaive(n);
        // endTime = System.currentTimeMillis();
        // System.out.println("fibonacciNaive(" + n + ") = " + result);
        // System.out.println("Time: " + (endTime - startTime) + " ms");

        // ========================================================================
        // SUMMARY
        // ========================================================================
        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║  ADVANCED RECURSION PATTERNS:                                    ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║  Divide and Conquer:                                             ║");
        System.out.println("║    • Split problem, solve subproblems, combine results           ║");
        System.out.println("║    • Examples: Merge Sort, Binary Search, Quick Sort             ║");
        System.out.println("║                                                                  ║");
        System.out.println("║  Backtracking:                                                   ║");
        System.out.println("║    • Try possibilities, backtrack if dead end                   ║");
        System.out.println("║    • Examples: Permutations, N-Queens, Sudoku                    ║");
        System.out.println("║                                                                  ║");
        System.out.println("║  Memoization:                                                    ║");
        System.out.println("║    • Cache results to avoid recomputation                        ║");
        System.out.println("║    • Transforms exponential time to linear                      ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
    }

    /**
     * BINARY SEARCH
     * Search for target in sorted array
     * Time: O(log n), Space: O(log n) for recursion
     */
    public static int binarySearch(int[] arr, int target, int left, int right) {
        // BASE CASE: element not found
        if (left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;

        // BASE CASE: found the target
        if (arr[mid] == target) {
            return mid;
        }

        // RECURSIVE CASE: search in appropriate half
        if (arr[mid] > target) {
            return binarySearch(arr, target, left, mid - 1);
        } else {
            return binarySearch(arr, target, mid + 1, right);
        }
    }

    /**
     * MERGE SORT
     * Divide array into halves, sort each, merge results
     * Time: O(n log n), Space: O(n)
     */
    public static void mergeSort(int[] arr, int left, int right) {
        // BASE CASE: single element
        if (left >= right) {
            return;
        }

        // Divide
        int mid = left + (right - left) / 2;

        // Conquer: sort both halves
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        // Combine: merge sorted halves
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        // Create temporary arrays
        int[] leftArr = Arrays.copyOfRange(arr, left, mid + 1);
        int[] rightArr = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, k = left;

        // Merge while both arrays have elements
        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        // Copy remaining elements
        while (i < leftArr.length) {
            arr[k++] = leftArr[i++];
        }
        while (j < rightArr.length) {
            arr[k++] = rightArr[j++];
        }
    }

    /**
     * TOWER OF HANOI
     * Move n disks from source to destination using auxiliary
     * Time: O(2^n), Space: O(n)
     */
    public static void towerOfHanoi(int n, char source, char dest, char aux) {
        // BASE CASE: no disks to move
        if (n == 0) {
            return;
        }

        // Move n-1 disks from source to auxiliary
        towerOfHanoi(n - 1, source, aux, dest);

        // Move nth disk from source to destination
        System.out.println("Move disk " + n + " from " + source + " to " + dest);

        // Move n-1 disks from auxiliary to destination
        towerOfHanoi(n - 1, aux, dest, source);
    }

    /**
     * GENERATE PERMUTATIONS
     * Generate all arrangements of characters in string
     * Time: O(n!), Space: O(n)
     */
    public static void generatePermutations(String remaining, String current) {
        // BASE CASE: no more characters to arrange
        if (remaining.isEmpty()) {
            System.out.println("  " + current);
            return;
        }

        // Try each character as the next position
        for (int i = 0; i < remaining.length(); i++) {
            char ch = remaining.charAt(i);
            String newRemaining = remaining.substring(0, i) + remaining.substring(i + 1);
            generatePermutations(newRemaining, current + ch);
        }
    }

    /**
     * GENERATE SUBSETS (Power Set)
     * Generate all possible subsets
     * Time: O(2^n), Space: O(n)
     */
    public static void generateSubsets(int[] nums, int index, List<Integer> current,
                                        List<List<Integer>> result) {
        // Add current subset to result
        result.add(new ArrayList<>(current));

        // Try including each remaining element
        for (int i = index; i < nums.length; i++) {
            current.add(nums[i]);
            generateSubsets(nums, i + 1, current, result);
            current.remove(current.size() - 1);  // Backtrack
        }
    }

    /**
     * LIST FILES RECURSIVELY
     * Traverse directory tree
     */
    public static void listFilesRecursive(File dir, int depth) {
        // BASE CASE: not a directory or doesn't exist
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }

        File[] files = dir.listFiles();
        if (files == null) return;

        String indent = "  ".repeat(depth);

        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println(indent + "[DIR] " + file.getName());
                listFilesRecursive(file, depth + 1);
            } else {
                System.out.println(indent + "[FILE] " + file.getName());
            }
        }
    }

    /**
     * FIBONACCI WITH MEMOIZATION
     * Store computed values to avoid redundant calculations
     * Time: O(n), Space: O(n)
     */
    public static long fibonacciMemo(int n, Map<Integer, Long> memo) {
        // BASE CASE
        if (n <= 1) {
            return n;
        }

        // Check if already computed
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        // Compute and store
        long result = fibonacciMemo(n - 1, memo) + fibonacciMemo(n - 2, memo);
        memo.put(n, result);
        return result;
    }

    /**
     * NAIVE FIBONACCI (for comparison)
     * Time: O(2^n) - very slow!
     */
    public static long fibonacciNaive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciNaive(n - 1) + fibonacciNaive(n - 2);
    }
}
