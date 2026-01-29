/*
* Name: Tirth Patel
* ID: 2919808
*/



import java.util.ArrayList;
//import java.util.LinkedList;
import java.util.Scanner;
import java.util.Collections;
public class eraTP1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        ArrayList<Integer> listNum = new ArrayList<>();
        //LinkedList<String> listNum = new LinkedList<>();
        listNum.add(1);
        listNum.add(2);
        listNum.add(3);

        System.out.println("before input");
        for (int num : listNum) {
            System.out.println(num);
        }

        System.out.println("getting input");
        while (listNum.size() < 6) {
            int input = scan.nextInt();
            listNum.add(input);
        }

        System.out.println("after input");
        for (int num : listNum) {
            System.out.println(num);
        }

        /*SORTING*/
        System.out.println("Sorted list");
        Collections.sort(listNum);
        for(int num:listNum){
            System.out.println(num);
        }
    }
}