package dataStructures;

import java.util.Arrays;

/**
 * ============================================================================
 * DATA STRUCTURES: ARRAYS
 * ============================================================================
 *
 * An array is a collection of elements of the same type stored in contiguous
 * memory locations. It provides O(1) access by index.
 *
 * TIME COMPLEXITY:
 * - Access: O(1)
 * - Search: O(n)
 * - Insertion: O(n) - need to shift elements
 * - Deletion: O(n) - need to shift elements
 *
 * SPACE COMPLEXITY: O(n)
 */

public class ArraysDS {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║     DATA STRUCTURE: ARRAYS                                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝\n");

        // ========================================================================
        // ARRAY DECLARATION AND INITIALIZATION
        // ========================================================================
        System.out.println("--- Array Declaration and Initialization ---\n");

        // Method 1: Declare and allocate
        int[] arr1 = new int[5];  // 5 elements, initialized to 0
        System.out.println("Array 1 (default values): " + Arrays.toString(arr1));

        // Method 2: Declare with initial values
        int[] arr2 = {10, 20, 30, 40, 50};
        System.out.println("Array 2 (initialized): " + Arrays.toString(arr2));

        // Method 3: Anonymous array
        int[] arr3 = new int[] {1, 2, 3, 4, 5};
        System.out.println("Array 3 (anonymous): " + Arrays.toString(arr3));

        // ========================================================================
        // ARRAY OPERATIONS
        // ========================================================================
        System.out.println("\n--- Array Operations ---\n");

        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original array: " + Arrays.toString(arr));

        // Access element
        System.out.println("Element at index 2: " + arr[2]);

        // Modify element
        arr[2] = 100;
        System.out.println("After modification: " + Arrays.toString(arr));

        // Get length
        System.out.println("Array length: " + arr.length);

        // ========================================================================
        // DYNAMIC ARRAY (ArrayList concept)
        // ========================================================================
        System.out.println("\n--- Dynamic Array Implementation ---\n");

        DynamicArray<Integer> dynamicArray = new DynamicArray<>();
        System.out.println("Adding elements to dynamic array:");
        for (int i = 1; i <= 10; i++) {
            dynamicArray.add(i);
            System.out.println("Added " + i + ", Size: " + dynamicArray.size() +
                ", Capacity: " + dynamicArray.capacity());
        }

        System.out.println("\nDynamic array contents: " + dynamicArray);

        // Remove element
        dynamicArray.removeAt(5);
        System.out.println("After removing index 5: " + dynamicArray);

        // ========================================================================
        // COMMON ARRAY ALGORITHMS
        // ========================================================================
        System.out.println("\n--- Common Array Algorithms ---\n");

        int[] numbers = {3, 7, 2, 9, 1, 5, 8};
        System.out.println("Array: " + Arrays.toString(numbers));

        // Find maximum
        int max = findMax(numbers);
        System.out.println("Maximum: " + max);

        // Find minimum
        int min = findMin(numbers);
        System.out.println("Minimum: " + min);

        // Linear search
        int target = 5;
        int index = linearSearch(numbers, target);
        System.out.println("Index of " + target + ": " + index);

        // Reverse array
        reverseArray(numbers);
        System.out.println("Reversed: " + Arrays.toString(numbers));

        // ========================================================================
        // 2D ARRAYS (MATRICES)
        // ========================================================================
        System.out.println("\n--- 2D Arrays (Matrices) ---\n");

        // Declaration
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        System.out.println("Matrix:");
        printMatrix(matrix);

        // Access element
        System.out.println("Element at [1][2]: " + matrix[1][2]);

        // Get dimensions
        System.out.println("Rows: " + matrix.length);
        System.out.println("Columns: " + matrix[0].length);

        // Transpose
        int[][] transposed = transpose(matrix);
        System.out.println("\nTransposed:");
        printMatrix(transposed);

        // ========================================================================
        // SUMMARY
        // ========================================================================
        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║  ARRAYS SUMMARY:                                                 ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║  Pros:                                                           ║");
        System.out.println("║    • O(1) random access                                          ║");
        System.out.println("║    • Cache friendly (contiguous memory)                          ║");
        System.out.println("║    • Simple and efficient                                        ║");
        System.out.println("║                                                                  ║");
        System.out.println("║  Cons:                                                           ║");
        System.out.println("║    • Fixed size (static arrays)                                  ║");
        System.out.println("║    • Insertion/Deletion is O(n)                                  ║");
        System.out.println("║    • Wasted space if not full                                    ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║  Time Complexity:                                                ║");
        System.out.println("║    Access:  O(1)                                                 ║");
        System.out.println("║    Search:  O(n)                                                 ║");
        System.out.println("║    Insert:  O(n)                                                 ║");
        System.out.println("║    Delete:  O(n)                                                 ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
    }

    // Dynamic Array implementation
    static class DynamicArray<T> {
        private Object[] data;
        private int size;
        private int capacity;

        public DynamicArray() {
            this.capacity = 10;
            this.data = new Object[capacity];
            this.size = 0;
        }

        public void add(T element) {
            if (size == capacity) {
                resize();
            }
            data[size++] = element;
        }

        @SuppressWarnings("unchecked")
        public T get(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
            return (T) data[index];
        }

        public void removeAt(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
            System.arraycopy(data, index + 1, data, index, size - index - 1);
            data[--size] = null;
        }

        private void resize() {
            capacity *= 2;
            data = Arrays.copyOf(data, capacity);
        }

        public int size() { return size; }
        public int capacity() { return capacity; }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < size; i++) {
                sb.append(data[i]);
                if (i < size - 1) sb.append(", ");
            }
            return sb.append("]").toString();
        }
    }

    // Find maximum
    public static int findMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) max = num;
        }
        return max;
    }

    // Find minimum
    public static int findMin(int[] arr) {
        int min = arr[0];
        for (int num : arr) {
            if (num < min) min = num;
        }
        return min;
    }

    // Linear search
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    // Reverse array in place
    public static void reverseArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    // Print matrix
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    // Transpose matrix
    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }
}
