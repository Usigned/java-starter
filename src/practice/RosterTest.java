package practice;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.lang.Iterable;

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
    *
    * Remember, to use a lambda expression, you need to implement a functional interface.
    *
    A functional interface is any interface that contains only one abstract method.
    A functional interface may contain one or more default methods or static methods.
    Lambda expression syntax:
        (parameters) -> { statements; }
        //or
        parameters -> expression
     */
    // Approach 6: Use Standard Functional Interfaces with Lambda Expressions
    // Standard Functional interfaces Predicate with Generic
    public static void printPersonsWithPredicate(
            List<Person> roster,
            Predicate<Person> tester) {
        for (Person person : roster) {
            if (tester.test(person)) {
                person.printPerson();
            }
        }
    }

    // Lambda try out
//    interface GreetingService {
//        void helloEnglish(String message);
//        void helloChinese(String message);
//    }

    /*
    Approach 7: Use Lambda Expressions Throughout Application
     */
    public static void processPersons (
            List<Person> roster,
            Predicate<Person> tester,
            Function<Person, String> mapper,
            Consumer block) {
        for (Person person : roster)
            if (tester.test(person)) {
                String data = mapper.apply(person);
                block.accept(data);
            }
    }

    /*
    Approach 8: Use Generics More Extensively
    all function interface with generic
     */
    public static <X, Y> void processElements (
            Iterable<X> source,
            Predicate<X> tester,
            Function<X, Y> mapper,
            Consumer<Y> blocker
    ) {
        for (X x : source) {
            if (tester.test(x)) {
                Y y = mapper.apply(x);
                blocker.accept(y);
            }
        }
    }
    /*
    Approach 9: Use Aggregate Operations That Accept Lambda Expressions as Parameters

    a stream carries values from a source, such as collection, through a pipeline
    A pipeline is a sequence of stream operations, which in this example is filter- map-forEach
    In addition, aggregate operations typically accept lambda expressions as parameters, enabling you to customize how they behave
     */

    public static void main(String[] args) {
        List<Person> roster = Person.createRoster();
//        printPersons(roster,
//                (Person p) -> p.getGender() == Person.Sex.MALE &&
//                        p.getAge() <= 25 &&
//                        p.getAge() >= 18
//                );
//        processPersons(
//                roster,
//                p -> p.gender == Person.Sex.MALE,
//                p -> p.getEmailAddress(),
//                email -> System.out.println(email)
//        );
//        processElements(
//                roster,
//                p -> p.getGender() == Person.Sex.FEMALE,
//                p -> p.getEmailAddress(),
//                info -> {
//                    System.out.println(info);
//                }
//        );
        roster.stream().filter(p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 10)
                .map(p -> p.getEmailAddress())
                .forEach(email -> System.out.println(email));


    }


}
