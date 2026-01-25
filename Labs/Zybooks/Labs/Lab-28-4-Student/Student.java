public class Student {

    private String name;
    private String id;
    private double gpa;

    public Student() {
        this.name = "Louie";
        this.id = "000000";
        this.gpa = 0.0;
    }

    public void setName(String n) {
        this.name = n;
    }

    public void setID(String n) {
        this.id = n;
    }

    public void setGPA(double g) {
        this.gpa = g;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return id;
    }

    public double getGPA() {
        return gpa;
    }
}
