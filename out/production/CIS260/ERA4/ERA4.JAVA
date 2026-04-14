import java.util.HashMap;

public class ERA4 {
    public static void main(String[] args) {
        HashMap<Character,Character> hashTable = new HashMap<>();
        hashTable.put(')', '(');
        hashTable.put('}', '{');
        hashTable.put(']', '[');

        for(int i=0;i<hashTable.size();i++) {
            System.out.println((hashTable.get(i))+hashTable.get(hashTable.get(i)));
        }
    }
}