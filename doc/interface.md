# Interface
**Summary of Interfaces**

An interface declaration can contain method signatures, default methods, static methods and constant definitions. The only methods that have implementations are default and static methods.
> Note: an interface can have abitray number of default method and static method.
A class that implements an interface must implement all the methods declared in the interface.

An interface name can be used anywhere a type can be used.

**Questions and Exercises: Interfaces**

**Questions**

1. What methods would a class that implements the java.lang.CharSequence interface have to implement?
    - public int length();
    - public char charAt(int index);
    - public CharSequence subSequence(int start, int end);
    
2. What is wrong with the following interface?
    ```java   
   public interface SomethingIsWrong {
        void aMethod(int aValue){
            System.out.println("Hi Mom");
        }
   }
   ```
   aMethod needs to be either static or default to have a method body.

3. Fix the interface in question 2.
    Modified Code -- declare method to be default
   ```java
    public interface SomethingIsWrong {
        default void aMethod(int aValue){
            System.out.println("Hi Mom");
        }
    }
   ```
4. Is the following interface valid?
   ```java
    public interface Marker {}
   ```
   Valid.

**Exercises**
1. Write a class that implements the CharSequence interface found in the java.lang package. Your implementation should return the string backwards. Select one of the sentences from this book to use as the data. Write a small main method to test your class; make sure to call all four methods.
   
2. Suppose you have written a time server that periodically notifies its clients of the current date and time. Write an interface the server could use to enforce a particular protocol on its clients.


## Inheritance

**Multiple inheritance**

When try to implement two interfaces that have the same signature of default method,
a compile time error will occur.

**Overriding and Hiding Methods**

See code in [Animal.java](../src/Interface/Animal.java)

The distinction between hiding a static method and overriding an instance method has important implications:

- The version of the overridden instance method that gets invoked is the one in the subclass.
- The version of the hidden static method that gets invoked depends on whether it is invoked from the superclass or the subclass.

**Interface Methods**
when the supertypes of a class or interface provide multiple default methods with the same signature, the Java compiler follows inheritance rules to resolve the name conflict.
- Instance methods are preferred over interface default methods.
- Methods that are already overridden by other candidates are ignored. This circumstance can arise when supertypes share a common ancestor(Diamond Problem).
- If two or more independently defined default methods conflict, or a default method conflicts with an abstract method, then the Java compiler produces a compiler error.

**Defining a Method with the Same Signature as a Superclass's Method**

|                              | Superclass Instance Method     | Superclass Static Method       |
| ---------------------------- | ------------------------------ | ------------------------------ |
| **Subclass Instance Method** | Overrides                      | Generates a compile-time error |
| **Subclass Static Method**   | Generates a compile-time error | Hides                          |

**Abstract class vs Interfaces**
