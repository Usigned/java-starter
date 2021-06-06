package collection;
import java.util.List;
import java.util.Map;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;

import practice.Person;

public class ReductionExamples {
    public static void main(String[] args) {
        List<Person> roster = Person.createRoster();

        //reduce
        double avg = roster
                .stream()
                .filter(person -> person.getGender() == Person.Sex.MALE)
                .mapToInt(Person::getAge)
                .average()
                .getAsDouble();

        int totalAge = roster
                .stream()
                .mapToInt(Person::getAge)
                .sum();

        int totalAgeReduce = roster
                .stream()
                .mapToInt(Person::getAge)
                .reduce(0, (a, b)->a + b);

        //collect
        Averager avgCollect = roster
                .stream()
                .filter(person -> person.getGender() == Person.Sex.MALE)
                .map(Person::getAge)
                .collect(Averager::new, Averager::accept, Averager::combine);

        List<String> names = roster
                .stream()
                .filter(person -> person.getGender() == Person.Sex.MALE)
                .map(Person::getName)
                .collect(Collectors.toList());

        Map<Person.Sex, List<Person>> byGender = roster
                .stream()
                .collect(Collectors.groupingBy(Person::getGender));

        Map<Person.Sex, Integer> totalAgeByGender = roster
                .stream()
                .collect(Collectors.groupingBy(
                        Person::getGender,
                        Collectors.reducing(0, Person::getAge, Integer::sum)
                ));

        Map<Person.Sex, Double> avgAgeByGender = roster.stream()
                .collect(Collectors.groupingBy(
                        Person::getGender,
                        Collectors.averagingInt(Person::getAge)
                ));
    }
}

class Averager implements IntConsumer {

    private int total = 0;
    private int count = 0;

    public double average() {
        return count > 0 ? ((double) total) / count : 0;
    }

    @Override
    public void accept(int value) {
        total += value;
        count ++;
    }

    public void combine(Averager other) {
        this.total += other.total;
        this.count += other.count;
    }
}
