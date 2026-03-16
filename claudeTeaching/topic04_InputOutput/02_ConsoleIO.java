package topic04_InputOutput;

import java.io.*;
import java.util.*;

/**
 * ============================================================================
 * TOPIC 4: INPUT/OUTPUT - Console I/O Operations
 * ============================================================================
 *
 * This file demonstrates various ways to read from and write to the console.
 *
 * METHODS COVERED:
 * 1. Scanner - Most versatile, easiest to use
 * 2. BufferedReader - Most efficient for large text
 * 3. Console - For password input (secure)
 * 4. System.out - Standard output
 */

public class ConsoleIO {
    public static void main(String[] args) throws IOException {
        System.out.println("╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║     TOPIC 4: CONSOLE INPUT/OUTPUT                                ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝\n");

        // ========================================================================
        // SECTION 1: SCANNER CLASS
        // ========================================================================
        System.out.println("--- SECTION 1: Scanner Class ---");
        System.out.println("Scanner is the most versatile way to read console input.");
        System.out.println("It can parse different data types automatically.\n");

        // Create a Scanner object that reads from System.in (keyboard)
        // System.in is the standard input stream (usually keyboard)
        Scanner scanner = new Scanner(System.in);

        // Reading a String (whole line)
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();  // Reads until Enter is pressed
        System.out.println("Hello, " + name + "!");

        // Reading different data types
        System.out.print("Enter your age: ");
        // nextInt() reads an integer
        // If user enters non-integer, InputMismatchException is thrown
        while (!scanner.hasNextInt()) {
            System.out.print("That's not a number! Enter your age: ");
            scanner.next();  // Clear the invalid input
        }
        int age = scanner.nextInt();
        System.out.println("You are " + age + " years old.");

        // IMPORTANT: After nextInt(), nextDouble(), etc.,
        // the newline character remains in the buffer!
        // We need to consume it before reading a line again
        scanner.nextLine();  // Consume the remaining newline

        System.out.print("Enter your favorite color: ");
        String color = scanner.nextLine();
        System.out.println(color + " is a nice color!");

        // Reading a double
        System.out.print("Enter your GPA (0.0 - 4.0): ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid! Enter a decimal number: ");
            scanner.next();
        }
        double gpa = scanner.nextDouble();
        System.out.printf("Your GPA is: %.2f%n", gpa);

        // Reading a boolean
        System.out.print("Are you a student? (true/false): ");
        while (!scanner.hasNextBoolean()) {
            System.out.print("Please enter 'true' or 'false': ");
            scanner.next();
        }
        boolean isStudent = scanner.nextBoolean();
        System.out.println("Student status: " + isStudent);

        // Reading a single word (stops at whitespace)
        scanner.nextLine();  // Clear buffer
        System.out.print("Enter a sentence: ");
        String firstWord = scanner.next();  // Reads only first word
        String restOfLine = scanner.nextLine();  // Reads the rest
        System.out.println("First word: " + firstWord);
        System.out.println("Rest: " + restOfLine);

        // ========================================================================
        // SECTION 2: BUFFEREDREADER
        // ========================================================================
        System.out.println("\n--- SECTION 2: BufferedReader ---");
        System.out.println("BufferedReader is more efficient for reading large amounts of text.");
        System.out.println("It's faster than Scanner but requires more setup.\n");

        // Create a BufferedReader that wraps an InputStreamReader
        // InputStreamReader converts bytes to characters
        // System.in is a byte stream (InputStream)
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter a line of text: ");
        String line = reader.readLine();  // Reads entire line
        System.out.println("You entered: " + line);

        // Reading multiple lines until empty line
        System.out.println("\nEnter multiple lines (empty line to quit):");
        StringBuilder multiLine = new StringBuilder();
        String input;
        while (!(input = reader.readLine()).isEmpty()) {
            multiLine.append(input).append("\n");
        }
        System.out.println("You entered:\n" + multiLine);

        // ========================================================================
        // SECTION 3: CONSOLE CLASS (Password Input)
        // ========================================================================
        System.out.println("\n--- SECTION 3: Console Class ---");
        System.out.println("Console is best for secure input like passwords.");
        System.out.println("Note: Console may not work in some IDEs!\n");

        // Get the Console object
        Console console = System.console();

        if (console != null) {
            // Read line with console
            String username = console.readLine("Enter username: ");

            // Read password (doesn't echo characters!)
            char[] passwordArray = console.readPassword("Enter password: ");
            String password = new String(passwordArray);

            // Clear the password array for security
            java.util.Arrays.fill(passwordArray, ' ');

            System.out.println("Username: " + username);
            System.out.println("Password length: " + password.length() + " characters");
        } else {
            System.out.println("Console not available (may be running in IDE)");
            System.out.println("Using Scanner as fallback...");

            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            System.out.println("Username: " + username);
        }

        // ========================================================================
        // SECTION 4: FORMATTED OUTPUT
        // ========================================================================
        System.out.println("\n--- SECTION 4: Formatted Output ---");

        // System.out.println() - Prints with newline
        System.out.println("This is println - adds newline at end");

        // System.out.print() - Prints without newline
        System.out.print("This is print - ");
        System.out.print("no newline between these");
        System.out.println();  // Just print newline

        // System.out.printf() - Formatted output (like C's printf)
        // Format specifiers:
        // %s - String
        // %d - Integer (decimal)
        // %f - Floating point
        // %c - Character
        // %b - Boolean
        // %n - Platform-independent newline
        // %t - Date/Time

        System.out.println("\nPrintf examples:");
        System.out.printf("String: %s%n", "Hello");
        System.out.printf("Integer: %d%n", 42);
        System.out.printf("Float: %f%n", 3.14159);
        System.out.printf("Formatted float: %.2f%n", 3.14159);  // 2 decimal places
        System.out.printf("Boolean: %b%n", true);
        System.out.printf("Character: %c%n", 'A');

        // Width and alignment
        System.out.println("\nWidth and alignment:");
        System.out.printf("|%-10s|%n", "Left");     // Left-aligned, width 10
        System.out.printf("|%10s|%n", "Right");    // Right-aligned, width 10
        System.out.printf("|%10d|%n", 42);         // Right-aligned number
        System.out.printf("|%-10d|%n", 42);        // Left-aligned number
        System.out.printf("|%010d|%n", 42);         // Pad with zeros

        // Multiple values
        System.out.println("\nMultiple values:");
        System.out.printf("Name: %s, Age: %d, GPA: %.2f%n", "Alice", 20, 3.85);

        // String.format() - Returns formatted string instead of printing
        String formatted = String.format("Pi to 4 decimals: %.4f", Math.PI);
        System.out.println("\nString.format result: " + formatted);

        // ========================================================================
        // SECTION 5: SCANNER ADVANCED FEATURES
        // ========================================================================
        System.out.println("\n--- SECTION 5: Scanner Advanced Features ---");

        // Using delimiter (default is whitespace)
        System.out.println("Enter numbers separated by commas (e.g., 1,2,3,4,5):");
        scanner.useDelimiter(",");  // Use comma as delimiter

        int sum = 0;
        while (scanner.hasNextInt()) {
            int num = scanner.nextInt();
            sum += num;
        }
        System.out.println("Sum of numbers: " + sum);

        // Reset delimiter to default
        scanner.reset();
        scanner.nextLine();  // Clear buffer

        // Reading from a String (not just System.in)
        System.out.println("\nReading from a String:");
        String data = "10 20.5 true Hello";
        Scanner stringScanner = new Scanner(data);

        int intVal = stringScanner.nextInt();
        double doubleVal = stringScanner.nextDouble();
        boolean boolVal = stringScanner.nextBoolean();
        String strVal = stringScanner.next();

        System.out.println("Int: " + intVal);
        System.out.println("Double: " + doubleVal);
        System.out.println("Boolean: " + boolVal);
        System.out.println("String: " + strVal);

        stringScanner.close();

        // ========================================================================
        // SECTION 6: ERROR HANDLING
        // ========================================================================
        System.out.println("\n--- SECTION 6: Error Handling ---");

        // Example of robust input handling
        int validNumber = readInt(scanner, "Enter a number between 1 and 100: ", 1, 100);
        System.out.println("Valid number entered: " + validNumber);

        // ========================================================================
        // CLEANUP
        // ========================================================================
        scanner.close();  // Close the scanner when done
        // Note: Closing System.in scanner means you can't read from console again!

        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║  SUMMARY:                                                        ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║  Scanner:                                                        ║");
        System.out.println("║    • Most versatile and easiest to use                           ║");
        System.out.println("║    • Can parse different data types                              ║");
        System.out.println("║    • Good for interactive programs                               ║");
        System.out.println("║                                                                  ║");
        System.out.println("║  BufferedReader:                                                 ║");
        System.out.println("║    • Most efficient for large text                               ║");
        System.out.println("║    • Only reads strings (manual parsing needed)                  ║");
        System.out.println("║    • Good for file reading                                       ║");
        System.out.println("║                                                                  ║");
        System.out.println("║  Console:                                                        ║");
        System.out.println("║    • Best for password input (secure)                            ║");
        System.out.println("║    • May not work in IDEs                                        ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
    }

    /**
     * Helper method to read a valid integer within a range
     * @param scanner The Scanner to use
     * @param prompt The prompt to display
     * @param min Minimum allowed value
     * @param max Maximum allowed value
     * @return Valid integer from user
     */
    private static int readInt(Scanner scanner, String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max);
                }
            } else {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();  // Clear invalid input
            }
        }
    }
}
