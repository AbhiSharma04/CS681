package edu.umb.cs681.hw18;

import java.util.List;

public class No_of_male_female_victim {
    public void No_of_male_female_victim() {
        DataProcessing data = new DataProcessing();
        List<List<String>> matrix = data.readfile();


        long maleCount = matrix.stream().parallel ()
                .filter(row -> "Male".equalsIgnoreCase(row.get(4)))
                .count();
        System.out.println("No of male victim: " + maleCount);

        long femaleCount = matrix.stream().parallel ()
                .filter(row -> "Female".equalsIgnoreCase(row.get(4)))
                .count();
        System.out.println("No of female victim: " + femaleCount);

        System.out.println();
    }
}
