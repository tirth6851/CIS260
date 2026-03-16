# Topic 4: Input/Output (I/O) - Complete Guide

## Overview
Java I/O (Input/Output) is used to process input and produce output. Java uses the concept of a stream to make I/O operations fast.

## What is a Stream?
A stream is a sequence of data. In Java, streams are ordered sequences of data that have a source (input stream) or destination (output stream).

## Types of Streams

### 1. Byte Streams (8-bit)
- Handle I/O of raw binary data
- Classes: `InputStream`, `OutputStream`
- Used for: Images, audio, binary files

### 2. Character Streams (16-bit Unicode)
- Handle I/O of character data, automatically handling character encoding
- Classes: `Reader`, `Writer`
- Used for: Text files

## Stream Hierarchy

```
                    InputStream (abstract)
                         │
        ┌────────────────┼────────────────┐
        │                │                │
   FileInputStream  BufferedInputStream  DataInputStream
        │                │                │
   [byte streams]   [buffered]        [primitive data]

                    Reader (abstract)
                         │
        ┌────────────────┼────────────────┐
        │                │                │
   FileReader      BufferedReader    InputStreamReader
        │                │                │
   [char streams]   [buffered]        [bridge]
```

## Console I/O

### Scanner Class
Most versatile way to read input from console.

```java
Scanner scanner = new Scanner(System.in);
String name = scanner.nextLine();  // Read a line
int age = scanner.nextInt();        // Read an integer
double salary = scanner.nextDouble(); // Read a double
```

### BufferedReader
More efficient for reading large amounts of text.

```java
BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
String line = reader.readLine();
```

## File I/O

### Reading Files

#### Using Scanner (simple)
```java
Scanner fileScanner = new Scanner(new File("data.txt"));
while (fileScanner.hasNextLine()) {
    String line = fileScanner.nextLine();
}
```

#### Using BufferedReader (efficient)
```java
BufferedReader br = new BufferedReader(new FileReader("data.txt"));
String line;
while ((line = br.readLine()) != null) {
    // process line
}
br.close();
```

#### Using Files class (Java 7+)
```java
List<String> lines = Files.readAllLines(Paths.get("data.txt"));
String content = Files.readString(Paths.get("data.txt"));
```

### Writing Files

#### Using FileWriter
```java
FileWriter writer = new FileWriter("output.txt");
writer.write("Hello, World!");
writer.close();
```

#### Using BufferedWriter (efficient)
```java
BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
bw.write("Hello, World!");
bw.newLine();  // Platform-independent newline
bw.close();
```

#### Using PrintWriter (convenient)
```java
PrintWriter pw = new PrintWriter(new FileWriter("output.txt"));
pw.println("Hello, World!");
pw.printf("Value: %d%n", 42);
pw.close();
```

## Try-With-Resources

Always use try-with-resources for automatic resource management:

```java
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        System.out.println(line);
    }
} catch (IOException e) {
    e.printStackTrace();
}
// br is automatically closed!
```

## Serialization

Serialization is the process of converting an object into a byte stream.
Deserialization is the reverse process.

### Serializable Interface
```java
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    // fields...
}
```

### Writing Objects
```java
try (ObjectOutputStream oos = new ObjectOutputStream(
        new FileOutputStream("student.ser"))) {
    oos.writeObject(student);
}
```

### Reading Objects
```java
try (ObjectInputStream ois = new ObjectInputStream(
        new FileInputStream("student.ser"))) {
    Student student = (Student) ois.readObject();
}
```

## NIO.2 (New I/O)

Java 7 introduced the NIO.2 API with Path and Files classes.

### Path Operations
```java
Path path = Paths.get("/home/user/file.txt");
Path fileName = path.getFileName();
Path parent = path.getParent();
boolean exists = Files.exists(path);
```

### File Operations
```java
// Copy
Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

// Move
Files.move(source, target);

// Delete
Files.delete(path);
Files.deleteIfExists(path);

// Create
Files.createFile(path);
Files.createDirectory(path);
Files.createDirectories(path);  // Creates parent dirs too
```

### Walking Directory Tree
```java
Files.walk(Paths.get("/home/user"))
    .filter(Files::isRegularFile)
    .forEach(System.out::println);
```

## Best Practices

1. **Always close resources**
   - Use try-with-resources
   - Or use finally block

2. **Use buffered streams**
   - BufferedReader/BufferedWriter
   - BufferedInputStream/BufferedOutputStream
   - Significantly faster for large files

3. **Choose appropriate stream type**
   - Text data: Character streams (Reader/Writer)
   - Binary data: Byte streams (InputStream/OutputStream)

4. **Handle exceptions properly**
   - I/O operations can fail (file not found, permission denied, etc.)

5. **Use NIO.2 for new code**
   - More flexible and powerful than old File class

6. **Be careful with serialization**
   - Security concerns with untrusted data
   - Consider JSON/XML for data exchange
