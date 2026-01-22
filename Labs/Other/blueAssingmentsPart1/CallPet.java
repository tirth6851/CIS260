public class CallPet {
    public static void main(String[] args) {
        Pet dog = new Pet();
        Pet cat = new Pet("Zeus", 2);

        dog.print();
        cat.print();
    }
}

public class Pet {
    private String name;
    private int age;

    public Pet() {
        name = "NoName";
        age = -1;
    }

    public Pet(String petName, int yearsOld) {
        name = petName;
        age = yearsOld;
    }

    public void print() {
        System.out.println(name + ", " + age);
    }
}