//import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String name;
        int age;

        //FileInputStream inStream = new FileInputStream("in.txt");
        //Scanner inFS = null;
        FileOutputStream outStream = null;
        PrintWriter outFS = null;
        outStream = new FileOutputStream("results.txt");
        outFS = new PrintWriter(outStream);







        inFS.close();
        inStream.close();
        outFS.close();
        outStream.close();

    }
}