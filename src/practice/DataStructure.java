package practice;

class DataStructure {

    private final static int SIZE = 15;
    private int[] arrayOfInts = new int[SIZE];

    public DataStructure() {
        for (int i = 0; i < arrayOfInts.length; i++) {
            arrayOfInts[i] = i;
        }
    }

    public void printEven() {
        DataStructureIterator iter = this.new EvenIterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }

    interface DataStructureIterator extends java.util.Iterator<Integer> { }

    public class EvenIterator implements DataStructureIterator {

        private int nextIndex = 0;

        @Override
        public boolean hasNext() {
            return (nextIndex <= SIZE - 1);
        }

        @Override
        public Integer next() {
            Integer ret = arrayOfInts[nextIndex];
            nextIndex += 2;
            return ret;
        }
    }

    public static void main(String[] args) {
        DataStructure ds = new DataStructure();
        ds.printEven();
    }
    
    public static class Factory {
        static DataStructure createDataStructure() {
            return new DataStructure();
        }
    }

}
