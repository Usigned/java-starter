package Interface;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SimpleTimeClient implements TimeClient {
    private LocalDateTime dateAndTime;

    public SimpleTimeClient() {
        dateAndTime = LocalDateTime.now();
    }
    @Override
    public void setTime(int hour, int minute, int second) {
        dateAndTime = LocalDateTime.of(
                LocalDate.from(dateAndTime),
                LocalTime.of(hour, minute, second)
        );
    }

    @Override
    public void setDate(int day, int month, int year) {
        dateAndTime = LocalDateTime.of(
                LocalDate.of(year, month, day),
                LocalTime.from(dateAndTime)
        );
    }

    @Override
    public void setDateAndTime(int day, int month, int year, int hour, int minute, int second) {
        dateAndTime = LocalDateTime.of(year, month, day, hour, minute, second);
    }

    @Override
    public LocalDateTime getLocalDateTime() {
        return dateAndTime;
    }

    @Override
    public String toString() {
        return dateAndTime.toString();
    }

    public static void main(String... args) {
        TimeClient client = new SimpleTimeClient();
        System.out.println(client);
        System.out.println(client.getZonedDateTime("afdaf"));
    }
}
