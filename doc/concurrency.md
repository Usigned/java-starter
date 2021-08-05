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

  - runnable需要通过线程来启用

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

# Synchronization

使用多线程可能出现以下问题：

- Thread Interference（干扰）
  - 多个线程同时对一个对象进行写入
- memory consistency errors（内存一致性错误）
  - 不同的线程对同一数据的内容不一致
  - `happens-before`关系

## **synchronized方法**

可用于方法或者语句

synchronized方法效果

- 保证只有一个线程能够占用方法，如果一个线程正在执行方法，其他试图调用该方法的线程在该执行完毕前会被阻塞
- synchronized方法执行完毕后会自动与后续的调用建立`happens-before`关系。这保证了对象的变化会被所有线程知晓。
- 构造器无法用`sychronized`修饰
- 可以防止thread interference和memory consistency error，但可能会导致`liveness`问题

## **内在锁(intrinsic locks)**

用于实现同步的机制(intrinsic /monitor lock or monitor)。内在锁有两个作用：

1. 强制单一访问
2. 建立`happens-before`关系

所有对象都有一个内在所与其相关。一般来说，当一个线程需要单一和一致性访问一个对象时，就先需要获取其内在锁并在访问完成后释放。这段时间内，这个线程*拥有*这个对象的内在锁，只要一个线程拥有一个内在锁，其他线程就无法获取同一内在锁，其他线程尝试获取时会被阻塞。

当一个线程释放了内在锁，就会将这个释放操作和后续该锁的操作建立一个`happens-before`的关系。

当一个线程调用了一个synchronized方法，该线程会自动获取这个方法对应对象的内在锁并在`return`后释放这个锁。就算方法因为某种未被捕捉的异常而返回时，该锁也会被释放。

对于`static`的`sychronized`方法，由于`static`是与类（而不是对象）关联的，线程获取的是和类相关的`Class`对象的内在锁。**故控制类中静态区域的内在锁和任何控制实例对象的内在锁不同。**

## **synchronize语句**

必须显示申明需要提供内在锁的对象

```java
public void addName(String name) {
	synchronized(this) {
        lastName = name;
        nameCount++;
    }
    nameList.add(name);
}
```

上述例子中，`addName`方法需要同步更改`lastName`和`nameCount`两个值，但需要避免同步的访问其他对象中的方法`nameList.add`（同步访问其他对象中的方法可能导致`liveness`问题）。如果不使用sychronized语句则需要将二者拆开成一个sychronized方法和一个普通方法。

synchronized语句也可用于细粒度的同步(fine-grained synchronization ?)。试举一例：`MsLunch`中有两个属性`c1`和`c2`且*从未一起使用过*。所有对这些属性的更新必须是同步的，但是当更新`c2`时我们没有理由去阻止`c1`的更新--这样会导致不必要的阻塞。故使用`synchronized`方法或是`this`的内在锁都不是最优的，这时我们可以创建两个单独的对象来提供锁。

```java
public class MsLunch {
    private long c1 = 0;
    private long c2 = 0;
    // Only used for synchronization
	private Object lock1 = new Object();
    private Object lock2 = new Object();
    
    public void inc1() {
        synchronized(lock1) {
            c1++;
        }
    }
    
    public void inc2() {
        synchronized(lock2) {
            c2++;
        }
    }
}
```

> Use this idiom with extreme care. You must be absolutely sure that it really is safe to interleave access of the affected fields.
>
> 使用时小心，必须确保同时更新二者是安全的。

## **Reentrant Synchronization**

一个线程无法获取一个被其他线程拥有的锁，却可以重复获取自己拥有的锁。这种情况被称为`re/entrant synchronization`，其描述的情况是：一段同步的代码直接的或间接的调用另一段同步的代码，并且二者使用的是同一个对象的内在锁。

> 如果没有重入同步(Reentrant Synchronization)机制，同步的代码则需要采取很多额外的措施来防止一个线程自己阻塞自己。

## 原子访问

原子操作定义不多赘述

即使是很简单的表达式`c++`也可以拆成其他多个操作。但是java中一些操作是被认为是原子的:

1. 引用变量和大多数原生类型变量（除了`long/double`）的读写操作。
2. 所有被`volatitle`修饰的变量的读写操作。

原子操作无法被插入(interleaved)，所以它们不会被受`thread interference`影响，但有可能发生内存一致性错误，所以原子操作有时也需要同步。

使用`volatile`关键字可以降低出现错误的可能性，因为其会和后续读取变量的语句建立`happens-before`关系--这样修改就对所有线程可见了。

使用原子变量比使用synchronzied代码更有效率，但是需要程序员自己规避内存一致性错误。

`java.util.concurrent`包中提供一些不依赖于同步的原子操作。

# Liveness

一个并发程序能同时执行的能力叫`liveness`。常见关于`liveness`的问题有`deadlock`, `starvation`和`livelock`。

## 死锁

死锁描述的场景是两个或多个线程都被阻塞，循环等待。

一个例子，规则是一个人向另一个人鞠躬后，直到另一个人回复你之前，你要保持鞠躬状态。假设两个人同时向对方鞠躬，这时就会发生死锁

```java
public class DeadLock {
    static class Friend {
        private final String name;

        Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public synchronized void bow(Friend bower) {
            // bower向this鞠躬
            System.out.printf("%s: %s"
                + " has bowed to me!%n",
                this.name, bower.getName());
            // this向bower回复
            bower.bowBack(this);
        }

        public synchronized void bowBack(Friend bower) {
            System.out.printf("%s: %s has bowed back to me!%n", this.name, bower.getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 当你向一个朋友鞠躬后，在朋友回复你之前，你要一直保持鞠躬
        final Friend alpha = new Friend("Alpha");
        final Friend beta = new Friend("Beta");
        new Thread(() -> alpha.bow(beta)).start();
        new Thread(() -> beta.bow(alpha)).start();
//        ((Runnable) () -> alpha.bow(beta)).run();
//        ((Runnable) () -> beta.bow(alpha)).run();
        
    }
}
```

## Starvation and Livelock

比较少见的情况

1. Starvation

   一个线程无法获取公共资源并继续工作，这种情况发生于共享的公共资源被贪婪的线程长时间占用。比如一个对象中的一个同步方法执行时间很长，如果一个线程经常使用这个方法，那么其他需要使用同步方法的线程就会被阻塞。

2. Livelock

   通常线程会根据相互间的响应采取行动，如果两个线程都是这种行为时就有可能发生livelock。

   和死锁一样，livelock的线程也无法继续，但是他们并未被阻塞，而是忙于响应对方的请求。

   打个比方：一个人到自己左手边给另一个人让路，这时对方也到自己右手边让路，两人又被阻塞了。
