package amq.utils;

import amq.pojo.Person;

import java.util.Calendar;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonTestBuilder {
    public static Person buildPerson(){
        Stream<String> hobbies = Stream.of("guitar", "swimming", "netflix");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 1981);
        c.set(Calendar.MONTH, Calendar.SEPTEMBER);
        c.set(Calendar.DAY_OF_MONTH, 27);
        c.set(Calendar.HOUR_OF_DAY, 1);
        c.set(Calendar.MINUTE, 23);
        c.set(Calendar.SECOND, 41);
        return Person.builder()
                .name("GAP")
                .cash(1000)
                .hobbies(hobbies.collect(Collectors.toList()))
                .birthDate(FormatUtils.dateToString(c.getTime()))
                .build();

    }
}
