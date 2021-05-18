# Concurrency

basic concurrency support  and some high-level APIs in `java.util.concurrent`

# Processes and Threads

two basic unit of execution:

- processes
  - have self-contained execution environment
    - complete, private set of basic run-time resources
    - **own memory space**
  - app -> set of cooperating processes
  - communicate through ***Inter Process Communication*** resources: pipes and sockets
  - most JVM implementations run as a single process
    - use `ProcessBuilder` object to create additional processes
- threads - **java mainly concerns**
  - lightweight processes
  - exist within a process
    - every process has at least one
    - share process's resource: memory, open files

# Thread Object

Each thread is associated with an instance of the class `Thread`. Two basic strategy for using `Thread` to create a concurrent application:

- directly control thread creation and management
- abstract thread management , pass the application's tasks to an `executor`

## Defining and Starting a Thread

Two way to creates an `Thread` instance:

- provide a `Runnable` object and pass it to the `Thread` constructor

  ```java
  public class HelloRunnable implements Runnable {
  
      public void run() {
          System.out.println("Hello from a thread!");
      }
  
      public static void main(String args[]) {
          (new Thread(new HelloRunnable())).start();
      }
  
  }
  ```

  

- Subclass `Thread`: `Thread` implements `Runnable`, though its `run` does nothing

  ```java
  public class HelloThread extends Thread {
  
      public void run() {
          System.out.println("Hello from a thread!");
      }
  
      public static void main(String args[]) {
          (new HelloThread()).start();
      }
  
  }
  ```

Both examples invoke `Thread.start` in order to start the new thread.

**Analysis**

- `Runnable` is more general
- subclass `Thread` is easier to use but limited
- In general, we should use the first way