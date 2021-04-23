package exercise;

public class AcessTest {
    public static void main(String[] args) {
        //access to static nested class
        //Note: A static nested class interacts with the instance members of its outer class (and other classes)
        //just like any other top-level class. In effect, a static nested class is behaviorally a top-level class
        // that has been nested in another top-level class for packaging convenience.
        DataStructure ds = DataStructure.Factory.createDataStructure();
        // access to inner class
        DataStructure.EvenIterator iter = ds.new EvenIterator();

        while (iter.hasNext())
            System.out.print(iter.next() + " ");
    }
}
