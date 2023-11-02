package edu.umb.cs681.hw06;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class YearWiseShootings implements Runnable{
    public void YearWiseShootings () {

        DataProcessing data = new DataProcessing ();
        List<List<String>> matrix = data.readfile ();

        Map<Integer, Long> yearCounts = matrix.stream ().skip (1).filter (row -> 1 < row.size ())
                .map (row -> {
                    String dateTimeStr = row.get (1);
                    ZonedDateTime dateTime = ZonedDateTime.parse (dateTimeStr, DateTimeFormatter.ofPattern ("yyyy-MM-dd HH:mm:ssX"));
                    return dateTime.getYear ();
                })
                .collect (Collectors.groupingBy (
                        year -> year,
                        Collectors.counting ()
                ));

        for (Map.Entry<Integer, Long> entry : yearCounts.entrySet ()) {
            System.out.println ("Year: " + entry.getKey () + ", Victim: " + entry.getValue ());
        }
    }

    @Override
    public void run () {
        YearWiseShootings();
    }
}
