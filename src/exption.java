import java.util.InputMismatchException;
import java.util.Scanner;
public class exption  {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        try{
            int num1 = scan.nextInt();
            int num2 = scan.nextInt();
            System.out.println(num1/num2);
        }

        catch(InputMismatchException e) {
            System.out.println("Please enter an integer");
        }
        catch(ArithmeticException e){
            System.out.println("no division by zero");
        }
    }
}