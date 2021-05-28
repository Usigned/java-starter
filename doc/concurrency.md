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

## Pausing Execution with Sleep

`Thread.sleep` causes the current thread to suspend execution for a specified period. Two overloaded versions:

- `Thread.sleep(millisecond)`
- `Thread.sleep(millisecond, nanosecond)`

> Note: these sleep times are not guaranteed to be precise, since they are limited by the facilities provided by the underlying OS.

> `sleep throws InterruptedException` when another thread interrupts the current thread while `sleep` is active. 

## Interrupts

- the interrupted thread must support its own interruption

- supporting interruption

  - add return when it triggers method that can throw `InterruptedException`

  - add condition `Thread.interrupted()` to judge whether it's interrupted. It's better to use the following style, and centralized the handling code in a `catch` clause.

    ```java
    if (Thread.interrupted()) {
        throw new InterruptedException();
    }
    ```

- flags

  - `Thread.interrupt` set `interrupt status`

  - `Thread.interrupted` checks and clear the flag
  - `isInterrupted` method does not clear the flag

## Joins

- `join `allows one thread to wait for the completion of another.

- if t is a `Thread` object whose thread is currently occupied,

  ```java
  t.join()
  ```

- can make the thread to wait for specific time

  ```java
  t.join(1000)
  ```

- ??????????????????????????????????????

