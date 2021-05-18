package practice;


public class AcessTest {
    public static void main(String[] args) {

        //1. Static nested class just like other top-level class nested for packaging, having no access to enclosing member
        //2. Inner class - Non-static nested class have access to enclosing members.
        //
        // access to static nested class
        //Note: A static nested class interacts with the instance members of its outer class (and other classes)
        //just like any other top-level class. In effect, a static nested class is behaviorally a top-level class
        // that has been nested in another top-level class for packaging convenience.
        DataStructure ds = DataStructure.Factory.createDataStructure();
        // access to inner class
        // DataStructure.DataStructureIterator iterator = DataStructure.Factory.createDataStructure().new EvenIterator();
        DataStructure.EvenIterator iter = ds.new EvenIterator();

        while (iter.hasNext())
            System.out.print(iter.next() + " ");

        System.out.println(" ");
        
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        /*
        When to Use Nested Classes, Local Classes, Anonymous Classes, and Lambda Expressions
        Nested classes enable you to logically group classes that are only used in one place, increase the use of encapsulation, and create more readable and maintainable code.

        Local class: Use it if you need to create more than one instance of a class, access its constructor, or introduce a new, named type (because, for example, you need to invoke additional methods later).

        Anonymous class: Use it if you need to declare fields or additional methods.

        Lambda expression:

            Use it if you are encapsulating a single unit of behavior that you want to pass to other code. For example, you would use a lambda expression if you want a certain action performed on each element of a collection, when a process is completed, or when a process encounters an error.

            Use it if you need a simple instance of a functional interface and none of the preceding criteria apply (for example, you do not need a constructor, a named type, fields, or additional methods).

        Nested class: Use it if your requirements are similar to those of a local class, you want to make the type more widely available, and you don't require access to local variables or method parameters.
         */
    }
}
