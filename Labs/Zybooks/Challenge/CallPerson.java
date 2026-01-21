import Labs.Zybooks.Challenge.Person;

public class CallPerson {
    public static void main(String [] args) {
        String userName;
        Person person1 = new Person();

        userName = "Bob";

        // Check Person.java file for setFirstName() definition
        person1.setFirstName(userName);

        // Check Person.java file for getFirstName() definition
        System.out.println("You are " + person1.getFirstName());
    }
}