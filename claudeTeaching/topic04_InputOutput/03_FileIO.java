package topic04_InputOutput;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * ============================================================================
 * TOPIC 4: INPUT/OUTPUT - File Operations
 * ============================================================================
 *
 * This file demonstrates reading from and writing to files.
 *
 * READING METHODS:
 * 1. Scanner - Simple, good for parsing
 * 2. BufferedReader - Efficient for large files
 * 3. Files.readAllLines() - Java 7+ convenience method
 * 4. Files.readString() - Java 11+ reads entire file
 *
 * WRITING METHODS:
 * 1. FileWriter - Basic writing
 * 2. BufferedWriter - Efficient writing
 * 3. PrintWriter - Convenient formatting
 * 4. Files.write() - Java 7+ convenience method
 *
 * BEST PRACTICE: Always use try-with-resources for automatic cleanup!
 */

public class FileIO {
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║     TOPIC 4: FILE INPUT/OUTPUT                                   ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝\n");

        // Define file paths
        String inputFile = "sample_input.txt";
        String outputFile = "sample_output.txt";
        String binaryFile = "sample_binary.dat";

        // ========================================================================
        // SECTION 1: CREATE SAMPLE INPUT FILE
        // ========================================================================
        System.out.println("--- Creating Sample Input File ---");
        createSampleFile(inputFile);

        // ========================================================================
        // SECTION 2: READING WITH SCANNER
        // ========================================================================
        System.out.println("\n--- SECTION 2: Reading with Scanner ---");
        System.out.println("Scanner is easy to use and good for parsing.\n");

        // Method 1: Scanner with try-with-resources
        // try-with-resources automatically closes the scanner
        try (Scanner fileScanner = new Scanner(new File(inputFile))) {
            System.out.println("Reading file line by line:");
            int lineNum = 1;
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println("Line " + lineNum + ": " + line);
                lineNum++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + e.getMessage());
        }

        // Method 2: Scanner with delimiter (token-based reading)
        System.out.println("\nReading tokens (words/numbers):");
        try (Scanner tokenScanner = new Scanner(new File(inputFile))) {
            // Use whitespace as delimiter (default)
            int count = 0;
            while (tokenScanner.hasNext() && count < 10) {
                String token = tokenScanner.next();
                System.out.print(token + " ");
                count++;
            }
            System.out.println("...");
        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }

        // ========================================================================
        // SECTION 3: READING WITH BUFFEREDREADER
        // ========================================================================
        System.out.println("\n--- SECTION 3: Reading with BufferedReader ---");
        System.out.println("BufferedReader is more efficient for large files.\n");

        // Method 1: Read line by line
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            System.out.println("Reading with BufferedReader:");
            String line;
            int lineNum = 1;
            while ((line = br.readLine()) != null) {
                System.out.println("  " + lineNum + ": " + line);
                lineNum++;
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        // Method 2: Read all lines into a List (Java 8+)
        System.out.println("\nReading all lines into List:");
        try {
            List<String> lines = Files.readAllLines(Paths.get(inputFile));
            for (int i = 0; i < lines.size(); i++) {
                System.out.println("  " + (i + 1) + ": " + lines.get(i));
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        // Method 3: Read entire file as String (Java 11+)
        System.out.println("\nReading entire file as String:");
        try {
            String content = Files.readString(Paths.get(inputFile));
            System.out.println("File size: " + content.length() + " characters");
            System.out.println("First 100 chars: " + content.substring(0, Math.min(100, content.length())));
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        // ========================================================================
        // SECTION 4: WRITING TO FILES
        // ========================================================================
        System.out.println("\n--- SECTION 4: Writing to Files ---");

        // Method 1: FileWriter (basic)
        System.out.println("\nWriting with FileWriter:");
        try (FileWriter fw = new FileWriter(outputFile)) {
            fw.write("Line 1: Written with FileWriter\n");
            fw.write("Line 2: Simple but not buffered\n");
            System.out.println("Written to " + outputFile);
        } catch (IOException e) {
            System.err.println("Error writing: " + e.getMessage());
        }

        // Method 2: BufferedWriter (efficient)
        System.out.println("\nWriting with BufferedWriter:");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, true))) {
            // 'true' parameter appends to file instead of overwriting
            bw.write("Line 3: Written with BufferedWriter");
            bw.newLine();  // Platform-independent newline
            bw.write("Line 4: More efficient for multiple writes");
            bw.newLine();
            System.out.println("Appended to " + outputFile);
        } catch (IOException e) {
            System.err.println("Error writing: " + e.getMessage());
        }

        // Method 3: PrintWriter (convenient formatting)
        System.out.println("\nWriting with PrintWriter:");
        try (PrintWriter pw = new PrintWriter(new FileWriter(outputFile, true))) {
            pw.println("Line 5: Written with PrintWriter");
            pw.println("Line 6: Easy to use like System.out");
            pw.printf("Line 7: Formatted number: %.2f%n", 3.14159);
            pw.printf("Line 8: Integer: %d, String: %s%n", 42, "Hello");
            System.out.println("Appended formatted text to " + outputFile);
        } catch (IOException e) {
            System.err.println("Error writing: " + e.getMessage());
        }

        // Method 4: Files.write() (Java 7+ convenience)
        System.out.println("\nWriting with Files.write():");
        try {
            List<String> newLines = Arrays.asList(
                "Line 9: Modern approach",
                "Line 10: Using NIO.2"
            );
            Files.write(Paths.get(outputFile), newLines,
                StandardOpenOption.APPEND,
                StandardOpenOption.CREATE);
            System.out.println("Written using NIO.2");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        // ========================================================================
        // SECTION 5: BINARY FILE I/O
        // ========================================================================
        System.out.println("\n--- SECTION 5: Binary File I/O ---");
        System.out.println("Use byte streams for non-text files (images, audio, etc.)\n");

        // Writing binary data
        System.out.println("Writing binary data:");
        try (DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(binaryFile)))) {
            dos.writeInt(42);
            dos.writeDouble(3.14159);
            dos.writeUTF("Hello Binary!");  // UTF string
            dos.writeBoolean(true);
            System.out.println("Written binary data to " + binaryFile);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        // Reading binary data
        System.out.println("\nReading binary data:");
        try (DataInputStream dis = new DataInputStream(
                new BufferedInputStream(new FileInputStream(binaryFile)))) {
            int intVal = dis.readInt();
            double doubleVal = dis.readDouble();
            String strVal = dis.readUTF();
            boolean boolVal = dis.readBoolean();

            System.out.println("  Integer: " + intVal);
            System.out.println("  Double: " + doubleVal);
            System.out.println("  String: " + strVal);
            System.out.println("  Boolean: " + boolVal);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        // ========================================================================
        // SECTION 6: FILE OPERATIONS WITH NIO.2
        // ========================================================================
        System.out.println("\n--- SECTION 6: NIO.2 File Operations ---");

        Path sourcePath = Paths.get(inputFile);
        Path copyPath = Paths.get("copy_of_" + inputFile);

        // Check if file exists
        System.out.println("File exists: " + Files.exists(sourcePath));
        System.out.println("Is readable: " + Files.isReadable(sourcePath));
        System.out.println("Is writable: " + Files.isWritable(sourcePath));
        System.out.println("File size: " + Files.size(sourcePath) + " bytes");
        System.out.println("Last modified: " + Files.getLastModifiedTime(sourcePath));

        // Copy file
        try {
            Files.copy(sourcePath, copyPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("\nCopied file to: " + copyPath);
        } catch (IOException e) {
            System.err.println("Copy error: " + e.getMessage());
        }

        // Move/Rename file
        Path movedPath = Paths.get("moved_" + inputFile);
        try {
            Files.move(copyPath, movedPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Moved file to: " + movedPath);

            // Clean up - delete the moved file
            Files.delete(movedPath);
            System.out.println("Deleted: " + movedPath);
        } catch (IOException e) {
            System.err.println("Move/Delete error: " + e.getMessage());
        }

        // Create directory
        Path newDir = Paths.get("test_directory");
        try {
            Files.createDirectories(newDir);
            System.out.println("\nCreated directory: " + newDir);

            // Clean up
            Files.delete(newDir);
            System.out.println("Deleted directory: " + newDir);
        } catch (IOException e) {
            System.err.println("Directory error: " + e.getMessage());
        }

        // ========================================================================
        // SECTION 7: READING CSV DATA
        // ========================================================================
        System.out.println("\n--- SECTION 7: Reading CSV Data ---");

        String csvFile = "sample_data.csv";
        createSampleCSV(csvFile);

        System.out.println("Reading CSV file:");
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String header = br.readLine();
            System.out.println("Header: " + header);

            String line;
            while ((line = br.readLine()) != null) {
                // Split by comma
                String[] fields = line.split(",");
                System.out.println("Record: " + Arrays.toString(fields));
            }
        } catch (IOException e) {
            System.err.println("CSV error: " + e.getMessage());
        }

        // ========================================================================
        // CLEANUP
        // ========================================================================
        System.out.println("\n--- Cleaning Up Sample Files ---");
        deleteIfExists(inputFile);
        deleteIfExists(outputFile);
        deleteIfExists(binaryFile);
        deleteIfExists(csvFile);

        // ========================================================================
        // SUMMARY
        // ========================================================================
        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║  READING FILES:                                                  ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║  Scanner:        Easy parsing, good for small files              ║");
        System.out.println("║  BufferedReader: Efficient for large files                       ║");
        System.out.println("║  Files class:    Modern, convenient methods                      ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║  WRITING FILES:                                                  ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║  FileWriter:     Basic writing                                   ║");
        System.out.println("║  BufferedWriter: Efficient for multiple writes                 ║");
        System.out.println("║  PrintWriter:    Convenient formatting                           ║");
        System.out.println("║  Files.write():  Modern approach                                 ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║  BEST PRACTICES:                                                 ║");
        System.out.println("║  • Always use try-with-resources                                 ║");
        System.out.println("║  • Use Buffered streams for large files                        ║");
        System.out.println("║  • Handle IOException properly                                   ║");
        System.out.println("║  • Use NIO.2 for new code                                        ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
    }

    // Helper method to create sample file
    private static void createSampleFile(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println("This is a sample file for demonstrating file I/O.");
            pw.println("Java provides many ways to read and write files.");
            pw.println("Line 3: Scanner is easy to use.");
            pw.println("Line 4: BufferedReader is efficient.");
            pw.println("Line 5: NIO.2 is modern and powerful.");
            pw.println("123 456 789");
            pw.println("3.14 2.71 1.41");
            System.out.println("Created: " + filename);
        } catch (IOException e) {
            System.err.println("Error creating sample file: " + e.getMessage());
        }
    }

    // Helper method to create sample CSV
    private static void createSampleCSV(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println("Name,Age,City,GPA");
            pw.println("Alice,20,New York,3.8");
            pw.println("Bob,22,Los Angeles,3.5");
            pw.println("Charlie,21,Chicago,3.9");
            pw.println("Diana,23,Houston,3.7");
            System.out.println("Created: " + filename);
        } catch (IOException e) {
            System.err.println("Error creating CSV: " + e.getMessage());
        }
    }

    // Helper method to delete file if exists
    private static void deleteIfExists(String filename) {
        try {
            Files.deleteIfExists(Paths.get(filename));
            System.out.println("Deleted: " + filename);
        } catch (IOException e) {
            System.err.println("Error deleting " + filename + ": " + e.getMessage());
        }
    }
}
