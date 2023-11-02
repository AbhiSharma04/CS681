package edu.umb.cs681.hw05;

import java.util.List;

public class No_of_male_female_victim {
    public void No_of_male_female_victim () {
        DataProcessing data = new DataProcessing ();
        List<List<String>> matrix = data.readfile ();


        //find how many males & females were victim
        long malecount = matrix.stream ().filter (row -> "Male".equalsIgnoreCase (row.get (4))).count ();
        System.out.println ("No of male victim :" + malecount);
        long femalecount = matrix.stream ().filter (row -> "Female".equalsIgnoreCase (row.get (4))).count ();
        System.out.println ("No of female victim :" + femalecount);

        System.out.println ();

    }
}
