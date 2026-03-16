package topic02_ObjectsAndClasses;

/**
 * ============================================================================
 * TOPIC 2: OBJECTS AND CLASSES - Basic Class Example
 * ============================================================================
 *
 * This file demonstrates the fundamental concepts of classes and objects in Java.
 * A class is like a blueprint, and an object is like a house built from that blueprint.
 *
 * KEY CONCEPTS COVERED:
 * 1. Class declaration
 * 2. Instance variables (fields)
 * 3. Methods
 * 4. Constructors
 * 5. Creating objects
 * 6. Accessing object members
 */

// ============================================================================
// CLASS DECLARATION
// ============================================================================
// The 'public' keyword means this class can be accessed from any other class
// Class names in Java should start with a capital letter (PascalCase)
public class BasicClassExample {

    // ========================================================================
    // INSTANCE VARIABLES (FIELDS/ATTRIBUTES)
    // ========================================================================
    // These variables store the state/data of each object
    // Each object gets its own copy of these variables

    // 'private' means these can only be accessed within this class
    // This is ENCAPSULATION - hiding internal data
    private String studentName;      // Stores the student's name
    private int studentId;           // Stores the student's ID number
    private double gpa;              // Stores the student's GPA
    private boolean isEnrolled;      // Stores enrollment status

    // ========================================================================
    // STATIC VARIABLE (CLASS VARIABLE)
    // ========================================================================
    // 'static' means this variable belongs to the CLASS, not to any object
    // All objects share this single variable
    // Used to track data common to all instances
    private static int totalStudents = 0;  // Counts total students created

    // ========================================================================
    // CONSTRUCTORS
    // ========================================================================
    // Constructors are special methods that run when an object is created
    // They initialize the object's state
    // They have NO return type and share the class name

    /**
     * DEFAULT CONSTRUCTOR
     * This constructor takes no parameters
     * It initializes object with default values
     */
    public BasicClassExample() {
        // 'this()' calls another constructor in the same class
        // Here we're calling the parameterized constructor with default values
        this("Unknown", 0, 0.0, false);
        System.out.println("Default constructor called - delegating to parameterized constructor");
    }

    /**
     * PARAMETERIZED CONSTRUCTOR
     * This constructor accepts parameters to initialize the object
     * @param name The student's name
     * @param id The student's ID
     * @param gpa The student's GPA
     * @param enrolled Whether the student is enrolled
     */
    public BasicClassExample(String name, int id, double gpa, boolean enrolled) {
        // 'this' keyword refers to the current object being created
        // We use 'this' to distinguish instance variables from parameters
        // (both could have the same name)
        this.studentName = name;      // 'this.studentName' is the field, 'name' is the parameter
        this.studentId = id;
        this.gpa = gpa;
        this.isEnrolled = enrolled;

        // Increment the static counter since we created a new student
        totalStudents++;

        System.out.println("Parameterized constructor called for: " + this.studentName);
    }

    /**
     * COPY CONSTRUCTOR
     * Creates a new object as a copy of an existing object
     * @param other The Student object to copy
     */
    public BasicClassExample(BasicClassExample other) {
        // Copy values from the other object
        this.studentName = other.studentName;
        this.studentId = other.studentId;
        this.gpa = other.gpa;
        this.isEnrolled = other.isEnrolled;
        totalStudents++;

        System.out.println("Copy constructor called - copied from: " + other.studentName);
    }

    // ========================================================================
    // GETTERS (ACCESSORS)
    // ========================================================================
    // These methods allow other classes to read private fields
    // This is part of encapsulation - controlled access to data

    public String getStudentName() {
        return this.studentName;  // Return the student's name
    }

    public int getStudentId() {
        return this.studentId;
    }

    public double getGpa() {
        return this.gpa;
    }

    public boolean isEnrolled() {
        return this.isEnrolled;
    }

    // Static getter for the class variable
    public static int getTotalStudents() {
        // Static methods can only access static variables directly
        return totalStudents;
    }

    // ========================================================================
    // SETTERS (MUTATORS)
    // ========================================================================
    // These methods allow other classes to modify private fields
    // We can add validation logic here

    public void setStudentName(String name) {
        // Validate that name is not null or empty
        if (name != null && !name.trim().isEmpty()) {
            this.studentName = name;
        } else {
            System.out.println("Error: Name cannot be empty");
        }
    }

    public void setGpa(double gpa) {
        // Validate GPA is within valid range (0.0 to 4.0)
        if (gpa >= 0.0 && gpa <= 4.0) {
            this.gpa = gpa;
        } else {
            System.out.println("Error: GPA must be between 0.0 and 4.0");
        }
    }

    public void setEnrolled(boolean enrolled) {
        this.isEnrolled = enrolled;
    }

    // ========================================================================
    // INSTANCE METHODS
    // ========================================================================
    // These methods define the behavior of the object
    // They operate on the instance variables

    /**
     * Displays student information
     */
    public void displayInfo() {
        System.out.println("\n--- Student Information ---");
        System.out.println("Name: " + this.studentName);
        System.out.println("ID: " + this.studentId);
        System.out.println("GPA: " + this.gpa);
        System.out.println("Enrolled: " + (this.isEnrolled ? "Yes" : "No"));
        System.out.println("---------------------------\n");
    }

    /**
     * Calculates honor status based on GPA
     * @return String indicating honor status
     */
    public String getHonorStatus() {
        if (this.gpa >= 3.8) {
            return "Summa Cum Laude";
        } else if (this.gpa >= 3.6) {
            return "Magna Cum Laude";
        } else if (this.gpa >= 3.4) {
            return "Cum Laude";
        } else {
            return "No Honors";
        }
    }

    /**
     * Updates GPA with a new course grade
     * @param courseCredits Credits for the course
     * @param gradePoints Grade points earned (A=4, B=3, etc.)
     */
    public void updateGpa(int courseCredits, double gradePoints) {
        // Simplified GPA calculation for demonstration
        double courseContribution = (gradePoints * courseCredits) / 100.0;
        this.gpa = Math.min(4.0, this.gpa + courseContribution);
        System.out.println("GPA updated to: " + this.gpa);
    }

    // ========================================================================
    // STATIC METHOD
    // ========================================================================
    // Belongs to the class, not to any specific instance
    // Can be called without creating an object

    public static void displayClassInfo() {
        System.out.println("\n=== Student Class Information ===");
        System.out.println("Total students created: " + totalStudents);
        System.out.println("This is a static method - no object needed!");
        System.out.println("===================================\n");
    }

    // ========================================================================
    // MAIN METHOD - Entry point of the program
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║     TOPIC 2: OBJECTS AND CLASSES - BASIC EXAMPLE                 ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝\n");

        // ------------------------------------------------------------------------
        // CREATING OBJECTS (INSTANTIATION)
        // ------------------------------------------------------------------------
        // We use the 'new' keyword to create objects
        // This allocates memory and calls the constructor

        System.out.println("--- Creating Student Objects ---\n");

        // Create student using parameterized constructor
        BasicClassExample student1 = new BasicClassExample("Alice Johnson", 1001, 3.8, true);

        // Create student using default constructor
        BasicClassExample student2 = new BasicClassExample();

        // Create student using copy constructor (copies student1)
        BasicClassExample student3 = new BasicClassExample(student1);

        // ------------------------------------------------------------------------
        // ACCESSING OBJECT MEMBERS
        // ------------------------------------------------------------------------
        // Use the dot (.) operator to access fields and methods

        System.out.println("\n--- Accessing Object Data ---");

        // Using getters to access private fields
        System.out.println("Student 1 Name: " + student1.getStudentName());
        System.out.println("Student 1 GPA: " + student1.getGpa());

        // Using setters to modify data
        System.out.println("\n--- Modifying Object Data ---");
        student2.setStudentName("Bob Smith");
        student2.setGpa(3.5);
        System.out.println("Student 2 new name: " + student2.getStudentName());

        // Try setting invalid GPA (will show error message)
        student2.setGpa(5.0);  // This will be rejected

        // ------------------------------------------------------------------------
        // CALLING INSTANCE METHODS
        // ------------------------------------------------------------------------
        System.out.println("\n--- Calling Instance Methods ---");
        student1.displayInfo();
        System.out.println("Honor Status: " + student1.getHonorStatus());

        // ------------------------------------------------------------------------
        // CALLING STATIC METHOD
        // ------------------------------------------------------------------------
        // Static methods are called using ClassName.methodName()
        // No object needed!
        System.out.println("\n--- Calling Static Method ---");
        BasicClassExample.displayClassInfo();

        // You can also call static methods on objects, but it's not recommended
        // student1.displayClassInfo(); // Works but confusing!

        // ------------------------------------------------------------------------
        // DEMONSTRATING OBJECT REFERENCES
        // ------------------------------------------------------------------------
        System.out.println("--- Object References Demo ---");

        // Both references point to the same object!
        BasicClassExample ref1 = student1;
        System.out.println("Original name: " + student1.getStudentName());
        System.out.println("Reference name: " + ref1.getStudentName());

        // Modifying through one reference affects both
        ref1.setStudentName("Alice Modified");
        System.out.println("After modification through ref1:");
        System.out.println("student1 name: " + student1.getStudentName());
        System.out.println("ref1 name: " + ref1.getStudentName());
        // Both show "Alice Modified" because they point to the same object!

        // ------------------------------------------------------------------------
        // NULL REFERENCES
        // ------------------------------------------------------------------------
        System.out.println("\n--- Null Reference Demo ---");
        BasicClassExample nullStudent = null;  // Reference that points to nothing
        // nullStudent.displayInfo();  // This would cause NullPointerException!
        System.out.println("nullStudent is null: " + (nullStudent == null));

        // ------------------------------------------------------------------------
        // FINAL SUMMARY
        // ------------------------------------------------------------------------
        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║                      SUMMARY                                     ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║ 1. Class is a blueprint, Object is an instance                   ║");
        System.out.println("║ 2. Use 'new' keyword to create objects                             ║");
        System.out.println("║ 3. Constructors initialize object state                            ║");
        System.out.println("║ 4. Encapsulation hides data with private fields                    ║");
        System.out.println("║ 5. Getters/Setters provide controlled access                       ║");
        System.out.println("║ 6. Static members belong to class, not objects                   ║");
        System.out.println("║ 7. 'this' refers to the current object                             ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
    }
}
