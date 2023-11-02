package edu.umb.cs681.hw06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataProcessing {


    public List<List<String>> readfile () {
        Path path = Paths.get ("/Users/abhisheksharma/Downloads/tmp0fqfugh4.csv");
        List<List<String>> matrix = null;
        try (Stream<String> lines = Files.lines (path)) {
            matrix = lines.map (line -> {
                        return Stream.of (line.split (",")).map (value -> value.substring (0, value.length ()))
                                .collect (Collectors.toList ());
                    })
                    .collect (Collectors.toList ());
        } catch (IOException ex) {
            System.out.println (ex);
        }

        return matrix;
    }



    public static void main (String[] args) throws InterruptedException {
        MostVictimRace o1= new MostVictimRace ();
        No_of_male_female_victim o2= new No_of_male_female_victim ();
        YearWiseShootings o3=new YearWiseShootings ();
        Thread t1 = new Thread (o1);
        Thread t2 = new Thread (o2);
        Thread t3 = new Thread (o3);
        t1.start ();
        t2.start ();
        t3.start ();
        t1.join ();
        t2.join ();
        t3.join ();
        System.out.println ("All tasks completed successfully");




    }
}





