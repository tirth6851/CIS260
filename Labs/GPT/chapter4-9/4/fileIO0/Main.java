import java.util.Scanner;         // Allows easy reading of tokens (words, ints, etc.)
import java.io.FileInputStream;   // For reading raw bytes from a file (input side)
import java.io.FileOutputStream;  // For writing raw bytes to a file (output side)
import java.io.PrintWriter;       // Wraps an output stream, gives us print/println/printf
import java.io.IOException;       // Needed because file operations can throw exceptions

public class Main {
    // "throws IOException" means this method might stop if a file error happens
    public static void main(String[] args) throws IOException {

        // --- Objects to handle INPUT from the file ---
        FileInputStream fileByteStream = null; // Low-level connection to input file
        Scanner inFS = null;                   // Scanner that reads tokens from that file

        // --- Objects to handle OUTPUT to the file ---
        FileOutputStream fileOutStream = null; // Low-level connection to output file
        PrintWriter outFS = null;              // Writer that prints text into that file

        // --- Variables to store data we read ---
        String name;   // Person's name from file
        int age;       // Person's age from file
        int num1;      // First number from file
        int num2;      // Second number from file
        int sum;       // Sum of num1 and num2

        // 1) Open the INPUT file "input.txt" for reading
        System.out.println("Opening input.txt for reading...");
        fileByteStream = new FileInputStream("input.txt"); // Connects to the file
        inFS = new Scanner(fileByteStream);                // Scanner will now read from file

        // 2) Read data from the file in the SAME ORDER it appears in input.txt
        // Example input.txt line:  Alex 19 5 10
        name = inFS.next();      // Reads the first token (e.g., "Alex")
        age  = inFS.nextInt();   // Reads the next token as an int (e.g., 19)
        num1 = inFS.nextInt();   // Reads next int (e.g., 5)
        num2 = inFS.nextInt();   // Reads next int (e.g., 10)

        // 3) Do the calculation (sum of the two numbers)
        sum = num1 + num2;       // Add num1 and num2, store result in sum

        // 4) Open the OUTPUT file "output.txt" for writing
        System.out.println("Opening output.txt for writing...");
        fileOutStream = new FileOutputStream("output.txt"); // Creates/overwrites file
        outFS = new PrintWriter(fileOutStream);             // Lets us use print/printf on it

        // 5) Write a formatted sentence into output.txt using printf
        // %s = String placeholder, %d = integer placeholder, %n = newline
        // This will create a line like:
        // Hi I am Alex, I am 19 years old and my number sum is 15
        outFS.pricdntf("Hi I am %s, I am %d years old and my number sum is %d%n",
                name, age, sum);

        // 6) Close all input and output resources (very important to avoid file locks/leaks)
        inFS.close();           // Close the Scanner reading the file
        fileByteStream.close(); // Close the underlying input stream

        outFS.close();          // Flush and close the PrintWriter
        fileOutStream.close();  // Close the underlying output stream

        // 7) Let the user know the program finished
        System.out.println("Done. Check output.txt for the result.");
    }
}
