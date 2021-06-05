package collection;

import java.util.Arrays;
import java.util.Collections;

public class NameSort {
    public static void main(String[] args) {
        Name nameArray[] = {
            new Name("John", "Smith"),
            new Name("Karl", "Ng"),
            new Name("Jeff", "Smith"),
            new Name("Tom", "Rich")
        };
        Collections.sort(Arrays.asList(nameArray));
        for (int i = 0; i < nameArray.length; i++) {
            System.out.println(nameArray[i]);
        }
    }
}

/**
 * Name
 */
class Name implements Comparable<Name> {
    private final String firstName, lastName;

    public Name(String firsName, String lastName) {
        if (firsName == null || lastName == null) {
            System.err.println("Iilegal Param");;
        }
        this.firstName = firsName;
        this.lastName = lastName;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Name)) {
            return false;
        }
        Name n = (Name) obj;
        return firstName.equals(n.firstName()) && lastName.equals(n.lastName());
    }
    
    @Override
    public int hashCode() {
        return 31*firstName.hashCode() + lastName.hashCode();
    }

    @Override
    public int compareTo(Name o) {
        int lastCmp = lastName.compareTo(o.lastName);
        return (lastCmp != 0 ? lastCmp: firstName.compareTo(o.firstName));
    }
    
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}