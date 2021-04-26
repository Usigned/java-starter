package exercise;

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
        DataStructure.DataStructureIterator iterator = DataStructure.Factory.createDataStructure().new EvenIterator();
        DataStructure.EvenIterator iter = ds.new EvenIterator();

        while (iter.hasNext())
            System.out.print(iter.next() + " ");
    }
}
