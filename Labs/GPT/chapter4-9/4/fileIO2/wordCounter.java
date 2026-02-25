import java.io.*;
import java.util.Scanner;

public class WordCounter {
    public static void main(String[] args) throws IOException {

        Scanner kb = new Scanner(System.in);   // keyboard
        FileInputStream inStream = null;
        Scanner inFS = null;

        FileOutputStream outStream = null;
        PrintWriter outFS = null;

        String fileName;
        String searchWord;
        String currWord;
        int count = 0;

        // 1) Ask user for file name, then for search word
        System.out.print("Enter input file name: ");
        // TODO: read fileName from keyboard
        fileName = kb.nextLine();

        System.out.print("Enter word to search for: ");
        // TODO: read searchWord from keyboard
        searchWord = kb.nextLine();

        // 2) Open that file for reading
        // TODO: create FileInputStream with fileName
        // TODO: create Scanner inFS from that stream
        FileInputStream file = new FileInputStream(fileName);
        Scanner inFS = new Scanner(file);

        // 3) Open counts.txt for writing
        // TODO: create FileOutputStream for "counts.txt"
        // TODO: create PrintWriter outFS from that stream
        inStream = new FileInputStream(fileName);

        // 4) Loop through all words in the input file
        // Hint: use inFS.hasNext() and inFS.next()
        // TODO: while (...)
        //          read currWord
        //          if it equals searchWord, increment count

        // 5) Build result sentence
        // Example: apple appears 2 times.
        String result = searchWord + " appears " + count + " times.";

        // 6) Print to screen
        // TODO: System.out.println(result);

        // 7) Print to counts.txt
        // TODO: outFS.println(result);

        // 8) Close everything
        kb.close();
        inFS.close();
        inStream.close();
        outFS.close();
        outStream.close();
    }
}
