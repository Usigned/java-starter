package exercise;

class DataStructure {

    // Create an array
    private final static int SIZE = 15;
    private int[] arrayOfInts = new int[SIZE];

    public DataStructure() {
        // fill the array with ascending integer values
        for (int i = 0; i < SIZE; i++) {
            arrayOfInts[i] = i;
        }
    }

    public int getSize() {
        return SIZE;
    }

    public int get(int index) {
        return arrayOfInts[index];
    }
    public void printEven() {

        // Print out values of even indices of the array
        DataStructureIterator iterator = this.new EvenIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }

    interface DataStructureIterator extends java.util.Iterator<Integer> { }

    // Inner class implements the DataStructureIterator interface,
    // which extends the Iterator<Integer> interface

    private class EvenIterator implements DataStructureIterator {

        // Start stepping through the array from the beginning
        private int nextIndex = 0;

        public boolean hasNext() {

            // Check if the current element is the last in the array
            return (nextIndex <= SIZE - 1);
        }

        public Integer next() {

            // Record a value of an even index of the array
            Integer retValue = Integer.valueOf(arrayOfInts[nextIndex]);

            // Get the next even element
            nextIndex += 2;
            return retValue;
        }
    }

    //modification starts here
    public void print(DataStructureIterator iterator) {
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println();
    }

    public void print(java.util.function.Function<Integer, Boolean> iterator) {
        for (int i = 0; i < arrayOfInts.length; i++) {
            if (iterator.apply(arrayOfInts[i])) {
                System.out.println(arrayOfInts[i]);
            }
        }
    }

    public static Boolean isEvenIndex(Integer index) {
        return index % 2 ==0;
    }

    public static void main(String s[]) {

        // Fill the array with integer values and print out only
        // values of even indices
        DataStructure ds = new DataStructure();
        //ds.printEven();
        System.out.println("DS Iterator");
        DataStructureIterator iterator = ds.new EvenIterator();
        System.out.println("Anonymous class");
        ds.print(new DataStructure.DataStructureIterator() {
            private int nextIndex = 1;

            public boolean hasNext() {
                return (nextIndex <= SIZE - 1);
            }

            public Integer next() {
                // Record a value of an even index of the array
                Integer retValue = Integer.valueOf(ds.arrayOfInts[nextIndex]);

                // Get the next even element
                nextIndex += 2;
                return retValue;
            }
        });
        System.out.println("Lambda exp");
        ds.print(index -> index % 2 == 0);
        System.out.println("Func reference");
        ds.print(DataStructure::isEvenIndex);
    }
}