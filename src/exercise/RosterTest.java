package exercise;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.Comparator;
import java.util.function.Predicate;
import java.lang.Iterable;
import java.time.chrono.IsoChronology;

public class RosterTest {

    /*
    Approach 1: Create Methods That Search for Members That Match One Characteristic

    brittle(fragile) implementation, need update if Person class changed
     */
    public static void printPersonsOlderThan(List<Person> roster, int age) {
        for (Person p: roster) {
            if (p.getAge() >= age)
                p.printPerson();
        }
    }

    /*
    Approach 2: Create More Generalized Search Methods

    little more general than Approach 1,but not enough
    search condition are fixed with age
     */
    public static void printPersonsWithAgeRange(List<Person> roster, int low, int high) {
        for (Person p : roster) {
            if (low <= p.getAge() && p.getAge() <= high)
                p.printPerson();
        }
    }

    /*
    Approach 3: Specify Search Criteria Code in a Local Class
     */
    interface CheckPerson {
        boolean test(Person p);
    }

    // local class
    class CheckPersonService implements CheckPerson {
        @Override
        public boolean test(Person p) {
            return p.gender == Person.Sex.MALE &&
                    p.getAge() >= 18 &&
                    p.getAge() <= 25;
        }
    }

    public static void printPersons(List<Person> roster, CheckPerson checker) {
        for (Person p : roster) {
            if (checker.test(p))
                p.printPerson();
        }
    }

    /*
    Approach 4: Specify Search Criteria Code in an Anonymous Class
    Approach 5: Specify Search Criteria Code with a Lambda Expression
     */
    public static void main(String[] args) {

    }

}
