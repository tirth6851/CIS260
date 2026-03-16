package topic02_ObjectsAndClasses;

/**
 * ============================================================================
 * TOPIC 2: OBJECTS AND CLASSES - Advanced Concepts
 * ============================================================================
 *
 * This file covers advanced OOP concepts:
 * 1. Method overloading
 * 2. Method chaining
 * 3. Immutable objects
 * 4. Nested classes
 * 5. Anonymous objects
 * 6. Object comparison (equals vs ==)
 * 7. toString(), hashCode(), equals() methods
 */

// ============================================================================
// ADVANCED CLASS EXAMPLE
// ============================================================================
class AdvancedStudent {
    // Final fields - can only be assigned once
    // Typically used for immutable data
    private final int studentId;           // Final: ID never changes
    private final String dateOfBirth;      // Final: Birth date never changes

    // Regular fields
    private String name;
    private String major;
    private int creditsCompleted;

    // Static final constant - shared and unchangeable
    public static final int MAX_CREDITS = 120;
    public static final String UNIVERSITY_NAME = "Tech University";

    // ========================================================================
    // CONSTRUCTOR OVERLOADING
    // ========================================================================
    // Multiple constructors with different parameters
    // Java determines which to call based on arguments provided

    // Constructor 1: Full constructor
    public AdvancedStudent(int id, String name, String dob, String major) {
        this.studentId = id;           // Final field must be set in constructor
        this.name = name;
        this.dateOfBirth = dob;          // Final field must be set in constructor
        this.major = major;
        this.creditsCompleted = 0;
    }

    // Constructor 2: Without major (defaults to "Undeclared")
    public AdvancedStudent(int id, String name, String dob) {
        // Call the other constructor using 'this()'
        // Must be the FIRST statement in the constructor
        this(id, name, dob, "Undeclared");
        System.out.println("Called 3-arg constructor, delegated to 4-arg");
    }

    // Constructor 3: Minimal info
    public AdvancedStudent(int id) {
        this(id, "Unknown", "Unknown", "Undeclared");
    }

    // ========================================================================
    // METHOD OVERLOADING
    // ========================================================================
    // Multiple methods with same name but different parameters
    // Compiler chooses based on arguments

    // Method 1: Add single credit
    public void addCredits(int credits) {
        if (credits > 0) {
            this.creditsCompleted += credits;
            System.out.println("Added " + credits + " credits. Total: " + this.creditsCompleted);
        }
    }

    // Method 2: Add credits with course name (overloaded)
    public void addCredits(int credits, String courseName) {
        addCredits(credits);  // Call the other overloaded method
        System.out.println("Course completed: " + courseName);
    }

    // Method 3: Add multiple courses at once (varargs)
    public void addCredits(String... courseNames) {
        for (String course : courseNames) {
            addCredits(3, course);  // Assume each course is 3 credits
        }
    }

    // ========================================================================
    // METHOD CHAINING (Fluent Interface)
    // ========================================================================
    // Return 'this' to allow chaining multiple method calls
    // Example: student.setName("X").setMajor("Y").addCredits(10);

    public AdvancedStudent setName(String name) {
        this.name = name;
        return this;  // Return current object for chaining
    }

    public AdvancedStudent setMajor(String major) {
        this.major = major;
        return this;
    }

    // ========================================================================
    // IMPORTANT OBJECT METHODS
    // ========================================================================

    /**
     * toString() - Returns string representation of the object
     * Called automatically when object is used in string context
     * ESSENTIAL for debugging!
     */
    @Override
    public String toString() {
        return "AdvancedStudent{" +
                "ID=" + studentId +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", credits=" + creditsCompleted +
                ", DOB='" + dateOfBirth + '\'' +
                '}';
    }

    /**
     * equals() - Compares two objects for equality
     * Default implementation compares memory addresses (==)
     * Should be overridden to compare content
     */
    @Override
    public boolean equals(Object obj) {
        // Check if same reference
        if (this == obj) return true;

        // Check if null or different class
        if (obj == null || getClass() != obj.getClass()) return false;

        // Cast and compare fields
        AdvancedStudent other = (AdvancedStudent) obj;
        return this.studentId == other.studentId;
        // We compare by ID since it's unique for each student
    }

    /**
     * hashCode() - Returns integer hash for use in hash-based collections
     * MUST be consistent with equals(): if equals() returns true,
     * hashCode() must return same value
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(studentId);
    }

    // Getters
    public int getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getMajor() { return major; }
    public int getCreditsCompleted() { return creditsCompleted; }
    public String getDateOfBirth() { return dateOfBirth; }
}

// ============================================================================
// IMMUTABLE CLASS EXAMPLE
// ============================================================================
// Immutable objects cannot be changed after creation
// Benefits: Thread-safe, can be cached, safer to share
final class ImmutablePoint {
    // All fields are private and final
    private final double x;
    private final double y;

    // Constructor initializes all fields
    public ImmutablePoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // No setters! Only getters
    public double getX() { return x; }
    public double getY() { return y; }

    // Instead of modifying, return a new object
    public ImmutablePoint translate(double dx, double dy) {
        return new ImmutablePoint(this.x + dx, this.y + dy);
    }

    @Override
    public String toString() {
        return "ImmutablePoint(" + x + ", " + y + ")";
    }
}

// ============================================================================
// NESTED CLASS EXAMPLE
// ============================================================================
class University {
    private String name;

    // Static nested class - belongs to the outer class, not instances
    static class Department {
        private String deptName;
        private int buildingNumber;

        public Department(String name, int building) {
            this.deptName = name;
            this.buildingNumber = building;
        }

        public void display() {
            System.out.println("Department: " + deptName + ", Building: " + buildingNumber);
        }
    }

    // Inner class - belongs to an instance of the outer class
    class Professor {
        private String profName;

        public Professor(String name) {
            this.profName = name;
        }

        public void display() {
            // Can access outer class's private members!
            System.out.println("Professor: " + profName + " at " + name);
        }
    }

    public University(String name) {
        this.name = name;
    }
}

// ============================================================================
// MAIN CLASS
// ============================================================================
public class AdvancedConcepts {
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║     ADVANCED OBJECTS AND CLASSES                                 ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝\n");

        // ------------------------------------------------------------------------
        // CONSTRUCTOR OVERLOADING DEMO
        // ------------------------------------------------------------------------
        System.out.println("--- Constructor Overloading ---");
        AdvancedStudent s1 = new AdvancedStudent(1001, "Alice", "2000-05-15", "CS");
        AdvancedStudent s2 = new AdvancedStudent(1002, "Bob", "1999-08-22");  // No major
        AdvancedStudent s3 = new AdvancedStudent(1003);  // Minimal info

        System.out.println("s1: " + s1);
        System.out.println("s2: " + s2);
        System.out.println("s3: " + s3);

        // ------------------------------------------------------------------------
        // METHOD OVERLOADING DEMO
        // ------------------------------------------------------------------------
        System.out.println("\n--- Method Overloading ---");
        s1.addCredits(3);                           // Calls method 1
        s1.addCredits(4, "Data Structures");          // Calls method 2
        s1.addCredits("Algorithms", "Databases", "AI");  // Calls method 3 (varargs)

        // ------------------------------------------------------------------------
        // METHOD CHAINING DEMO
        // ------------------------------------------------------------------------
        System.out.println("\n--- Method Chaining ---");
        AdvancedStudent s4 = new AdvancedStudent(1004, "Charlie", "2001-01-10")
                .setName("Charles")      // Returns 'this', so we can chain
                .setMajor("Engineering"); // Returns 'this' again
        System.out.println("s4: " + s4);

        // ------------------------------------------------------------------------
        // EQUALS VS == DEMO
        // ------------------------------------------------------------------------
        System.out.println("\n--- equals() vs == ---");
        AdvancedStudent a = new AdvancedStudent(2001, "Test", "2000-01-01");
        AdvancedStudent b = new AdvancedStudent(2001, "Test", "2000-01-01");
        AdvancedStudent c = a;  // Same reference

        System.out.println("a == b: " + (a == b));      // false - different objects in memory
        System.out.println("a.equals(b): " + a.equals(b));  // true - same ID
        System.out.println("a == c: " + (a == c));      // true - same reference

        // ------------------------------------------------------------------------
        // IMMUTABLE OBJECTS DEMO
        // ------------------------------------------------------------------------
        System.out.println("\n--- Immutable Objects ---");
        ImmutablePoint p1 = new ImmutablePoint(3.0, 4.0);
        System.out.println("Original: " + p1);

        // translate() returns a NEW object, doesn't modify p1
        ImmutablePoint p2 = p1.translate(1.0, 1.0);
        System.out.println("After translate: ");
        System.out.println("  p1 (unchanged): " + p1);
        System.out.println("  p2 (new point): " + p2);

        // ------------------------------------------------------------------------
        // NESTED CLASSES DEMO
        // ------------------------------------------------------------------------
        System.out.println("\n--- Nested Classes ---");

        // Static nested class - can be created without outer class instance
        University.Department csDept = new University.Department("Computer Science", 101);
        csDept.display();

        // Inner class - requires outer class instance
        University techUni = new University("Tech University");
        University.Professor prof = techUni.new Professor("Dr. Smith");
        prof.display();

        // ------------------------------------------------------------------------
        // ANONYMOUS OBJECTS
        // ------------------------------------------------------------------------
        System.out.println("\n--- Anonymous Objects ---");
        // Object created and used immediately, no reference stored
        System.out.println(new AdvancedStudent(999, "Temp", "2000-01-01").toString());
        // After this line, the object is eligible for garbage collection

        // ------------------------------------------------------------------------
        // CONSTANTS
        // ------------------------------------------------------------------------
        System.out.println("\n--- Class Constants ---");
        System.out.println("Max Credits: " + AdvancedStudent.MAX_CREDITS);
        System.out.println("University: " + AdvancedStudent.UNIVERSITY_NAME);

        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║  KEY TAKEAWAYS:                                                  ║");
        System.out.println("║  • Overloading: Same name, different parameters                  ║");
        System.out.println("║  • Chaining: Return 'this' for fluent interfaces                 ║");
        System.out.println("║  • Immutable: Create new objects instead of modifying              ║");
        System.out.println("║  • equals(): Compare content, == compares references             ║");
        System.out.println("║  • toString(): Essential for debugging                           ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
    }
}
