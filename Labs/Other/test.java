public class Person {
    private String firstName;
    private String lastName;

    public void setFirstName(String firstNameToSet) {
        firstName = firstNameToSet;
    }

    public void setLastName(String lastNameToSet) {
        lastName = lastNameToSet;
    }

    public String getFullName() {
        return "(" + lastName + ", " + firstName + ")";
    }
}



public class test{
    public static void main(String[] args){
        String aFirstName;
        String anotherFirstName;
        String aLastName;
        String anotherLastName;

        aFirstName = "Sam";
        anotherFirstName = "Ron";
        aLastName = "Wayne";
        anotherLastName = "Banner";

        Person person1 = new Person();
        Person person2 = new Person();

        person1.setFirstName(aFirstName);
        person2.setFirstName(anotherFirstName);
        person1.setLastName(aLastName);
        person2.setLastName(anotherLastName);

        System.out.println("You are " + person1.getFullName());
        System.out.println("I am " + person2.getFullName());
    }
}