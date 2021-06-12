# Java Tutorial
basic java knowledge
example & exercises

main material from [oracle](https://docs.oracle.com/javase/tutorial) (pure english)

reference material [On java8](https://usigned.github.io/OnJava8/) (Chinese Edition)

## Learning the Java Language

### Object-Oriented Programming Concept

    What Is an Object?
    What Is a Class?
    What Is Inheritance?
    What Is an Interface?
    What Is a Package?
    Questions and Exercises: Object-Oriented Programming Concepts
### Language Basics

    Variables
    Primitive Data Types
    Arrays
    Summary of Variables
    Questions and Exercises: Variables
    Operators
        Assignment, Arithmetic, and Unary Operators
        Equality, Relational, and Conditional Operators
        Bitwise and Bit Shift Operators
        Summary of Operators
    Questions and Exercises: Operators
    Expressions, Statements, and Blocks
    Questions and Exercises: Expressions, Statements, and Blocks
    Control Flow Statements
        The if-then and if-then-else Statements
        The switch Statement
        The while and do-while Statements
        The for Statement
        Branching Statements
        Summary of Control Flow Statements
    Questions and Exercises: Control Flow Statements
### Classes and Objects

    Classes
        Declaring Classes
        Declaring Member Variables
        Defining Methods
        Providing Constructors for Your Classes
        Passing Information to a Method or a Constructor
    Objects
        Creating Objects
        Using Objects
    More on Classes
        Returning a Value from a Method
        Using the this Keyword
        Controlling Access to Members of a Class
        Understanding Class Members
        Initializing Fields
        Summary of Creating and Using Classes and Objects
    Questions and Exercises: Classes
    Questions and Exercises: Objects
    Nested Classes
        Inner Class Example
        Local Classes
        Anonymous Classes
        Lambda Expressions
        Method References
        When to Use Nested Classes, Local Classes, Anonymous Classes, and Lambda Expressions
    Questions and Exercises: Nested Classes
    Enum Types
    Questions and Exercises: Enum Types
### Annotations

    Annotations Basics
    Declaring an Annotation Type
    Predefined Annotation Types
    Type Annotations and Pluggable Type Systems
    Repeating Annotations
    Questions and Exercises: Annotations
### Interfaces and Inheritance

    Interfaces
        Defining an Interface
        Implementing an Interface
        Using an Interface as a Type
        Evolving Interfaces
        Default Methods
        Summary of Interfaces
        Questions and Exercises: Interfaces
    Inheritance
        Multiple Inheritance of State, Implementation, and Type
        Overriding and Hiding Methods
        Polymorphism
        Hiding Fields
        Using the Keyword super
        Object as a Superclass
        Writing Final Classes and Methods
        Abstract Methods and Classes
        Summary of Inheritance
        Questions and Exercises: Inheritance
### Numbers and Strings

    Numbers
        The Numbers Classes
        Formatting Numeric Print Output
        Beyond Basic Arithmetic
        Summary of Numbers
        Questions and Exercises: Numbers
    Characters
    Strings
        Converting Between Numbers and Strings
        Manipulating Characters in a String
        Comparing Strings and Portions of Strings
        The StringBuilder Class
        Summary of Characters and Strings
    Autoboxing and Unboxing
    Questions and Exercises: Characters and Strings
### Generics (Updated)

    Why Use Generics?
    Generic Types
        Raw Types
    Generic Methods
    Bounded Type Parameters
        Generic Methods and Bounded Type Parameters
    Generics, Inheritance, and Subtypes
    Type Inference
    Wildcards
        Upper Bounded Wildcards
        Unbounded Wildcards
        Lower Bounded Wildcards
        Wildcards and Subtyping
        Wildcard Capture and Helper Methods
        Guidelines for Wildcard Use
    Type Erasure
        Erasure of Generic Types
        Erasure of Generic Methods
        Effects of Type Erasure and Bridge Methods
        Non-Reifiable Types
    Restrictions on Generics
    Questions and Exercises: Generics
### Packages

    Creating and Using Packages
        Creating a Package
        Naming a Package
        Using Package Members
        Managing Source and Class Files
        Summary of Creating and Using Packages
        Questions and Exercises: Creating and Using Packages

## Essential Java Classes

### Exceptions
    What Is an Exception?
    The Catch or Specify Requirement
    Catching and Handling Exceptions
        The try Block
        The catch Blocks
        The finally Block
    The try-with-resources Statement
    Putting It All Together
    Specifying the Exceptions Thrown by a Method
    How to Throw Exceptions
        Chained Exceptions
        Creating Exception Classes
    Unchecked Exceptions — The Controversy
    Advantages of Exceptions
    Summary
    Questions and Exercises

### Basic I/O
    I/O Streams
        Byte Streams
        Character Streams
        Buffered Streams
        Scanning and Formatting
            Scanning
            Formatting
        I/O from the Command Line
        Data Streams
        Object Streams
    File I/O (Featuring NIO.2)
        What Is a Path? (And Other File System Facts)
        The Path Class
            Path Operations
        File Operations
        Checking a File or Directory
        Deleting a File or Directory
        Copying a File or Directory
        Moving a File or Directory
        Managing Metadata (File and File Store Attributes)
        Reading, Writing, and Creating Files
        Random Access Files
        Creating and Reading Directories
        Links, Symbolic or Otherwise
        Walking the File Tree
        Finding Files
        Watching a Directory for Changes
        Other Useful Methods
        Legacy File I/O Code
    Summary
    Questions and Exercises: Basic I/O

### Concurrency
    Processes and Threads
    Thread Objects
        Defining and Starting a Thread
        Pausing Execution with Sleep
        Interrupts
        Joins
        The SimpleThreads Example
    Synchronization
        Thread Interference
        Memory Consistency Errors
        Synchronized Methods
        Intrinsic Locks and Synchronization
        Atomic Access
    Liveness
        Deadlock
        Starvation and Livelock
    Guarded Blocks
    Immutable Objects
        A Synchronized Class Example
        A Strategy for Defining Immutable Objects
    High Level Concurrency Objects
        Lock Objects
        Executors
            Executor Interfaces
            Thread Pools
        Fork/Join
        Concurrent Collections
        Atomic Variables
        Concurrent Random Numbers
    For Further Reading
    Questions and Exercises: Concurrency

### The Platform Environment
    Configuration Utilities
        Properties
        Command-Line Arguments
        Environment Variables
        Other Configuration Utilities
    System Utilities
        Command-Line I/O Objects
        System Properties
        The Security Manager
        Miscellaneous Methods in System
    PATH and CLASSPATH
    Questions and Exercises: The Platform Environment

### Regular Expressions
    Introduction
    Test Harness
    String Literals
    Character Classes
    Predefined Character Classes
    Quantifiers
    Capturing Groups
    Boundary Matchers
    Methods of the Pattern Class
    Methods of the Matcher Class
    Methods of the PatternSyntaxException Class
    Unicode Support
    Additional Resources
    Questions and Exercises: Regular Expressions

## Logs

---
    Date: 2021/6/12
    current: collection finished, JAR almost finished (example excluded), start essential class/ system configuration
---
    Date:2021/6/5
    current: collection.aggregate operations.reduction
---
	Date:2021/5/29
	current: collection.interface ~-map interface
---
	Date:2021/5/28
	curent: pause concurrency, begin collection. ~ - interface.the list interface
---
    Date: 2021/5/18
    current: begin concurrency, pause for a while to do quantization experiment
---
	Date: 2021/5/18
	current: finish Basic I/O (basicly ignoring some part of Files), next: Concurrency
---
	Date: 2021/5/16
	current: Basic I/O, ~ - I/O from the command line

---
    Date: 2021/5/15
    current: Finish Exception
---
    Date: 2021/5/12
    current: Finish Learning the [Java Language](#Learning-the-Java-Language), next: Essential Java Classes
---
    Date: 2021/5/12
    current: Finish Generics
---
    Date: 2021/5/11
    current: Finish Numbers and Strings, begin Generics. ~ - Generrics/Type inference
---
    Date: 2021/5/10
    current: Strings
---
    Date: 2021/5/8
    current: Numbers and Strings
---
    Date: 2021/5/7
    current: Interfaces and Inheritance