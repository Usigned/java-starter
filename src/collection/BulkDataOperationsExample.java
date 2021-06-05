package collection;

import java.util.List;
import practice.Person;

public class BulkDataOperationsExample {
    public static void main(String[] args) {
        List<Person> roster = Person.createRoster();
        double avg = roster.stream().filter((person)->{
            return person.getGender() == Person.Sex.MALE;
        }).mapToInt(Person::getAge).average().getAsDouble();
        System.out.println(avg);
        // roster.stream().collect(supplier, accumulator, combiner)
    }
}
