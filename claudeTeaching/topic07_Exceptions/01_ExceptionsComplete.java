package topic07_Exceptions;

/**
 * ============================================================================
 * TOPIC 7: EXCEPTIONS - Complete Guide
 * ============================================================================
 *
 * EXCEPTIONS are events that disrupt the normal flow of program execution.
 * They represent error conditions that occur during runtime.
 *
 * EXCEPTION HIERARCHY:
 * Throwable
 * ├── Error (serious problems, don't catch these)
 * │   ├── OutOfMemoryError
 * │   ├── StackOverflowError
 * │   └── ...
 * └── Exception (conditions that programs should catch)
 *     ├── RuntimeException (unchecked - programming errors)
 *     │   ├── NullPointerException
 *     │   ├── ArrayIndexOutOfBoundsException
 *     │   ├── IllegalArgumentException
 *     │   └── ...
 *     └── Other Exceptions (checked - must handle or declare)
 *         ├── IOException
 *         ├── SQLException
 *         └── ...
 *
 * CHECKED vs UNCHECKED:
 * - Checked: Compiler forces you to handle or declare (IOException, SQLException)
 * - Unchecked: Runtime exceptions, don't need to declare (NullPointerException)
 */

import java.io.*;
import java.util.*;

public class ExceptionsComplete {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║     TOPIC 7: EXCEPTIONS - Complete Guide                         ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝\n");

        // ========================================================================
        // SECTION 1: TRY-CATCH BASICS
        // ========================================================================
        System.out.println("--- SECTION 1: Try-Catch Basics ---");
        System.out.println("Catch exceptions to prevent program crashes\n");

        // Example 1: Division by zero
        System.out.println("Example 1: Division by zero");
        try {
            int result = 10 / 0;  // This throws ArithmeticException
            System.out.println("Result: " + result);  // This won't execute
        } catch (ArithmeticException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
        System.out.println("Program continues after catch block\n");

        // Example 2: Array index out of bounds
        System.out.println("Example 2: Array index out of bounds");
        int[] numbers = {1, 2, 3};
        try {
            System.out.println(numbers[10]);  // Throws ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid index: " + e.getMessage());
        }

        // ========================================================================
        // SECTION 2: MULTIPLE CATCH BLOCKS
        // ========================================================================
        System.out.println("\n--- SECTION 2: Multiple Catch Blocks ---");
        System.out.println("Order matters: most specific first, most general last\n");

        String[] inputs = {"123", "abc", null};

        for (String input : inputs) {
            try {
                System.out.println("Processing: " + input);
                int num = Integer.parseInt(input);  // May throw NumberFormatException
                int result = 100 / num;             // May throw ArithmeticException
                System.out.println("Result: " + result);
            } catch (NumberFormatException e) {
                System.out.println("  Not a valid number!");
            } catch (ArithmeticException e) {
                System.out.println("  Cannot divide by zero!");
            } catch (Exception e) {
                System.out.println("  Some other error: " + e.getClass().getSimpleName());
            }
        }

        // ========================================================================
        // SECTION 3: FINALLY BLOCK
        // ========================================================================
        System.out.println("\n--- SECTION 3: Finally Block ---");
        System.out.println("Finally ALWAYS executes, whether exception occurs or not\n");

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("nonexistent.txt"));
            System.out.println("File opened successfully");
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } finally {
            System.out.println("Finally block executed");
            if (scanner != null) {
                scanner.close();
                System.out.println("Scanner closed in finally");
            }
        }

        // ========================================================================
        // SECTION 4: TRY-WITH-RESOURCES
        // ========================================================================
        System.out.println("\n--- SECTION 4: Try-With-Resources ---");
        System.out.println("Automatic resource management (Java 7+)\n");

        // Resources are automatically closed, even if exception occurs
        try (Scanner autoScanner = new Scanner("1 2 3")) {
            while (autoScanner.hasNextInt()) {
                System.out.println("Read: " + autoScanner.nextInt());
            }
        }  // autoScanner is automatically closed here
        System.out.println("Scanner automatically closed");

        // Multiple resources
        System.out.println("\nMultiple resources:");
        try (Scanner sc = new Scanner("Hello World");
             PrintWriter pw = new PrintWriter(new FileWriter("temp.txt"))) {
            pw.println(sc.nextLine());
            System.out.println("Data written to file");
        } catch (IOException e) {
            System.out.println("IO Error: " + e.getMessage());
        }

        // Clean up temp file
        new File("temp.txt").delete();

        // ========================================================================
        // SECTION 5: THROWING EXCEPTIONS
        // ========================================================================
        System.out.println("\n--- SECTION 5: Throwing Exceptions ---");
        System.out.println("Use 'throw' to create and throw exceptions\n");

        try {
            validateAge(15);  // Will throw exception
        } catch (IllegalArgumentException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }

        try {
            validateAge(25);  // Will succeed
            System.out.println("Age is valid!");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }

        // ========================================================================
        // SECTION 6: CUSTOM EXCEPTIONS
        // ========================================================================
        System.out.println("\n--- SECTION 6: Custom Exceptions ---");
        System.out.println("Create your own exception types for domain-specific errors\n");

        BankAccount account = new BankAccount(100);
        System.out.println("Account balance: $" + account.getBalance());

        try {
            account.withdraw(150);  // Will throw InsufficientFundsException
        } catch (InsufficientFundsException e) {
            System.out.println("Transaction failed: " + e.getMessage());
            System.out.println("Shortage: $" + e.getShortageAmount());
        }

        try {
            account.withdraw(50);  // Will succeed
            System.out.println("Withdrawal successful!");
            System.out.println("New balance: $" + account.getBalance());
        } catch (InsufficientFundsException e) {
            System.out.println("Transaction failed: " + e.getMessage());
        }

        // ========================================================================
        // SECTION 7: EXCEPTION METHODS
        // ========================================================================
        System.out.println("\n--- SECTION 7: Exception Methods ---");

        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Exception class: " + e.getClass().getName());
            System.out.println("Message: " + e.getMessage());
            System.out.println("toString(): " + e.toString());
            System.out.println("\nStack trace:");
            e.printStackTrace();
        }

        // ========================================================================
        // SECTION 8: BEST PRACTICES
        // ========================================================================
        System.out.println("\n--- SECTION 8: Best Practices ---");

        System.out.println("\n1. Catch specific exceptions, not just Exception");
        System.out.println("   BAD:  catch (Exception e)");
        System.out.println("   GOOD: catch (FileNotFoundException e)");

        System.out.println("\n2. Don't swallow exceptions");
        System.out.println("   BAD:  catch (Exception e) { }  // Empty catch!");
        System.out.println("   GOOD: catch (Exception e) { log.error(e); }");

        System.out.println("\n3. Use try-with-resources for AutoCloseable objects");
        System.out.println("   BAD:  try { ... } finally { resource.close(); }");
        System.out.println("   GOOD: try (Resource r = ...) { ... }");

        System.out.println("\n4. Don't use exceptions for flow control");
        System.out.println("   BAD:  try { return stack.pop(); } catch (EmptyStackException e) { return null; }");
        System.out.println("   GOOD: if (!stack.isEmpty()) return stack.pop(); else return null;");

        System.out.println("\n5. Document exceptions with @throws");
        System.out.println("   /**");
        System.out.println("    * @throws IllegalArgumentException if amount is negative");
        System.out.println("    */");

        // ========================================================================
        // SUMMARY
        // ========================================================================
        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║  EXCEPTION HANDLING KEYWORDS:                                    ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║  try:      Block to monitor for exceptions                       ║");
        System.out.println("║  catch:    Block to handle specific exceptions                   ║");
        System.out.println("║  finally:  Block that ALWAYS executes                          ║");
        System.out.println("║  throw:    Create and throw an exception                         ║");
        System.out.println("║  throws:   Declare exceptions a method might throw               ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║  CHECKED vs UNCHECKED:                                           ║");
        System.out.println("║  • Checked:   Must handle or declare (IOException, etc.)          ║");
        System.out.println("║  • Unchecked: Runtime exceptions, optional handling              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
    }

    /**
     * Method that validates age and throws exception if invalid
     * @throws IllegalArgumentException if age is less than 18
     */
    public static void validateAge(int age) {
        if (age < 18) {
            // Create and throw a new exception
            throw new IllegalArgumentException("Age must be at least 18. Provided: " + age);
        }
    }
}

// ============================================================================
// CUSTOM EXCEPTION CLASS
// ============================================================================
/**
 * Custom checked exception for insufficient funds
 * Extends Exception for checked, RuntimeException for unchecked
 */
class InsufficientFundsException extends Exception {
    // Additional field specific to this exception
    private double shortage;

    public InsufficientFundsException(double shortage) {
        super("Insufficient funds. Shortage: $" + shortage);
        this.shortage = shortage;
    }

    public double getShortageAmount() {
        return shortage;
    }
}

// ============================================================================
// BANK ACCOUNT CLASS
// ============================================================================
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    /**
     * Withdraw money from account
     * @param amount Amount to withdraw
     * @throws InsufficientFundsException if amount exceeds balance
     */
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException(amount - balance);
        }
        balance -= amount;
    }
}
