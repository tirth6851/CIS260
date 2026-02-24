import java.util.Scanner;//scan any input
import java.io.FileInputStream;// scan .txt inputs
import java.io.FileOutputStream;//out put to .txt
import java.io.PrintWriter;//helps use regular print functions for file out put
import java.io.IOException;// not beak pc if file reading/output goes wrong

public class PeopleSums {
    public static void main(String[] args) throws IOException {

        // Streams and helpers
        FileInputStream inStream = null;
        Scanner inFS = null;

        FileOutputStream outStream = null;
        PrintWriter outFS = null;

        // Variables for one person at a time
        String name;
        int age;
        int num1;
        int num2;
        int sum;

        // 1) Open input file people.txt
        inStream = new FileInputStream("people.txt");
        inFS = new Scanner(inStream);

        // 2) Open output file results.txt
        outStream = new FileOutputStream("results.txt");
        outFS = new PrintWriter(outStream);

        // 3) Loop: read until no more data in people.txt
        // Use inFS.hasNext() to decide if another name exists
        while (inFS.hasNext()) {

            // 3a) Read one person's data (must match file order)
            // TODO: read name
            name=inFS.next();
            // TODO: read age
            age=inFS.nextInt();
            // TODO: read num1
            // TO
            num1=inFS.nextInt();
            // DO: read num2
            num2=inFS.nextInt();

            // 3b) Compute sum
            // TODO: sum = ...
            sum=num1+num2;

            // 3c) Write formatted line to results.txt
            // Use printf like before
            // TODO: outFS.printf("Hi I am %s, ...",  ???  );
            outFS.println(name+","+age+","+sum);
        }

        // 4) Close streams
        inFS.close();
        inStream.close();

        outFS.close();
        outStream.close();


    }
}
