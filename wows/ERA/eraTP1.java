/*
* Name: Tirth Patel
* ID: 2919808
*
*/


import java.util.LinkedList;
import java.util.Scanner;

public class eraTP1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        LinkedList<Integer> listNum = new LinkedList<>();
        listNum.add(1);
        listNum.add(2);
        listNum.add(3);
        // printing before adding the numbers
        System.out.println("before input");
        for (int i = 0; i < listNum.size(); i++) {System.out.println(listNum.get(i));}
        System.out.println("getting input");
        for(int i=0;i<listNum.size();i++){
            int input = scan.nextInt();
            listNum.add(input);
            if(listNum.size()==6){
                break;
            }
        }
        System.out.println("after input");
        for (int i = 0; i < listNum.size(); i++) {System.out.println(listNum.get(i));}

    }
}