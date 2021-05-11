# Generic Type

Why use Generics?

- Stronger type checks at compile time.

- Elimination of casts.
  
- Enabling programmers to implement generic algorithms.


Type Parameter Naming Conventions
- E - Element
- K - Key
- N - Number
- T - Type
- V -Value
- S,U,V - 2nd, 3rd, 4th types

Raw Types
```java
public class Box<T> {
    public void set(T t) {/* */}
    //...
}
```

If the actual type argument is omitted, you create a raw type.
```
Box rawBox = new Box();
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
