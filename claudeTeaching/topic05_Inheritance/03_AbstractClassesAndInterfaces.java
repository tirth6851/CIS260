package topic05_Inheritance;

/**
 * ============================================================================
 * TOPIC 5: INHERITANCE - Abstract Classes and Interfaces
 * ============================================================================
 *
 * ABSTRACT CLASSES:
 * - Cannot be instantiated (cannot create objects directly)
 * - Can have both abstract methods (no body) and concrete methods
 * - Used when classes share a common base but need different implementations
 * - A class can extend only ONE abstract class
 *
 * INTERFACES:
 * - Define a contract that implementing classes must follow
 * - All methods were abstract (before Java 8), now can have default methods
 * - A class can implement MULTIPLE interfaces
 * - Used to define capabilities (CAN-DO relationship)
 */

// ============================================================================
// ABSTRACT CLASS EXAMPLE
// ============================================================================
// Abstract class representing a generic Shape
// Cannot create: new Shape() - this would be a compile error!
abstract class Shape {
    // Instance variables (can exist in abstract classes)
    protected String color;
    protected boolean filled;

    // Constructor (can exist in abstract classes)
    public Shape(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    // ABSTRACT METHOD - no implementation, must be overridden by subclasses
    // Abstract methods can only exist in abstract classes
    public abstract double getArea();
    public abstract double getPerimeter();

    // CONCRETE METHOD - has implementation, can be used or overridden
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void displayInfo() {
        System.out.println("Shape: " + this.getClass().getSimpleName());
        System.out.println("  Color: " + color);
        System.out.println("  Filled: " + filled);
        System.out.println("  Area: " + getArea());
        System.out.println("  Perimeter: " + getPerimeter());
    }
}

// Concrete subclass implementing the abstract methods
class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height, String color, boolean filled) {
        super(color, filled);
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }
}

class Circle extends Shape {
    private double radius;
    private static final double PI = 3.14159;

    public Circle(double radius, String color, boolean filled) {
        super(color, filled);
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * PI * radius;
    }
}

// ============================================================================
// INTERFACE EXAMPLES
// ============================================================================
// Interface defining drawable capability
interface Drawable {
    // All fields in interfaces are public static final (constants)
    int MAX_SIZE = 1000;

    // Abstract method (implicitly public abstract)
    void draw();

    // Default method (Java 8+) - has implementation
    default void drawBorder() {
        System.out.println("Drawing default border");
    }

    // Static method (Java 8+)
    static void printInfo() {
        System.out.println("Drawable interface - max size: " + MAX_SIZE);
    }
}

// Interface defining resizable capability
interface Resizable {
    void scale(double factor);
}

// Interface defining movable capability
interface Movable {
    void move(int x, int y);
}

// ============================================================================
// CLASS IMPLEMENTING MULTIPLE INTERFACES
// ============================================================================
// A class can implement multiple interfaces!
class DrawableRectangle extends Rectangle implements Drawable, Resizable, Movable {
    private int x, y;

    public DrawableRectangle(double width, double height, String color, boolean filled) {
        super(width, height, color, filled);
        this.x = 0;
        this.y = 0;
    }

    // Must implement all abstract methods from interfaces
    @Override
    public void draw() {
        System.out.println("Drawing rectangle at (" + x + ", " + y + ")");
        System.out.println("  Color: " + color + ", Size: " + getArea());
    }

    @Override
    public void scale(double factor) {
        System.out.println("Scaling rectangle by factor: " + factor);
    }

    @Override
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
        System.out.println("Moved rectangle to (" + x + ", " + y + ")");
    }
}

// ============================================================================
// MAIN CLASS
// ============================================================================
public class AbstractClassesAndInterfaces {
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║     ABSTRACT CLASSES AND INTERFACES                              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝\n");

        // ========================================================================
        // SECTION 1: ABSTRACT CLASSES
        // ========================================================================
        System.out.println("--- SECTION 1: Abstract Classes ---");
        System.out.println("Cannot instantiate abstract classes directly.\n");

        // Shape shape = new Shape("red", true);  // ERROR! Cannot instantiate

        // But can use abstract class as reference type
        Shape rect = new Rectangle(5, 3, "Blue", true);
        Shape circle = new Circle(4, "Red", false);

        System.out.println("Rectangle:");
        rect.displayInfo();

        System.out.println("\nCircle:");
        circle.displayInfo();

        // ========================================================================
        // SECTION 2: ARRAY OF ABSTRACT TYPE
        // ========================================================================
        System.out.println("\n--- SECTION 2: Array of Abstract Type ---");

        Shape[] shapes = new Shape[3];
        shapes[0] = new Rectangle(4, 6, "Green", true);
        shapes[1] = new Circle(3, "Yellow", true);
        shapes[2] = new Rectangle(2, 8, "Purple", false);

        double totalArea = 0;
        for (Shape shape : shapes) {
            totalArea += shape.getArea();
            System.out.println(shape.getClass().getSimpleName() + " area: " + shape.getArea());
        }
        System.out.println("Total area: " + totalArea);

        // ========================================================================
        // SECTION 3: INTERFACES
        // ========================================================================
        System.out.println("\n--- SECTION 3: Interfaces ---");

        DrawableRectangle drawableRect = new DrawableRectangle(10, 5, "Orange", true);

        drawableRect.draw();
        drawableRect.move(100, 200);
        drawableRect.scale(2.0);
        drawableRect.drawBorder();  // Default method from interface

        // ========================================================================
        // SECTION 4: INTERFACE REFERENCES
        // ========================================================================
        System.out.println("\n--- SECTION 4: Interface References ---");

        // Can use interface as reference type
        Drawable d = drawableRect;
        Resizable r = drawableRect;
        Movable m = drawableRect;

        d.draw();      // Can only call Drawable methods
        r.scale(1.5);  // Can only call Resizable methods
        m.move(50, 50); // Can only call Movable methods

        // ========================================================================
        // SECTION 5: STATIC INTERFACE METHOD
        // ========================================================================
        System.out.println("\n--- SECTION 5: Static Interface Method ---");
        Drawable.printInfo();  // Call static method on interface

        // ========================================================================
        // SUMMARY
        // ========================================================================
        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║  ABSTRACT CLASS vs INTERFACE                                     ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║  Abstract Class:                                                 ║");
        System.out.println("║    • Can have constructors                                       ║");
        System.out.println("║    • Can have instance variables                                 ║");
        System.out.println("║    • Single inheritance only                                     ║");
        System.out.println("║    • Use for IS-A with shared code                             ║");
        System.out.println("║                                                                  ║");
        System.out.println("║  Interface:                                                      ║");
        System.out.println("║    • No constructors                                             ║");
        System.out.println("║    • Only constants (static final)                               ║");
        System.out.println("║    • Multiple inheritance allowed                                ║");
        System.out.println("║    • Use for CAN-DO capabilities                                 ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
    }
}
