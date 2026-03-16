package topic12_SearchingSorting;

import java.util.*;

/**
 * ============================================================================
 * TOPIC 12: SEARCHING AND SORTING ALGORITHMS
 * ============================================================================
 *
 * This file implements and analyzes common searching and sorting algorithms.
 *
 * SEARCHING ALGORITHMS:
 * 1. Linear Search - O(n)
 * 2. Binary Search - O(log n)
 * 3. Jump Search - O(√n)
 *
 * SORTING ALGORITHMS:
 * 1. Bubble Sort - O(n²)
 * 2. Selection Sort - O(n²)
 * 3. Insertion Sort - O(n²)
 * 4. Merge Sort - O(n log n)
 * 5. Quick Sort - O(n log n) average, O(n²) worst
 * 6. Heap Sort - O(n log n)
 */

public class SearchingSorting {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║     TOPIC 12: SEARCHING AND SORTING ALGORITHMS                   ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝\n");

        // ========================================================================
        // SEARCHING ALGORITHMS
        // ========================================================================
        System.out.println("═══════════════════════════════════════════════════════════════════");
        System.out.println("                    SEARCHING ALGORITHMS                           ");
        System.out.println("═══════════════════════════════════════════════════════════════════\n");

        int[] searchArray = {64, 34, 25, 12, 22, 11, 90, 5};
        System.out.println("Array: " + Arrays.toString(searchArray));

        // Linear Search
        System.out.println("\n--- Linear Search O(n) ---");
        int target = 22;
        int result = linearSearch(searchArray, target);
        System.out.println("Searching for " + target + ": " +
            (result != -1 ? "Found at index " + result : "Not found"));

        // Binary Search (requires sorted array)
        System.out.println("\n--- Binary Search O(log n) ---");
        int[] sortedArray = {5, 11, 12, 22, 25, 34, 64, 90};
        System.out.println("Sorted array: " + Arrays.toString(sortedArray));
        target = 22;
        result = binarySearch(sortedArray, target, 0, sortedArray.length - 1);
        System.out.println("Searching for " + target + ": " +
            (result != -1 ? "Found at index " + result : "Not found"));

        // ========================================================================
        // SORTING ALGORITHMS
        // ========================================================================
        System.out.println("\n═══════════════════════════════════════════════════════════════════");
        System.out.println("                    SORTING ALGORITHMS                             ");
        System.out.println("═══════════════════════════════════════════════════════════════════\n");

        // Test arrays
        int[] original = {64, 34, 25, 12, 22, 11, 90, 5};

        // Bubble Sort
        System.out.println("--- Bubble Sort O(n²) ---");
        int[] bubbleArray = original.clone();
        System.out.println("Original: " + Arrays.toString(bubbleArray));
        bubbleSort(bubbleArray);
        System.out.println("Sorted:   " + Arrays.toString(bubbleArray));

        // Selection Sort
        System.out.println("\n--- Selection Sort O(n²) ---");
        int[] selectionArray = original.clone();
        System.out.println("Original: " + Arrays.toString(selectionArray));
        selectionSort(selectionArray);
        System.out.println("Sorted:   " + Arrays.toString(selectionArray));

        // Insertion Sort
        System.out.println("\n--- Insertion Sort O(n²) ---");
        int[] insertionArray = original.clone();
        System.out.println("Original: " + Arrays.toString(insertionArray));
        insertionSort(insertionArray);
        System.out.println("Sorted:   " + Arrays.toString(insertionArray));

        // Merge Sort
        System.out.println("\n--- Merge Sort O(n log n) ---");
        int[] mergeArray = original.clone();
        System.out.println("Original: " + Arrays.toString(mergeArray));
        mergeSort(mergeArray, 0, mergeArray.length - 1);
        System.out.println("Sorted:   " + Arrays.toString(mergeArray));

        // Quick Sort
        System.out.println("\n--- Quick Sort O(n log n) ---");
        int[] quickArray = original.clone();
        System.out.println("Original: " + Arrays.toString(quickArray));
        quickSort(quickArray, 0, quickArray.length - 1);
        System.out.println("Sorted:   " + Arrays.toString(quickArray));

        // ========================================================================
        // PERFORMANCE COMPARISON
        // ========================================================================
        System.out.println("\n═══════════════════════════════════════════════════════════════════");
        System.out.println("                    PERFORMANCE COMPARISON                         ");
        System.out.println("═══════════════════════════════════════════════════════════════════\n");

        int[] largeArray = generateRandomArray(10000);

        compareSort("Bubble Sort", largeArray.clone(), SearchingSorting::bubbleSort);
        compareSort("Selection Sort", largeArray.clone(), SearchingSorting::selectionSort);
        compareSort("Insertion Sort", largeArray.clone(), SearchingSorting::insertionSort);
        compareSort("Merge Sort", largeArray.clone(),
            arr -> mergeSort(arr, 0, arr.length - 1));
        compareSort("Quick Sort", largeArray.clone(),
            arr -> quickSort(arr, 0, arr.length - 1));
        compareSort("Arrays.sort()", largeArray.clone(), Arrays::sort);

        // ========================================================================
        // SUMMARY
        // ========================================================================
        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM COMPLEXITY SUMMARY:                                   ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║  SEARCHING:                                                      ║");
        System.out.println("║    Linear Search:  O(n)                                          ║");
        System.out.println("║    Binary Search:  O(log n) - requires sorted array              ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║  SORTING:                                                        ║");
        System.out.println("║    Bubble Sort:    O(n²) - simple, educational                   ║");
        System.out.println("║    Selection Sort: O(n²) - minimal swaps                         ║");
        System.out.println("║    Insertion Sort: O(n²) - good for nearly sorted                ║");
        System.out.println("║    Merge Sort:     O(n log n) - stable, needs extra space        ║");
        System.out.println("║    Quick Sort:     O(n log n) - fastest in practice              ║");
        System.out.println("║    Heap Sort:      O(n log n) - in-place, not stable             ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║  RECOMMENDATIONS:                                                ║");
        System.out.println("║    • Small data: Insertion Sort                                  ║");
        System.out.println("║    • General use: Quick Sort or Merge Sort                       ║");
        System.out.println("║    • Java built-in: Arrays.sort() (Dual-Pivot QuickSort)         ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
    }

    // ============================================================================
    // SEARCHING ALGORITHMS
    // ============================================================================

    /**
     * LINEAR SEARCH
     * Check each element sequentially until found
     * Time: O(n), Space: O(1)
     */
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;  // Found at index i
            }
        }
        return -1;  // Not found
    }

    /**
     * BINARY SEARCH (Recursive)
     * Divide array in half, search in appropriate half
     * Requires sorted array
     * Time: O(log n), Space: O(log n) for recursion
     */
    public static int binarySearch(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1;  // Not found
        }

        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            return mid;  // Found
        } else if (arr[mid] > target) {
            return binarySearch(arr, target, left, mid - 1);  // Search left
        } else {
            return binarySearch(arr, target, mid + 1, right);  // Search right
        }
    }

    // ============================================================================
    // SORTING ALGORITHMS
    // ============================================================================

    /**
     * BUBBLE SORT
     * Repeatedly swap adjacent elements if in wrong order
     * Time: O(n²), Space: O(1)
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            // Optimization: stop if no swaps occurred
            if (!swapped) break;
        }
    }

    /**
     * SELECTION SORT
     * Find minimum element and place at beginning
     * Time: O(n²), Space: O(1)
     */
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            // Swap found minimum with first element
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    /**
     * INSERTION SORT
     * Build sorted array one element at a time
     * Time: O(n²), Space: O(1)
     * Best for: Small or nearly sorted arrays
     */
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            // Move elements greater than key one position ahead
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    /**
     * MERGE SORT
     * Divide and conquer: split, sort, merge
     * Time: O(n log n), Space: O(n)
     * Stable: Yes
     */
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        while (i < n1) arr[k++] = leftArr[i++];
        while (j < n2) arr[k++] = rightArr[j++];
    }

    /**
     * QUICK SORT
     * Divide and conquer: partition around pivot
     * Time: O(n log n) average, O(n²) worst
     * Space: O(log n)
     */
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // ============================================================================
    // HELPER METHODS
    // ============================================================================

    private static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(100000);
        }
        return arr;
    }

    private static void compareSort(String name, int[] arr, java.util.function.Consumer<int[]> sortFunc) {
        long start = System.currentTimeMillis();
        sortFunc.accept(arr);
        long end = System.currentTimeMillis();
        System.out.printf("%-15s: %6d ms%n", name, (end - start));
    }
}
