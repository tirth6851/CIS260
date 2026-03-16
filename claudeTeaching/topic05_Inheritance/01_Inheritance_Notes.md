# Topic 5: Inheritance - Complete Guide

## What is Inheritance?

Inheritance is an OOP concept where a new class (subclass/child) derives properties and behaviors from an existing class (superclass/parent). It promotes code reusability and establishes an "IS-A" relationship.

## Key Concepts

### IS-A Relationship
- A Dog IS-A Animal
- A Car IS-A Vehicle
- A Student IS-A Person

### HAS-A Relationship (Composition)
- A Car HAS-A Engine
- A University HAS-A Department
- Use composition when there's no true IS-A relationship

## Types of Inheritance

### 1. Single Inheritance
A class inherits from one parent class.
```java
class Animal { }
class Dog extends Animal { }
```

### 2. Multilevel Inheritance
A class inherits from a child class.
```java
class Animal { }
class Mammal extends Animal { }
class Dog extends Mammal { }
```

### 3. Hierarchical Inheritance
Multiple classes inherit from one parent.
```java
class Animal { }
class Dog extends Animal { }
class Cat extends Animal { }
```

### 4. Multiple Inheritance (Not in Java Classes)
A class inheriting from multiple parents. **Not supported in Java** (avoid ambiguity).
- Achieved through interfaces instead.

## The 'extends' Keyword

```java
class Subclass extends Superclass {
    // Additional fields and methods
}
```

## The 'super' Keyword

### 1. Calling Parent Constructor
```java
public Dog(String name, int age) {
    super(name);  // Call parent's constructor
    this.age = age;
}
```

### 2. Accessing Parent Methods
```java
public void makeSound() {
    super.makeSound();  // Call parent's method
    System.out.println("Bark!");
}
```

### 3. Accessing Parent Fields
```java
public void display() {
    System.out.println(super.name);  // Access parent's field
}
```

## Method Overriding

When a subclass provides a specific implementation of a method already defined in its parent.

### Rules for Overriding:
1. Same method name and parameter list
2. Return type must be same or covariant
3. Access modifier cannot be more restrictive
4. Cannot override final methods
5. Cannot override static methods (they're hidden, not overridden)

### @Override Annotation
Always use @Override to indicate intentional overriding:
```java
@Override
public void makeSound() {
    System.out.println("Bark!");
}
```

## Access Modifiers and Inheritance

| Modifier | Same Class | Same Package | Subclass | Anywhere |
|----------|-----------|--------------|----------|----------|
| private | Yes | No | No | No |
| default | Yes | Yes | Yes* | No |
| protected | Yes | Yes | Yes | No |
| public | Yes | Yes | Yes | Yes |

*Only if subclass is in same package

## Abstract Classes

Classes that cannot be instantiated, meant to be extended.

```java
abstract class Shape {
    // Can have both abstract and concrete methods
    abstract double calculateArea();  // No implementation

    void display() {  // Concrete method
        System.out.println("Displaying shape");
    }
}
```

### Abstract Class vs Interface

| Feature | Abstract Class | Interface |
|---------|---------------|-----------|
| Instantiation | No | No |
| Constructor | Yes | No |
| Multiple inheritance | Single | Multiple |
| Method types | Abstract + Concrete | Abstract (Java 8+: default, static) |
| Variables | Any | public static final only |
| Use when | "IS-A" with shared code | "CAN-DO" capability |

## The Object Class

Every class in Java implicitly extends `java.lang.Object`.

### Important Methods from Object:
- `toString()` - String representation
- `equals()` - Equality comparison
- `hashCode()` - Hash code for collections
- `getClass()` - Returns Class object
- `clone()` - Creates copy
- `finalize()` - Called before GC (deprecated)

## Polymorphism

"Many forms" - Objects can take multiple forms.

### Compile-time Polymorphism (Overloading)
Same method name, different parameters.

### Runtime Polymorphism (Overriding)
Method call determined at runtime based on actual object type.

```java
Animal myPet = new Dog();  // Dog object, Animal reference
myPet.makeSound();  // Calls Dog's makeSound() - runtime polymorphism!
```

## Casting

### Upcasting (Implicit)
```java
Dog dog = new Dog();
Animal animal = dog;  // Automatic - safe
```

### Downcasting (Explicit)
```java
Animal animal = new Dog();
Dog dog = (Dog) animal;  // Manual - risky if not actually a Dog
```

### instanceof Operator
Check object type before casting:
```java
if (animal instanceof Dog) {
    Dog dog = (Dog) animal;
}
```

## Final Keyword

### Final Class
Cannot be extended:
```java
final class String { }
```

### Final Method
Cannot be overridden:
```java
public final void display() { }
```

### Final Variable
Cannot be changed (constant):
```java
public final int MAX_SIZE = 100;
```

## Best Practices

1. **Favor composition over inheritance**
   - Use inheritance for true IS-A relationships
   - Use composition for HAS-A relationships

2. **Design for inheritance or prohibit it**
   - Document self-use of overridable methods
   - Make classes final if not designed for extension

3. **Don't call overridable methods in constructors**
   - Can lead to unexpected behavior

4. **Use @Override annotation**
   - Catches errors at compile time

5. **Keep inheritance hierarchies shallow**
   - Deep hierarchies are hard to understand

6. **Follow Liskov Substitution Principle**
   - Subtypes must be substitutable for their base types
