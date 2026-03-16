# Topic 2: Objects and Classes - Complete Guide

## What is Object-Oriented Programming (OOP)?

Object-Oriented Programming is a programming paradigm based on the concept of "objects".
- **Object**: A real-world entity that has state (data) and behavior (methods)
- **Class**: A blueprint/template for creating objects

## Key Concepts

### 1. Class
A class is a user-defined data type that serves as a blueprint for creating objects.
It defines:
- **Fields/Attributes**: Variables that store data (state)
- **Methods**: Functions that define behavior
- **Constructors**: Special methods for initializing objects

### 2. Object
An object is an instance of a class. When a class is defined, no memory is allocated
until an object of that class is created.

### 3. The Four Pillars of OOP

#### Encapsulation
- Bundling data and methods that work on that data within a single unit (class)
- Hiding internal details using access modifiers (private, protected, public)
- Providing public getters and setters to access private data

#### Abstraction
- Hiding complex implementation details and showing only essential features
- Achieved through abstract classes and interfaces

#### Inheritance
- Creating new classes from existing classes
- Promotes code reusability
- Covered in detail in Topic 5

#### Polymorphism
- Ability to take multiple forms
- Method overloading and overriding
- Covered throughout multiple topics

## Access Modifiers

| Modifier | Same Class | Same Package | Subclass | Anywhere |
|----------|-----------|--------------|----------|----------|
| public | Yes | Yes | Yes | Yes |
| protected | Yes | Yes | Yes | No |
| default | Yes | Yes | No | No |
| private | Yes | No | No | No |

## Constructors

### Default Constructor
- Provided automatically if no constructor is defined
- Initializes object with default values

### Parameterized Constructor
- Accepts parameters to initialize object with specific values

### Copy Constructor
- Creates a new object as a copy of an existing object

## The 'this' Keyword
- Refers to the current object instance
- Used to:
  1. Differentiate instance variables from parameters
  2. Call another constructor in the same class
  3. Pass the current object as a parameter
  4. Return the current object

## Static vs Instance Members

### Static (Class-level)
- Belongs to the class, not to any specific object
- Shared among all instances
- Accessed using ClassName.memberName
- Cannot access non-static members directly

### Instance (Object-level)
- Belongs to a specific object
- Each object has its own copy
- Accessed using objectReference.memberName

## Best Practices
1. Keep fields private (encapsulation)
2. Provide public getters and setters
3. Use meaningful names for classes, methods, and variables
4. Follow Java naming conventions
5. Keep classes focused on a single responsibility
