package edu.umb.cs681.hw04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Distance {
    public static double get(List<Double> p1, List<Double> p2) {
        return Distance.get(p1, p2, new Euclidean());
    }

    public static double get(List<Double> p1, List<Double> p2, DistanceMetric metric) {
        return metric.distance(p1, p2);
    }

    public static List<List<Double>> matrix(List<List<Double>> points) {
        return Distance.matrix(points, new Euclidean());
    };

    public static List<List<Double>> matrixold(List<List<Double>> points, DistanceMetric metric){
        int numOfPoints = points.size();
        List<List<Double>> distanceMatrix = Distance.initDistanceMatrix(numOfPoints);
        List<Double> current, peer;

        for(int i=0; i < numOfPoints; i++) {
            current = points.get(i);
            for(int j=0; j < numOfPoints; j++) {
                peer = points.get(j);
                double distance = Distance.get(current, peer, metric);
                distanceMatrix.get(i).set(j, distance);
            }
        }

        return distanceMatrix;
    }

    public static List<List<Double>> matrix(List<List<Double>> points, DistanceMetric metric) {
        int numOfPoints = points.size();
        List<List<Double>> distanceMatrix = Distance.initDistanceMatrix(numOfPoints);

        IntStream.range(0, numOfPoints).forEach(i -> {
            List<Double> current = points.get (i);
            List<Double> distances = IntStream.range (0, numOfPoints)
                    .mapToObj (j -> {
                        List<Double> peer = points.get (j);
                        return Distance.get (current, peer, metric);
                    })
                    .collect (Collectors.toList ());

            distanceMatrix.set (i, distances);
        });

        return distanceMatrix;
    }

    static List<List<Double>> generatePoints () {
        List<List<Double>> points = new ArrayList<> ();
        Random random = new Random ();

        for (int i = 0; i < 1000; i++) {
            List<Double> point = new ArrayList<> ();
            for (int j = 0; j < 100; j++) {
                point.add (random.nextDouble (10));
            }
            points.add (point);
        }

        return points;
    }

    private static List<List<Double>> initDistanceMatrix(int numOfPoints){
        List<List<Double>> distanceMatrix = new ArrayList<> (numOfPoints);
        for(int i=0; i < numOfPoints; i++) {
            Double[] vector = new Double[numOfPoints];
            Arrays.fill(vector, 0.0);
            distanceMatrix.add( Arrays.asList(vector) );
        }
        return distanceMatrix;
    }


}