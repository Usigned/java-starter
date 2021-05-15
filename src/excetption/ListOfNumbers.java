package excetption;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListOfNumbers {
    private List<Integer> list;
    private static final int SIZE = 10;

    public ListOfNumbers () {
        list = new ArrayList<Integer>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            list.add(i);
        }
    }

    public void writeList() {
        // IO exception is a checked exception
        PrintWriter out = null;
        try {
            Handler handler = new FileHandler("OutFile.log");
            Logger.getLogger("").addHandler(handler);

            System.out.println("Entering" + " try statement");

            out = new PrintWriter(new FileWriter("./OutFile.txt"));

            for (int i = 0; i < SIZE; i++) {
                // index out of bound is a unchecked exception
                out.println("list[" + i + "]: " + list.get(i));
            }
        } catch (IOException e) {
            System.out.println("Caught IOException: " + e.getMessage());

            Logger logger = Logger.getLogger("exception.ListOfNumbers");
            // Stack Trace
            StackTraceElement elements[] = e.getStackTrace();
            for (int i = 0, n = elements.length; i < n; i++) {
                System.err.println(elements[i].getFileName()
                        + ":" + elements[i].getLineNumber()
                        + ">> "
                        + elements[i].getMethodName() + "()");

                // log exception
                logger.log(Level.WARNING, elements[i].getMethodName());
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Caught IndexOutOfBoundsException: " + e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
            else {
                System.out.println("PrintWriter not open");
            }
        }
    }


    public static void main(String[] args) {
        new ListOfNumbers().writeList();
    }


}
