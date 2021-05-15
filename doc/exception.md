# Exception

## What is an exception?
An exception is an event, which occurs during the execution of a program, that disrupts the normal flow of the program's instructions.

 Creating an exception object and handing it to the runtime system is called *throwing an exception*.

After a method throws an exception, the runtime system attempts to find something to handle it. The set of possible "somethings" to handle the exception is the ordered list of methods that had been called to get to the method where the error occurred. The list of methods is known as the *call stack* (see the next figure).

![The call stack showing three method calls, where the first method called has the exception handler.](https://docs.oracle.com/javase/tutorial/figures/essential/exceptions-callstack.gif)

The runtime system searches the call stack for exception handler. If the runtime system failed to find a appropriate exception handler, the runtime system terminates.

![The call stack showing three method calls, where the first method called has the exception handler.](https://docs.oracle.com/javase/tutorial/figures/essential/exceptions-errorOccurs.gif)

**Why use exception?**

Using exceptions to manage errors has some advantages over traditional error-management techniques.

## The Catch or Specify Requirement

Code that might throw certain exceptions must be enclosed by either following:

- try statement
- throws clause

Code fails to honor the Catch or Specify Requirement will not compile.

### The Three Kinds of Exceptions

- checked exception
  - exceptional conditions that a well-written application should anticipate and recover from. 
  - Checked exceptions *are subject* to the Catch or Specify Requirement. All exceptions are checked exceptions, except for those indicated by `Error`, `RuntimeException`, and their subclasses.

- error

  - exceptional conditions that are external to the application, and that the application usually cannot anticipate or recover from.

  - Errors *are not subject* to the Catch or Specify Requirement. Errors are those exceptions indicated by `Error` and its subclasses.

- runtime exception

  - exceptional conditions that are internal to the application, and that the application usually cannot anticipate or recover from. usually indicate programming bugs, such as logic errors or improper use of an API.

  - Runtime exceptions *are not subject* to the Catch or Specify Requirement. Runtime exceptions are those indicated by `RuntimeException` and its subclasses.

Errors and runtime exceptions are collectively known as *unchecked exceptions*.

## Catching and Handling Exceptions

three exception handler


- **try**
```
try {
  code
}
catch and finally blocks
```
- **catch**
```
try {
} catch (ExceptionType name) {

} catch (ExceptionTYpe name) {

}
```
  ExceptionType must inherit from _Throwable_ class.

- **finally**

Always executes when the try block exits.

> Note: If the JVM exits while the try or catch code is being executed, then the finally block may not execute. Likewise, if the thread executing the try or catch code is interrupted or killed, the finally block may not execute even though the application as a whole continues.

- **try-with-resources**

resource can be any obj that implements _java.lang.AutoCloseable_, 
which include all obj that implements _java.io.Closeable_.

> kind of like "with" statement in python ?

example:
```
static String readFirstLineFromFile(String path) throws IOException {
    try (BufferedReader br =
                   new BufferedReader(new FileReader(path))) {
        return br.readLine();
    }
}
```

You may declare one or more resources in a try-with-resources statement separated by ; . 

## Specifying the Exceptions Thrown by a Method
Sometimes, it's appropriate for code to catch exceptions that can occur within it.
In other cases, however, it's better to let a method further up the call stack handle the exception.

``public void writeList() throws IOException { }``

## Throw Exception
All exception classes are descendants of the [Throwable](https://docs.oracle.com/javase/8/docs/api/java/lang/Throwable.html) class.
- Throw statement

  ``throw someThrowableObject;``

**Throwable Class and Its Subclasses**

two direct descendants: **Error** and **Exception**

![The Throwable class and its most significant subclasses.](https://docs.oracle.com/javase/tutorial/figures/essential/exceptions-throwable.gif)

- Error: When a dynamic linking failure or other hard failure in the Java virtual machine occurs, the virtual machine throws an `Error`. Simple programs typically do *not* catch or throw `Error`s.
- Exception: indicates that a problem occurred, but not a serious system problem.
  - `RuntimeException` is a subclass of `Exception` reserved for incorrect use of an API, such as `NullPointerException`. Most app shouldn't throw runtime exceptions or its subclass.

### Chained Exceptions

An application often responds to an exception by throwing another exception. In effect, the first exception *causes* the second exception. It can be very helpful to know when one exception causes another.

**Accessing Stack Trace Information**

If higher-level exception handler wants to access stack trace information.

```java
catch (Exception cause) {
    StackTraceElement elements[] = cause.getStackTrace();
    for (int i = 0, n = elements.length; i < n; i++) {       
        System.err.println(elements[i].getFileName()
            + ":" + elements[i].getLineNumber() 
            + ">> "
            + elements[i].getMethodName() + "()");
    }
}
```

**Logging API**

Log exception to an output file using the logging facility in `java.util.logging` package.

```java
try {
    Handler handler = new FileHandler("OutFile.log");
    Logger.getLogger("").addHandler(handler);
}
catch (IOException e) {
    Logger logger = Logger.getLogge("package.name");
    StackTraceElement elements[] = e.getStackTrace();
    for (int i = 0, n = elements.length; i < n; i++) {
        logger.log(Level.WARNING, elements[i].getMethodName());
    }
}
```

## Creating Exception Classes

You can create new type of exception rather than using the existing ones if:

- you need an exception type that isn't represented by those in Java platform.

- help user to distinguish your exceptions from others
- your code throw more than one related exception
- your package is independent and self-contained

see [Creating Exception Classes]([Creating Exception Classes (The Java™ Tutorials > Essential Classes > Exceptions) (oracle.com)](https://docs.oracle.com/javase/tutorial/essential/exceptions/creating.html)) for more.

### Unchecked Exception

when to use checked exception or unchecked exception?

Guideline:

-  If a client can reasonably be expected to recover from an exception, make it a checked exception.
- If a client cannot do anything to recover from the exception, make it an unchecked exception

## Advantages of Exception

- Separating Error-Handling Code from "Regular" Code

- Propagating Errors Up the Call Stack

- Grouping and Differentiating Error Types

