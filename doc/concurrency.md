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

# Guarded Blocks

大多数线程会在特定的情况下触发，如`guardedJoy`只有在共享变量`joy`为真时才能继续。理论上可以用一个循环来阻塞，如下实现。但是这种实现会一直循环，十分浪费资源。

```java
public void guardedJoy() {
    // Simple loop guard. Wastes
    // processor time. Don't do this!
    while(!joy) {}
    System.out.println("Joy has been achieved!");
}
```

有效率的实现方法是`Object.wait`，可用于挂起当前线程。`wait`直到特定事件（虽然可能不是你等待的事件，故需要将其放入循环中）触发前不会返回。

```java
public synchronized guardedJoy() {
    while(!joy) {
        try {
            wait();
        } catch (InterruptedException e) {}
    }
    System.out.println("Joy and efficiency has been achieved!");
}
```

>  **Note**: Always invoke `wait` inside a loop that tests for the condition being waited for. Don't assume that the interrupt was for the particular condition you were waiting for, or that the condition is still true.

Q: 为什么方法是synchronized?

A: 假设调用`wait`的对象是`d`，那么当一个线程触发`d.wait`时，其必须有`d`的内在锁（否则会报错）。而在一个synchronized方法中触发`wait`是获取内在锁的一个简便方法。

当`wait`被调用时，这个线程释放内在锁并挂起。在之后，另一个线程可以获取这个锁并调用`Object.notifyAll`来告知所有等待该锁的线程。

```java
public synchronized notifyJoy() {
	joy = true;
	notifyAll();
}
```

当第二线程释放锁后，第一个线程重新获取该锁并从挂起状态恢复。

> There is a second notification method, `notify`, which wakes up a single thread. Because `notify` doesn't allow you to specify the thread that is woken up, it is useful only in massively parallel applications — that is, programs with a large number of threads, all doing similar chores(works). In such an application, you don't care which thread gets woken up.

# 不可变对象

不可变对象在创建后变不可被改变。最大化依赖于不可变对象是创建简单，可靠代码的有效手段。

在多线程中使用不可变对象可以避免许多麻烦。

## 定义不可变对象的原则

下列原则不一定需要严格遵守，但不遵守时需要恰当的理由

1. 不提供`setter`方法
2. 将所有属性定义为`final`和`private`
3. 不允许子类覆盖方法
   - 最简单的实现方式是将类设为`final`，更好的做法是将构造器设为私有，构造对象任务交给工厂方法。
4. 如果某个属性指向可变对象，那么应该禁止这些对象改变
   - 不提供可以修改这些对象的方法
   - 不要将这些对象的引用传给外界。对于外界在构造时传入的可变对象引用不要储存--尽量将其拷贝存储。同样对于方法返回给外界的可变对象也进行拷贝后再返回。

# High level Concurrency Objects

上述低层api在java早起便已被支持，且足够用于某些基础的任务。但对于更复杂的任务，则需要一些更高层的的api。这对那些要榨干当今多线程，多核处理器性能的高并发应用来说尤为重要。

下列是java5以后支持的一些高层并发api，大多数在`java.util.concurrent`包中。Java Collections Framework中也有支持并非的新型数据结构：

- Lock对象
- Executors：定义了启动和管理线程的高级api。其采用了`java.util.concurrent`中的线程池实现，适合管理大型应用。
- 并非集合：使管理大型集合更容易，减少了对同步的需求。
- 原子变量：有助于减少同步，内存一致性错误。
- ThreadLocalRandom (>= JDK7)：为多线程提供了有效的伪随机实现。

## Lock对象

Synchronized代码实现依赖于内在锁--这是一种可以重复获取的锁，易用但有局限性。`java.util.concurrent.locks`提供更强大的锁实现，`Lock`是其中一基础的接口。

`Lock`对象和内在锁很像：同一时间只能有一个线程拥有`Lock`对象，且通过与其关联的`Condition`对象来支持`wait/notify`机制。

`Lock`相较于内在锁的优势在于：`Lock`可以取消获取锁的请求（而synchronized则会一直阻塞）。`tryLock`可以在锁不可用或是超时时回退。`lockInterruputibly`在获取锁过程中被其他线程打断时回退。

`Lock`可用于解决[死锁问题](#死锁)

> 不得不说官网文档里的那个鞠躬例子是真的绕，自己改下打印输出会好理解很多

| Lock                           | 内在锁                                           |
| ------------------------------ | ------------------------------------------------ |
| 请求不到资源时可以选择放弃请求 | 请求不到资源会一直阻塞                           |
| 通过自己建`Lock`来实现         | 有`synchronized`, `volatile`等关键字可以直接使用 |

# Executors

之前例子中`Runnale`(线程承担的任务)和`Thread`本身联系很紧密，在大型系统中需要将线程创建/管理和应用的其他部分拆开。有这样功能的对象叫做`executors`。

- Executor interface
- Thread pools：最常见的executor实现
- Fork/Join：为多处理器设计的框架(JDK 7)

## Executor接口

`java.util.concurrent`中有3个executor接口

- `Executor`：最简单，支持启动新任务
- `ExecutorService`：`Executor`的子接口，添加了管理任务和executor本身生命周期的功能
- `ScheduledExecutorService`：`ExecutorService`的子接口，支持定时或周期性的执行任务

通常变量类型会被声明为上述接口中的一个，而不是具体的executor类（实现）

### Executor

仅含一个方法`execute`，是为了能直接代替创建线程语句而设计的：如执行一个`Runnable`接口，`e`是一个`Executor`对象，那么你可以将原本创建线程的代码

```java
(new Thread(r)).start(),
```

替换为

```java
e.execute(r);
```

但是`execute`如何执行比较依赖于具体实现，有可能`execute`会和原本一样创建一个新线程来执行，或是使用一个已有的worker线程去执行，亦或是将`r`放入一个等待队列中，等有worker空闲后载执行。

`java.util.concurrent`中的实现都是服务于`ExecutorService`和`ScheduledExecutorService`来提供比较完善、高阶的功能，但也可以用于单纯的`Executor`接口。

### ExecutorService

`execute`方法和`Executor`类似，但还有一个更灵活的`submit`方法

`submit`可以接受`Runnable`对象，也可以接受`Callable`对象

> [Callable (Java Platform SE 8 ) (oracle.com)](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Callable.html)
>
> The `Callable` interface is similar to [`Runnable`](https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html), in that both are designed for classes whose instances are potentially executed by another thread. A `Runnable`, however, does not return a result and cannot throw a checked exception.

> `Callable`对象和`Runnable`类似，都是用于线程执行。但是`Callable`可以有返回值且可以抛出异常，但是`Runnable`不行

`submit`会返回一个`Future`对象用于提取`Callable`的返回值和管理`Callable/Runnable`任务的状态。

`ExecutorService`也提供了用于提交大型`Callable`对象集合的方法。

`ExecutorService`提供了一些用于关闭`executor`的方法，为了支持立即关闭，任务们`(Callable/Runnable)`应该具备正确处理`interrupts`的能力。

### ScheduledExecutorService

在父接口基础上添加了`schedule`方法，其能够支持在一段时延后执行`Runnable/Callable`任务。

另外接口还定义了`scheduledAtFixedRate/scheduleWithFixedDelay`，能够在特定的间隔下重复执行任务。

## 线程池(Thread Pools)

`java.util.concurrent`中大多数executor实现都是基于线程池的，线程池由worker线程组成。这类线程本身的存在和其执行的`Runnable/Callable`任务是分开的，而且多数情况下一个worker线程会被用来执行多个任务。

使用worker线程可以减少创建线程的开销。线程对象会占用较多的内存资源，而且在大型系统中如果存在过多的线程，那么管理这些线程的内存开销也会很大。

一种常见的线程池类型是`fixed thread pool`， 这类线程池中：

- 永远只有特定数量的线程在运行
- 如果某个线程在使用过程中处于某些原因被中止了，那么一个新线程会被自动创建来代替它
- 任务通过外部的队列输送到线程池中，若线程池已满（任务大于worker数），其余任务会在队列中等待

固定线程数的线程池的一个优势是对应用程序很友好，即使在某时刻外界的任务请求激增，系统也不会创建过多的线程而导致崩溃，而是可以有效的降解、消化这些请求。

> An important advantage of the fixed thread pool is that applications using it *degrade gracefully*.

`java.util.concurrent.Executors`中提供了一个工厂方法`newFixedThreadPool`可以很方便的创建固定线程数目的线程池。除此之外，其还提供了其他工厂方法：

- `newCachedThreadPool`：创建一个具有可扩展线程池的executor，其适用于可能会启动很多生命周期较短任务的应用程序。
- `newSingleThreadExecutor`：一次只能执行一个线程的executor
- 一些上述工厂方法的`ScheduledExecutorService`版本

如果有特别需求，可以自己创建`java.util.concurrent.ThreadPoolExecutor/java.util.concurrent.ScheduledThreadExecutor`

## Fork/Join

fork/join框架是一个适用于多处理器的，`ExecutorService`接口的实现。其是为那些可递归拆分为更小任务而设计的。

其功能和其余`ExecutorService`一样能把任务分配给线程池中的worker。但其特殊之处在于，其中的线程在自己任务执行完毕后，可以从其他任务还没执行完的线程中“偷”任务（*work-steal*算法）。

>  The fork/join framework is distinct because it uses a *work-stealing* algorithm.

这个框架核心部分位于`ForkJoinPool`类中，这是一个`AbstractExecutorService`的扩展类，实现了核心的*work-steal*算法和可以执行`ForkJoinTask`

**基本使用**

第一步是将任务分块，伪代码如下

```
if (my portion of the work is small enough):
	do the work directly
else
	split my work into pieces
	invoke each pieces and wait for results
```

将这些代码放入一个`ForkJoinTask`的子类中，通常可以使用`RecursiveTask`或`RecursiveAction`。

当`ForkJoinTask`准备完毕后，创建一个代表所有工作都完成的标志并将其传给`ForkJoinPool`对象的`invoke`方法。

**标准实现**

java 8中已经有一些使用`fork/join`框架的实现，如`java.util.Arrays`中的`parallelSort()`方法，其和`sort()`类似，但采用了fork/join框架具有并发性，在多处理器系统中会比线性排序处理大型数组更快。

`java.util.streams`中也有一些实现，如`parallelStream`等。

