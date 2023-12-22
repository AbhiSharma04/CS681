package edu.umb.cs681.hw18;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataProcessing {


    public List<List<String>> readfile () {
        Path path = Paths.get ("tmp0fqfugh4.csv");
        List<List<String>> matrix = null;
        try (Stream<String> lines = Files.lines (path)) {
            matrix = lines.parallel ().map (line -> {
                        return Stream.of (line.split (",")).map (value -> value.substring (0, value.length ()))
                                .collect (Collectors.toList ());
                    })
                    .collect (Collectors.toList ());
        } catch (IOException ex) {
            System.out.println (ex);
        }

        return matrix;
    }



    public static void main (String[] args) {
        //DataProcessing data = new DataProcessing ();
        MostVictimRace o1= new MostVictimRace ();
        No_of_male_female_victim o2= new No_of_male_female_victim ();
        YearWiseShootings o3=new YearWiseShootings ();
        //List<List<String>> matrix = data.readfile ();
        o1.MostVictimRace ();
        o2.No_of_male_female_victim ();
        o3.YearWiseShootings ();

//        data.No_of_male_female_victim ();
//        data.MostVictimRace ();
//        data.YearWiseShootings ();

    }
}





