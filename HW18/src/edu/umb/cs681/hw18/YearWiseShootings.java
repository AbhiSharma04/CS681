package edu.umb.cs681.hw18;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class YearWiseShootings {
    public void YearWiseShootings() {
        DataProcessing data = new DataProcessing();
        List<List<String>> matrix = data.readfile();

        Map<Integer, Long> yearCounts = matrix.stream().parallel()  // Using parallelStream
                .skip(1)
                .filter(row -> row.size() > 1)
                .map(row -> {
                    String dateTimeStr = row.get(1);
                    ZonedDateTime dateTime = ZonedDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX"));
                    return dateTime.getYear();
                })
                .collect(Collectors.groupingByConcurrent(
                        year -> year,
                        Collectors.counting()
                ));

        yearCounts.entrySet().forEach(entry ->
                System.out.println("Year: " + entry.getKey() + ", Victim: " + entry.getValue())
        );
    }
}
