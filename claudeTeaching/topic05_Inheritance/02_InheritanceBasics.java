package topic05_Inheritance;

/**
 * ============================================================================
 * TOPIC 5: INHERITANCE - Basic Concepts
 * ============================================================================
 *
 * Inheritance allows a class to inherit properties and methods from another class.
 * The class that inherits is called the SUBCLASS (child/derived).
 * The class being inherited from is called the SUPERCLASS (parent/base).
 *
 * KEY BENEFITS:
 * 1. Code reusability - Don't repeat common code
 * 2. Extensibility - Add new features to existing code
 * 3. Organization - Create logical class hierarchies
 *
 * SYNTAX: class Subclass extends Superclass { }
 */

// ============================================================================
// SUPERCLASS (Parent Class)
// ============================================================================
// This is the base class that contains common attributes and methods
// that will be inherited by subclasses
class Animal {
    // Protected fields can be accessed by subclasses
    // Private fields cannot be accessed directly by subclasses
    protected String name;
    protected int age;
    private String species;  // Private - not inherited directly

    // Constructor
    public Animal(String name, int age, String species) {
        this.name = name;
        this.age = age;
        this.species = species;
        System.out.println("  [Animal constructor called for " + name + "]");
    }

    // Methods that will be inherited
    public void eat() {
        System.out.println(name + " is eating.");
    }

    public void sleep() {
        System.out.println(name + " is sleeping.");
    }

    // Method that will be overridden by subclasses
    public void makeSound() {
        System.out.println(name + " makes a generic animal sound.");
    }

    // Method to display information
    public void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age + ", Species: " + species);
    }

    // Getter for private field (subclasses can use this)
    public String getSpecies() {
        return species;
    }
}

// ============================================================================
// SUBCLASS (Child Class) - Single Inheritance
// ============================================================================
// Dog IS-A Animal (IS-A relationship)
// Dog inherits all non-private members from Animal
class Dog extends Animal {
    // Additional fields specific to Dog
    private String breed;
    private boolean isTrained;

    // Constructor
    public Dog(String name, int age, String breed, boolean isTrained) {
        // super() calls the parent class constructor
        // MUST be the first statement in the constructor
        // If not called explicitly, Java inserts super() (no-arg constructor)
        super(name, age, "Canis lupus familiaris");

        this.breed = breed;
        this.isTrained = isTrained;
        System.out.println("  [Dog constructor called for " + name + "]");
    }

    // METHOD OVERRIDING
    // Dog provides its own implementation of makeSound()
    // The @Override annotation is optional but HIGHLY RECOMMENDED
    // It tells the compiler we're intentionally overriding
    @Override
    public void makeSound() {
        // super.makeSound();  // Can call parent's version first
        System.out.println(name + " barks: Woof! Woof!");
    }

    // Additional methods specific to Dog
    public void fetch() {
        if (isTrained) {
            System.out.println(name + " fetches the ball!");
        } else {
            System.out.println(name + " looks confused. Needs more training!");
        }
    }

    public void wagTail() {
        System.out.println(name + " wags tail happily!");
    }

    // Override displayInfo to include Dog-specific information
    @Override
    public void displayInfo() {
        // Call parent's displayInfo first
        super.displayInfo();
        System.out.println("  Breed: " + breed + ", Trained: " + isTrained);
    }
}

// ============================================================================
// ANOTHER SUBCLASS - Cat
// ============================================================================
class Cat extends Animal {
    private int livesRemaining;
    private boolean isIndoor;

    public Cat(String name, int age, boolean isIndoor) {
        super(name, age, "Felis catus");
        this.livesRemaining = 9;  // Cats have 9 lives!
        this.isIndoor = isIndoor;
        System.out.println("  [Cat constructor called for " + name + "]");
    }

    @Override
    public void makeSound() {
        System.out.println(name + " meows: Meow!");
    }

    public void climb() {
        System.out.println(name + " climbs up the tree!");
    }

    public void loseLife() {
        if (livesRemaining > 0) {
            livesRemaining--;
            System.out.println(name + " lost a life! Lives remaining: " + livesRemaining);
        } else {
            System.out.println(name + " has no lives left!");
        }
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("  Lives: " + livesRemaining + ", Indoor: " + isIndoor);
    }
}

// ============================================================================
// MULTILEVEL INHERITANCE
// ============================================================================
// Puppy IS-A Dog IS-A Animal
class Puppy extends Dog {
    private int cutenessLevel;

    public Puppy(String name, String breed) {
        // Call Dog's constructor
        // Dog's constructor will call Animal's constructor
        super(name, 0, breed, false);  // Puppies are age 0 and untrained
        this.cutenessLevel = 100;  // All puppies are 100% cute
        System.out.println("  [Puppy constructor called for " + name + "]");
    }

    @Override
    public void makeSound() {
        // Puppies have their own sound
        System.out.println(name + " yips: Yip! Yip!");
    }

    public void chewShoes() {
        System.out.println(name + " is chewing on your shoes!");
    }
}

// ============================================================================
// HIERARCHICAL INHERITANCE
// ============================================================================
// Multiple classes inherit from the same parent
class Bird extends Animal {
    private double wingspan;
    private boolean canFly;

    public Bird(String name, int age, double wingspan, boolean canFly) {
        super(name, age, "Aves");
        this.wingspan = wingspan;
        this.canFly = canFly;
    }

    @Override
    public void makeSound() {
        System.out.println(name + " chirps: Tweet tweet!");
    }

    public void fly() {
        if (canFly) {
            System.out.println(name + " soars through the sky!");
        } else {
            System.out.println(name + " tries to fly but can't!");
        }
    }
}

// ============================================================================
// MAIN CLASS
// ============================================================================
public class InheritanceBasics {
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║     TOPIC 5: INHERITANCE - Basic Concepts                        ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝\n");

        // ========================================================================
        // SECTION 1: CREATING SUBCLASS OBJECTS
        // ========================================================================
        System.out.println("--- SECTION 1: Creating Subclass Objects ---");
        System.out.println("Notice the constructor chain: Animal → Dog → (Puppy)\n");

        // Create a Dog object
        System.out.println("Creating Dog object:");
        Dog myDog = new Dog("Buddy", 3, "Golden Retriever", true);
        System.out.println();

        // Create a Cat object
        System.out.println("Creating Cat object:");
        Cat myCat = new Cat("Whiskers", 2, true);
        System.out.println();

        // Create a Puppy object (multilevel inheritance)
        System.out.println("Creating Puppy object (multilevel inheritance):");
        Puppy myPuppy = new Puppy("Tiny", "Labrador");
        System.out.println();

        // ========================================================================
        // SECTION 2: INHERITED METHODS
        // ========================================================================
        System.out.println("--- SECTION 2: Using Inherited Methods ---");
        System.out.println("Dog and Cat inherit methods from Animal:\n");

        // Methods inherited from Animal
        myDog.eat();    // Inherited from Animal
        myDog.sleep();  // Inherited from Animal

        myCat.eat();    // Inherited from Animal
        myCat.sleep();  // Inherited from Animal

        // ========================================================================
        // SECTION 3: OVERRIDDEN METHODS
        // ========================================================================
        System.out.println("\n--- SECTION 3: Method Overriding ---");
        System.out.println("Each subclass provides its own implementation of makeSound():\n");

        myDog.makeSound();    // Dog's version: barks
        myCat.makeSound();    // Cat's version: meows
        myPuppy.makeSound();  // Puppy's version: yips

        // ========================================================================
        // SECTION 4: SUBCLASS-SPECIFIC METHODS
        // ========================================================================
        System.out.println("\n--- SECTION 4: Subclass-Specific Methods ---");
        System.out.println("Methods that only exist in the subclass:\n");

        myDog.fetch();      // Dog-specific
        myDog.wagTail();    // Dog-specific

        myCat.climb();      // Cat-specific
        myCat.loseLife();   // Cat-specific

        myPuppy.chewShoes(); // Puppy-specific

        // ========================================================================
        // SECTION 5: POLYMORPHISM
        // ========================================================================
        System.out.println("\n--- SECTION 5: Polymorphism ---");
        System.out.println("A superclass reference can point to subclass objects:\n");

        // UPCASTING: Subclass object assigned to superclass reference
        // This is automatic and safe
        Animal animal1 = myDog;   // Dog IS-A Animal
        Animal animal2 = myCat;   // Cat IS-A Animal
        Animal animal3 = myPuppy; // Puppy IS-A Dog IS-A Animal

        System.out.println("animal1 reference type: " + animal1.getClass().getSimpleName());
        System.out.println("animal2 reference type: " + animal2.getClass().getSimpleName());
        System.out.println("animal3 reference type: " + animal3.getClass().getSimpleName());

        // Method calls are resolved at runtime (dynamic binding)
        System.out.println("\nCalling makeSound() on Animal references:");
        animal1.makeSound();  // Calls Dog's makeSound()
        animal2.makeSound();  // Calls Cat's makeSound()
        animal3.makeSound();  // Calls Puppy's makeSound()

        // But we can only call methods known to Animal class
        // animal1.fetch();  // ERROR: fetch() is not in Animal class!

        // ========================================================================
        // SECTION 6: DOWNCASTING
        // ========================================================================
        System.out.println("\n--- SECTION 6: Downcasting ---");
        System.out.println("Converting superclass reference back to subclass:\n");

        // To access subclass methods, we need to downcast
        // This is manual and can be unsafe if the object isn't actually that type

        if (animal1 instanceof Dog) {
            Dog dogRef = (Dog) animal1;  // Explicit cast
            System.out.println("Downcast successful!");
            dogRef.fetch();  // Now we can call Dog-specific methods
        }

        // Using pattern matching (Java 16+)
        if (animal2 instanceof Cat catRef) {
            System.out.println("Pattern matching downcast successful!");
            catRef.climb();
        }

        // ========================================================================
        // SECTION 7: DISPLAYING INFORMATION
        // ========================================================================
        System.out.println("\n--- SECTION 7: Displaying Information ---");

        myDog.displayInfo();
        System.out.println();
        myCat.displayInfo();

        // ========================================================================
        // SECTION 8: ARRAY OF SUPERCLASS REFERENCES
        // ========================================================================
        System.out.println("\n--- SECTION 8: Array of Animals ---");
        System.out.println("Can store different subclass objects in same array:\n");

        Animal[] zoo = new Animal[4];
        zoo[0] = new Dog("Rex", 5, "German Shepherd", true);
        zoo[1] = new Cat("Mittens", 3, false);
        zoo[2] = new Bird("Tweety", 1, 0.5, true);
        zoo[3] = new Puppy("Spot", "Beagle");

        System.out.println("All animals make sounds:");
        for (Animal animal : zoo) {
            System.out.print(animal.getClass().getSimpleName() + ": ");
            animal.makeSound();  // Polymorphism in action!
        }

        // ========================================================================
        // SUMMARY
        // ========================================================================
        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║  KEY CONCEPTS:                                                   ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║  extends:     Creates inheritance relationship                 ║");
        System.out.println("║  super():      Calls parent constructor                          ║");
        System.out.println("║  @Override:    Indicates method overriding                     ║");
        System.out.println("║  Upcasting:    Subclass → Superclass (automatic, safe)         ║");
        System.out.println("║  Downcasting:  Superclass → Subclass (manual, check instanceof)  ║");
        System.out.println("║  Polymorphism: Same method call, different behaviors            ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
    }
}
