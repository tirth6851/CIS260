import java.util.HashMap;

public class era4 {
    public static void main(String[] args) {
        HashMap<Character,Character> hashTable = new HashMap<>();
        hashTable.put(')', '(');
        hashTable.put('}', '{');
        hashTable.put(']', '[');
        hashTable.put('>', '<');

        System.out.println(hashTable.get(')'));
        System.out.println(hashTable.get('}'));
        System.out.println(hashTable.get(']'));
        System.out.println(hashTable.get('>'));
        System.out.println("removing '<>'");
        hashTable.remove('>');
        System.out.println(hashTable.get(')'));
        System.out.println(hashTable.get('}'));
        System.out.println(hashTable.get(']'));
        System.out.println(hashTable.get('>'));
    }
}