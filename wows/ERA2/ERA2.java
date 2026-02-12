/*
* TIRTH PATEL
* CSU ID-2919808
* */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ERA2 {
    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("era3.txt")) {
            PrintWriter outFS = new PrintWriter(writer);
            for (String arg : args) {writer.write(arg + System.lineSeparator());}
            outFS.println("done b***h");
            writer.close();
            outFS.close();

        } catch (IOException e) {System.out.println("Error writing to file: " + e.getMessage());}
    }
}
