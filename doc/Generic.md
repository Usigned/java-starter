# Generic Type

**Why use Generics?**

- Stronger type checks at compile time.

- Elimination of casts.
  
- Enabling programmers to implement generic algorithms.


**Type Parameter Naming Conventions**
- E - Element
- K - Key
- N - Number
- T - Type
- V -Value
- S,U,V - 2nd, 3rd, 4th types

**Raw Types**
```java
public class Box<T> {
    public void set(T t) {/* */}
    //...
}
```

If the actual type argument is omitted, you create a raw type.
```
Box rawBox = new Box();
// basicly same as:
Box<Object> objBox = new Box<>();
```

Usually appears in legacy code.

**The Diamond**

In Java SE 7 and later, you can replace the type arguments required to invoke the constructor of a generic class with an empty set of type arguments (<>) as long as the compiler can determine, or infer, the type arguments from the context.

```
Box<Integer> integerBox = new Box<>();
```

Note that here integerBox is not a raw type.

**Bounded Type Parameters**

```
public <U extends Number> void inspect(U u) {}
```

Multiple bounds

``<T extends B1 & B2 & B3>``

If one of the bounds is a class, it must be specified first.

**Wildcards**

- Unbounded
```
List<?>
```
- Upper Bounded Wildcards: 
``` 
List<? extends Number>
```
- Lower Bounded Wildcards:
```
List<? super Integer>
```

## Type Erasure
To **implement** generics, the Java compiler applies type erasure to:
- Replace all type parameters in generic types with their bounds or Object if the type parameters are unbounded. The produced bytecode, therefore, contains only ordinary classes, interfaces, and methods.
- Insert type casts if necessary to preserve type safety.
- Generate bridge methods to preserve polymorphism in extended generic types.

Type erasure ensures that no new classes are created for parameterized types; consequently, generics incur no runtime overhead.

### Bridge Method

This section is copied from [Effects of Type Erasure and Bridge Methods](https://docs.oracle.com/javase/tutorial/java/generics/bridgeMethods.html).

When compiling a class or interface that extends a parameterized class or implements a parameterized interface, the compiler may need to create a synthetic method, which is called a bridge method, as part of the type erasure process. You normally don't need to worry about bridge methods, but you might be puzzled if one appears in a stack trace.

see [Node.java](../src/generic/Node.java)

After type erasure, the Node and MyNode classes become:

```java
public class Node {

    public Object data;

    public Node(Object data) { this.data = data; }

    public void setData(Object data) {
        System.out.println("Node.setData");
        this.data = data;
    }
}

public class MyNode extends Node {

    public MyNode(Integer data) { super(data); }

    public void setData(Integer data) {
        System.out.println("MyNode.setData");
        super.setData(data);
    }
}
```

After type erasure, the method signatures do not match; the Node.setData(T) method becomes Node.setData(Object). As a result, the MyNode.setData(Integer) method does not override the Node.setData(Object) method.

To solve this problem and preserve the polymorphism of generic types after type erasure, the Java compiler generates a bridge method to ensure that subtyping works as expected.

For the MyNode class, the compiler generates the following bridge method for setData:

```java
class MyNode extends Node {

    // Bridge method generated by the compiler
    //
    public void setData(Object data) {
        setData((Integer) data);
    }

    public void setData(Integer data) {
        System.out.println("MyNode.setData");
        super.setData(data);
    }

    // ...
}
```

The bridge method MyNode.setData(object) delegates to the original MyNode.setData(Integer) method. As a result, the n.setData("Hello"); statement calls the method MyNode.setData(Object), and a ClassCastException is thrown because "Hello" can't be cast to Integer.

## Heap Pollution
Heap pollution occurs when a variable of a parameterized type refers to an object that is not of that parameterized type. This situation occurs if the program performed some operation that gives rise to an unchecked warning at compile-time.  For example, heap pollution occurs when mixing raw types and parameterized types, or when performing unchecked casts.

If you ensure that your code compiles without warnings, then no heap pollution can occur.

## Questions and Exercises: Generics
1. Write a generic method to count the number of elements in a collection that have a specific property (for example, odd integers, prime numbers, palindromes).

Implementation see [ProcessCollection.java](../src/generic/ProcessCollection.java)
   
2. Will the following class compile? If not, why?
```java
public final class Algorithm {
    public static <T> T max(T x, T y) {
        return x > y ? x: y;
    }
}
```
No, > operator can only be applied to primitive numeric types.

3. Write a generic method to exchange the positions of two different elements in an array.
```java
public final class Algorithm {
    public <T> void swap(T[] a, int i, int j) {
        T temp = a[i];
        a[j] = a[i];
        a[i] = temp;
    } 
}
```

4. If the compiler erases all type parameters at compile time, why should you use generics?
- Java compiler enforces tighter type check on generic code at compiler time.
- generics support programming types as parameters.
- generics enable you to implement generic algorithm.

5. What is the following class converted to after type erasure?
```java
public class Pair<K, V> {

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey(); { return key; }
    public V getValue(); { return value; }

    public void setKey(K key)     { this.key = key; }
    public void setValue(V value) { this.value = value; }

    private K key;
    private V value;
}
```
After Type Erasure, all K, V are replaced with Object.
```java
public class Paris {
    
    public Paris(Object key, Object value) {
        this.key = key;
        this.value = value;
    }

    public Object getKey(); { return key; }
    public Object getValue(); { return value; }

    public void setKey(Object key)     { this.key = key; }
    public void setValue(Object value) { this.value = value; }

    private Object key;
    private Object value;
}
```
6. What is the following method converted to after type erasure?
```
public static <T extends Comparable<T>>
    int findFirstGreaterThan(T[] at, T elem) {
    // ...
}
```
After Type Erasure:
```
pubilc static int findFirstGreaterThan(Comparable[] at, Comparable elem) { } 
```

7. Will the following method compile? If not, why?
```
public static void print(List<? extends Number> list) {
    for (Number n : list)
        System.out.print(n + " ");
    System.out.println();
}
```
yes

8. Write a generic method to find the maximal element in the range [begin, end) of a list.

```java
import java.util.Collection;
import java.util.List;

public final class Algorithm {
    public static <T extends Comparable<? super T>> T findMaximal(List<T> list, int begin, int end) {
        T curr = list.get(begin);
        for (int i = begin + 1; i < end; i++) {
            if (curr.compareTo(list.get(i)) < 0) {
                curr = list.get(i);
            }
        }
        return curr;
    }
}
```

9. Will the following class compile? If not, why?
```java
public class Singleton<T> {

    public static T getInstance() {
        if (instance == null)
            instance = new Singleton<T>();

        return instance;
    }

    private static T instance = null;
}
```
No, can't create static field of the type parameter.

10. Given the following classes:
```java
class Shape { /* ... */ }
class Circle extends Shape { /* ... */ }
class Rectangle extends Shape { /* ... */ }

class Node<T> { /* ... */ }
```
Will the following code compile? If not, why?
```
Node<Circle> nc = new Node<>();
Node<Shape>  ns = nc;
```

No, Node<Circle> is not a subtype of Node<Shape>.

11. Consider this class: 
```java
class Node<T> implements Comparable<T> {
    public int compareTo(T obj) { /* ... */ }
    // ...
}
```

Will the following code compile? If not, why?
```
Node<String> node = new Node<>();
Comparable<String> comp = node;
```
Yes

12. How do you invoke the following method to find the first integer in a list that is relatively prime to a list of specified integers?
```public static <T>
    int findFirst(List<T> list, int begin, int end, UnaryPredicate<T> p)
```
Note that two integers a and b are relatively prime if gcd(a, b) = 1, where gcd is short for greatest common divisor.

see [Algorithm.java](../src/generic/Algorithm.java)