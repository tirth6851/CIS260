public class Main {
    public static void main(String[] args) {
        Student student = new Student();

        System.out.println(student.getName() + "/" +
                student.getID() + "/" +
                student.getGPA());

        student.setName("Felix");
        student.setID("012345");
        student.setGPA(3.7);

        System.out.println(student.getName() + "/" +
                student.getID() + "/" +
                student.getGPA());
    }
}
