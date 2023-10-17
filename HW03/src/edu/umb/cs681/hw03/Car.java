package edu.umb.cs681.hw03;

import java.util.ArrayList;
import java.util.List;

public class Car {

    private String make, model;
    private int mileage, year, DominationCount;
    private  double price;

    public Car (String make, String model, int mileage, int year, float price) {
        this.make = make;
        this.model = model;
        this.mileage = mileage;
        this.year = year;
        this.price = price;
    }


    public String GetMake () {
        return make;
    }

    public String GetModel () {
        return model;
    }

    public float GetMileage () {
        return mileage;
    }

    public int GetYear () {
        return year;
    }

    public double GetPrice () {
        return price;
    }

    public void setDominationCount (List<edu.umb.cs681.hw03.Car> Cars) {
        DominationCount = 0;
        for (edu.umb.cs681.hw03.Car car : Cars) {
            if ( this.GetPrice () <= car.GetPrice () && this.GetMileage () >= car.GetMileage () && this.GetYear () >= car.GetYear () ) {
                if ( this.GetPrice () < car.GetPrice () || this.GetMileage () > car.GetMileage () || this.GetYear () > car.GetYear () ) {
                    DominationCount++;
                }
            }

        }
    }

    public int getDominationCount () {

        return DominationCount;
    }

    public static List<edu.umb.cs681.hw03.Car> createcarlist () {
        edu.umb.cs681.hw03.Car verna = new edu.umb.cs681.hw03.Car ("Hyundai", "Verna", 10, 2013, 25000);
        edu.umb.cs681.hw03.Car Fortuner = new edu.umb.cs681.hw03.Car ("Toyota", "Fortuner", 8, 2019, 45000);
        edu.umb.cs681.hw03.Car audi = new edu.umb.cs681.hw03.Car ("audi", "q3", 12, 2017, 50000);
        edu.umb.cs681.hw03.Car swift = new edu.umb.cs681.hw03.Car ("Suzuki", "Swift", 16, 2015, 15000);
        edu.umb.cs681.hw03.Car city = new edu.umb.cs681.hw03.Car ("Honda", "city", 13, 2010, 20000);

        List<edu.umb.cs681.hw03.Car> carsList = new ArrayList<> ();
        carsList.add (verna);
        carsList.add (Fortuner);
        carsList.add (audi);
        carsList.add (swift);
        carsList.add (city);
        return carsList;
    }

    public static void main (String[] args) {

        List<edu.umb.cs681.hw03.Car> CarList= createcarlist ();


        double averagePrice
                = CarList.stream()
                .map( car -> car.GetPrice () )
                .reduce( new CarPriceResultHolder(),
                        (CarPriceResultHolder result, Double price)->{ result.totalprice (price);
                            return result;},(finalResult,intermediateResult)->finalResult).getAverage();


        System.out.println ("Average=" + averagePrice);

    }

}

