import java.util.Scanner;        // Scanner for easy token reading
import java.io.FileInputStream;  // For reading from a file
import java.io.IOException;      // Because file operations can throw exceptions
public class Main {
    // "throws IOException" means main might stop if there's a file error
    public static void main(String[] args) throws IOException {
        // 1) Declare a FileInputStream and a Scanner that will read from that stream
        FileInputStream fileByteStream = null; // Low-level byte stream from file
        Scanner inFS = null;                   // Scanner that wraps the stream

        // 2) Declare variables to hold the file data
        int num1;
        int num2;

        // 3) Try to open the file "nums.txt" for reading
        System.out.println("Opening file input.txt.");
        fileByteStream = new FileInputStream("input.txt");

        // 4) Attach the Scanner to the file stream instead of System.in
        inFS = new Scanner(fileByteStream);

        // 5) Read two integers from the file using Scanner
        System.out.println("Reading two integers from file.");
        num1 = inFS.nextInt();   // Reads first int from nums.txt
        num2 = inFS.nextInt();   // Reads second int from nums.txt

        // 6) Output the values and their sum
        System.out.println("num1: " + num1);
        System.out.println("num2: " + num2);
        System.out.println("sum: " + (num1 + num2));

        // 7) Close the file stream (very important!)
        System.out.println("Closing file nums.txt.");
        fileByteStream.close();
    }
}
