package edu.umb.cs681.hw18;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MostVictimRace {
    public void MostVictimRace() {
        DataProcessing data = new DataProcessing();
        List<List<String>> matrix = data.readfile();

        Map<String, Long> wordFrequencies = matrix.stream().parallel()
                .filter(row -> row.size() > 5)
                .map(row -> row.get(5))
                .collect(Collectors.groupingByConcurrent(
                        word -> word,
                        Collectors.counting()
                ));

        String mostRepeatedVictimRace = null;
        long noOfTimes = Long.MIN_VALUE;

        for (Map.Entry<String, Long> entry : wordFrequencies.entrySet()) {
            String word = entry.getKey();
            long frequency = entry.getValue();

            if (frequency > noOfTimes) {
                mostRepeatedVictimRace = word;
                noOfTimes = frequency;
            }
        }
        if (mostRepeatedVictimRace != null) {
            System.out.println("Most repeated Victim race: " + mostRepeatedVictimRace);
            System.out.println("No. of times : " + noOfTimes);
        } else {
            System.out.println("The map is empty.");
        }
    }
}
