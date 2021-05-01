package practice;

import java.util.*;
import java.util.function.Supplier;

public class MethodReferenceTest {


    public static <T,SOURCE extends Collection<T>, DEST extends Collection<T>>
        DEST transferElements(SOURCE sourceCollection, Supplier<DEST> collectionFactory) {

        DEST result = collectionFactory.get();
        for (T t : sourceCollection) {
            result.add(t);
        }
        return result;
    }


    public static void main(String[] args) {
        List<Person> roster = Person.createRoster();

        Person[] rosterArray = roster.toArray(new Person[roster.size()]);

        /*
        Local class
         */
        class PersonAgeComparator implements Comparator<Person> {

            @Override
            public int compare(Person a, Person b) {
                return a.getBirthday().compareTo(b.getBirthday());
            }
        }

        Arrays.sort(rosterArray, new PersonAgeComparator().reversed());
        Arrays.stream(rosterArray).forEach(person -> System.out.println(person.getName() + ' ' + person.getGender() + ' ' + person.getBirthday()));

        /*
        Lambda expression
         */
//        Arrays.sort(rosterArray,
//                (a, b) -> { return a.getBirthday().compareTo(b.getBirthday()); })
        Arrays.sort(rosterArray,
                (a, b) -> Person.compareByAge(a, b));

        /*
        Reference to static method
         */
        Arrays.sort(rosterArray, Person::compareByAge);
        Arrays.stream(rosterArray).forEach(person -> System.out.println(person.getName() + ' ' + person.getGender() + ' ' + person.getBirthday()));

        //Reference to an Instance Method of an Arbitrary Object of a Particular Type
        String[] stringArray = { "Barbara", "James", "Mary", "John",
                "Patricia", "Robert", "Michael", "Linda" };
        Arrays.sort(stringArray, String::compareToIgnoreCase);

        //Reference to a Constructor
//        Set<Person> rosterSetLambda = transferElements(roster, () -> new HashSet<>());
        Set<Person> rosterSet = transferElements(roster, HashSet::new);
        rosterSet.stream().forEach(person -> person.printPerson());
    }
}
