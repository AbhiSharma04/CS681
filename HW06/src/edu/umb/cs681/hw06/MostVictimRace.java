package edu.umb.cs681.hw06;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MostVictimRace implements Runnable{
    public void MostVictimRace () {

        DataProcessing data = new DataProcessing ();
        List<List<String>> matrix = data.readfile ();


        Map<String, Long> wordFrequencies = matrix.stream ()
                .filter (row -> 5 < row.size ())
                .map (row -> row.get (5))
                .collect (Collectors.groupingBy (
                        word -> word,
                        Collectors.counting ()
                ));

        String mostRepeatedVictimRace = null;
        long Nooftimes = Long.MIN_VALUE;

        for (Map.Entry<String, Long> entry : wordFrequencies.entrySet ()) {
            String word = entry.getKey ();
            long frequency = entry.getValue ();

            if ( frequency > Nooftimes ) {
                mostRepeatedVictimRace = word;
                Nooftimes = frequency;
            }
        }
        if ( mostRepeatedVictimRace != null ) {
            System.out.println ("Most repeated Victim race: " + mostRepeatedVictimRace);
            System.out.println ("No. of times : " + Nooftimes);
            System.out.println ();
        } else {
            System.out.println ("The map is empty.");
        }
    }

    @Override
    public void run () {
        MostVictimRace();
    }
}
