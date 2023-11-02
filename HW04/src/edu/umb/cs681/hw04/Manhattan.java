package edu.umb.cs681.hw04;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static edu.umb.cs681.hw04.Distance.generatePoints;

public class Manhattan implements DistanceMetric{
    @Override
    public double distance(List<Double> p1, List<Double> p2) {
        double sum = IntStream.range (0, p1.size ()).mapToDouble (i -> p2.get (i) - p1.get (i)).map (Math::abs).sum ();
        return sum;
    }



    public static void main(String[] args) {
        System.out.println ("Welcome to Manhattan Class");


        List<List<Double>> points= generatePoints ();


        Manhattan man= new Manhattan();
        double distanceman= man.distance(points.get (0),points.get (1));
        System.out.println (distanceman);
        Distance distance= new Distance ();
        List<List<Double>> matrix= distance.matrix (points,man);
        List<List<Double>> matrixold=distance.matrixold(points,man);
        boolean isequal=matrix.equals (matrixold);
        if(isequal)
        System.out.println ("Matrix are equal");

    }
}