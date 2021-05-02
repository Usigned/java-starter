package exercise;

public class DSTest {
    public static void main(String[] args) {
        DataStructure ds = new DataStructure();
        ds.print(new DataStructure.DataStructureIterator() {
            int nextIndex = 1;

            @Override
            public boolean hasNext() {
                return nextIndex < (ds.getSize() -1);
            }

            @Override
            public Integer next() {
                Integer next = ds.get(nextIndex);
                nextIndex += 2;
                return next;
            }
        });
    }
}
