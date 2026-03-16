package topic06_Recursion;

/**
 * ============================================================================
 * TOPIC 6: RECURSION - Basic Concepts and Examples
 * ============================================================================
 *
 * Recursion is a method calling itself to solve smaller instances of the same problem.
 *
 * RECURSIVE METHOD STRUCTURE:
 * 1. BASE CASE: Condition that stops recursion (prevents infinite loop)
 * 2. RECURSIVE CASE: Method calls itself with modified parameters
 *
 * VISUALIZATION OF RECURSION:
 * factorial(5)
 *     5 * factorial(4)
 *         4 * factorial(3)
 *             3 * factorial(2)
 *                 2 * factorial(1)
 *                     1  [BASE CASE - returns 1]
 *                 2 * 1 = 2
 *             3 * 2 = 6
 *         4 * 6 = 24
 *     5 * 24 = 120
 */

public class RecursionBasics {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║     TOPIC 6: RECURSION - Basic Concepts                          ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝\n");

        // ========================================================================
        // SECTION 1: FACTORIAL
        // ========================================================================
        System.out.println("--- SECTION 1: Factorial ---");
        System.out.println("n! = n * (n-1) * (n-2) * ... * 1");
        System.out.println("Base case: 0! = 1 and 1! = 1\n");

        for (int i = 0; i <= 5; i++) {
            System.out.printf("factorial(%d) = %d%n", i, factorial(i));
        }

        // ========================================================================
        // SECTION 2: FIBONACCI
        // ========================================================================
        System.out.println("\n--- SECTION 2: Fibonacci ---");
        System.out.println("F(n) = F(n-1) + F(n-2)");
        System.out.println("Base case: F(0) = 0, F(1) = 1\n");

        System.out.print("Fibonacci sequence: ");
        for (int i = 0; i <= 10; i++) {
            System.out.print(fibonacci(i) + " ");
        }
        System.out.println();

        // ========================================================================
        // SECTION 3: POWER CALCULATION
        // ========================================================================
        System.out.println("\n--- SECTION 3: Power Calculation ---");
        System.out.println("x^n = x * x^(n-1)");
        System.out.println("Base case: x^0 = 1\n");

        System.out.println("2^10 = " + power(2, 10));
        System.out.println("5^3 = " + power(5, 3));
        System.out.println("10^0 = " + power(10, 0));

        // ========================================================================
        // SECTION 4: SUM OF DIGITS
        // ========================================================================
        System.out.println("\n--- SECTION 4: Sum of Digits ---");
        System.out.println("sum(123) = 3 + sum(12) = 3 + 2 + sum(1) = 3 + 2 + 1 = 6\n");

        int[] numbers = {123, 456, 789, 1000};
        for (int num : numbers) {
            System.out.println("sumOfDigits(" + num + ") = " + sumOfDigits(num));
        }

        // ========================================================================
        // SECTION 5: COUNT DOWN AND UP
        // ========================================================================
        System.out.println("\n--- SECTION 5: Count Down and Up ---");
        System.out.println("Demonstrates head vs tail recursion\n");

        System.out.println("Count down from 5:");
        countDown(5);

        System.out.println("\nCount up to 5:");
        countUp(5);

        // ========================================================================
        // SECTION 6: PALINDROME CHECK
        // ========================================================================
        System.out.println("\n--- SECTION 6: Palindrome Check ---");
        System.out.println("A palindrome reads the same forwards and backwards\n");

        String[] testStrings = {"radar", "hello", "racecar", "java"};
        for (String s : testStrings) {
            System.out.println(s + " is palindrome: " + isPalindrome(s));
        }

        // ========================================================================
        // SECTION 7: GCD (Greatest Common Divisor)
        // ========================================================================
        System.out.println("\n--- SECTION 7: GCD (Euclidean Algorithm) ---");
        System.out.println("gcd(a, b) = gcd(b, a % b)");
        System.out.println("Base case: gcd(a, 0) = a\n");

        System.out.println("gcd(48, 18) = " + gcd(48, 18));
        System.out.println("gcd(100, 35) = " + gcd(100, 35));
        System.out.println("gcd(17, 5) = " + gcd(17, 5));

        // ========================================================================
        // SECTION 8: REVERSE A STRING
        // ========================================================================
        System.out.println("\n--- SECTION 8: Reverse String ---");

        String[] strings = {"Hello", "World", "Recursion"};
        for (String s : strings) {
            System.out.println("reverse(\"" + s + "\") = \"" + reverse(s) + "\"");
        }

        // ========================================================================
        // SUMMARY
        // ========================================================================
        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║  RECURSION CHECKLIST:                                            ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║  1. Identify the base case(s)                                    ║");
        System.out.println("║  2. Ensure recursive calls move toward base case                 ║");
        System.out.println("║  3. Combine results from recursive calls                         ║");
        System.out.println("║  4. Watch out for stack overflow with deep recursion             ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
    }

    /**
     * FACTORIAL
     * n! = n * (n-1) * (n-2) * ... * 1
     * Base case: 0! = 1, 1! = 1
     */
    public static long factorial(int n) {
        // BASE CASE: factorial of 0 or 1 is 1
        if (n <= 1) {
            return 1;
        }
        // RECURSIVE CASE: n! = n * (n-1)!
        return n * factorial(n - 1);
    }

    /**
     * FIBONACCI
     * F(n) = F(n-1) + F(n-2)
     * Base case: F(0) = 0, F(1) = 1
     */
    public static int fibonacci(int n) {
        // BASE CASE
        if (n <= 1) {
            return n;
        }
        // RECURSIVE CASE
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /**
     * POWER
     * x^n = x * x^(n-1)
     * Base case: x^0 = 1
     */
    public static double power(double base, int exponent) {
        // BASE CASE: anything to power 0 is 1
        if (exponent == 0) {
            return 1;
        }
        // Handle negative exponents
        if (exponent < 0) {
            return 1 / power(base, -exponent);
        }
        // RECURSIVE CASE
        return base * power(base, exponent - 1);
    }

    /**
     * SUM OF DIGITS
     * sum(123) = 3 + sum(12) = 3 + 2 + sum(1) = 3 + 2 + 1 = 6
     * Base case: single digit number
     */
    public static int sumOfDigits(int n) {
        // BASE CASE: if n is a single digit
        if (n < 10) {
            return n;
        }
        // RECURSIVE CASE: last digit + sum of remaining digits
        // n % 10 gives the last digit
        // n / 10 removes the last digit
        return (n % 10) + sumOfDigits(n / 10);
    }

    /**
     * COUNT DOWN (Tail Recursion)
     * Prints numbers from n down to 1
     */
    public static void countDown(int n) {
        // BASE CASE
        if (n <= 0) {
            System.out.println("Done!");
            return;
        }
        // Process BEFORE recursive call
        System.out.println(n);
        // RECURSIVE CASE
        countDown(n - 1);
    }

    /**
     * COUNT UP (Head Recursion)
     * Prints numbers from 1 up to n
     */
    public static void countUp(int n) {
        // BASE CASE
        if (n <= 0) {
            return;
        }
        // RECURSIVE CALL FIRST
        countUp(n - 1);
        // Process AFTER recursive call
        System.out.println(n);
    }

    /**
     * PALINDROME CHECK
     * A string is palindrome if first and last chars match,
     * and the substring between them is also palindrome
     */
    public static boolean isPalindrome(String s) {
        // BASE CASE: empty string or single character
        if (s.length() <= 1) {
            return true;
        }
        // Check if first and last characters match
        if (s.charAt(0) != s.charAt(s.length() - 1)) {
            return false;
        }
        // RECURSIVE CASE: check substring without first and last chars
        return isPalindrome(s.substring(1, s.length() - 1));
    }

    /**
     * GCD (Euclidean Algorithm)
     * gcd(a, b) = gcd(b, a % b)
     * Base case: gcd(a, 0) = a
     */
    public static int gcd(int a, int b) {
        // BASE CASE
        if (b == 0) {
            return a;
        }
        // RECURSIVE CASE
        return gcd(b, a % b);
    }

    /**
     * REVERSE STRING
     * reverse("abc") = "c" + reverse("ab") = "c" + "b" + reverse("a") = "cba"
     */
    public static String reverse(String s) {
        // BASE CASE: empty or single character
        if (s.length() <= 1) {
            return s;
        }
        // RECURSIVE CASE: last char + reverse of rest
        return s.charAt(s.length() - 1) + reverse(s.substring(0, s.length() - 1));
    }
}
