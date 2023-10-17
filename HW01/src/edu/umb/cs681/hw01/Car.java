package edu.umb.cs681.hw01;
import java.util.*;
import java.util.stream.Collectors;

public class Car {

    private String make,model;
    private int mileage,year,DominationCount;
    private float price ;

    public Car(String make, String model, int mileage, int year, float price) {
        this.make = make; this.model = model; this.mileage = mileage; this.year = year; this.price = price;
    }


    public String GetMake() {
        return make;
    }

    public String GetModel() {
        return model;
    }

    public float GetMileage() {
        return mileage;
    }

    public int GetYear() {
        return year;
    }

    public float GetPrice() {
        return price;
    }

    public void setDominationCount(List<edu.umb.cs681.hw01.Car> Cars){
        DominationCount=0;
        for (edu.umb.cs681.hw01.Car car: Cars){
            if(this.GetPrice()<=car.GetPrice()&&this.GetMileage()>=car.GetMileage()&&this.GetYear()>=car.GetYear()){
                if(this.GetPrice()<car.GetPrice()||this.GetMileage()>car.GetMileage()||this.GetYear()>car.GetYear()){
                    DominationCount++;
                }
            }

        }
    }

    public int getDominationCount(){

        return DominationCount;
    }

    public static List<Car> createcarlist(){
        Car verna = new Car("Hyundai","Verna", 10,2013,25000);
        Car Fortuner = new Car("Toyota","Fortuner",8,2019,45000);
        Car audi = new Car("audi","q3",12,2017,50000);
        Car swift = new Car("Suzuki","Swift",16,2015,15000);
        Car city = new Car("Honda","city",13,2010,20000);

        List<Car> carsList = new ArrayList<> ();
        carsList.add(verna);
        carsList.add(Fortuner);
        carsList.add(audi);
        carsList.add(swift);
        carsList.add(city);
        return carsList;
    }

    public static void main (String[] args) {
        List<Car> CarList= createcarlist ();
       // System.out.println (CarList);


        //Price based sorting
        Map<Boolean, List<Car>> carsGroupedByPriceThreshold =
                CarList.stream()
                        .collect(Collectors.partitioningBy(
                                (Car car)->car.GetPrice ()>25000));
        DoubleSummaryStatistics highPriceGrp = carsGroupedByPriceThreshold.get(true).stream()
                .collect(Collectors.summarizingDouble ( (car) -> car.GetPrice()));
        DoubleSummaryStatistics lowPriceGrp = carsGroupedByPriceThreshold.get(false).stream()
                .collect(Collectors.summarizingDouble ( (car) -> car.GetPrice()));

        System.out.println ("Average price for high "+ highPriceGrp.getAverage ());
        System.out.println ("Highest price for high "+ highPriceGrp.getMax ());
        System.out.println ("Lowest price for high "+ highPriceGrp.getMin ());
        System.out.println ("No of cars for high "+ highPriceGrp.getCount ());

        System.out.println ("Average price for low "+ lowPriceGrp.getAverage ());
        System.out.println ("Highest price for low "+ lowPriceGrp.getMax ());
        System.out.println ("Lowest price for low "+ lowPriceGrp.getMin ());
        System.out.println ("No of cars for low "+ lowPriceGrp.getCount ());


        //Mileage based sorting



        Map<Boolean, List<Car>> carsGroupedByMileageThreshold =
                CarList.stream()
                        .collect(Collectors.partitioningBy(
                                (Car car)->car.GetMileage()>12));
        DoubleSummaryStatistics HighMilGrp = carsGroupedByMileageThreshold.get(true).stream()
                .collect(Collectors.summarizingDouble ( (car) -> car.GetMileage()));
        DoubleSummaryStatistics LowMilGrp = carsGroupedByMileageThreshold.get(false).stream()
                .collect(Collectors.summarizingDouble ( (car) -> car.GetMileage()));

        System.out.println ("Average Mileage for high "+ HighMilGrp.getAverage ());
        System.out.println ("Highest Mileage for high "+ HighMilGrp.getMax ());
        System.out.println ("Lowest Mileage for high "+ HighMilGrp.getMin ());
        System.out.println ("No of cars for high "+ HighMilGrp.getCount ());

        System.out.println ("Average Mileage for low "+ LowMilGrp.getAverage ());
        System.out.println ("Highest Mileage for low "+ LowMilGrp.getMax ());
        System.out.println ("Lowest Mileage for low "+ LowMilGrp.getMin ());
        System.out.println ("No of cars for low "+ LowMilGrp.getCount ());






        //Year Based Sorting
        Map<Boolean, List<Car>> carsGroupedByYearThreshold =
                CarList.stream()
                        .collect(Collectors.partitioningBy(
                                (Car car)->car.GetYear()>2015));

        DoubleSummaryStatistics HighYearGrp = carsGroupedByYearThreshold.get(true).stream()
                .collect(Collectors.summarizingDouble ( (car) -> car.GetYear()));
        DoubleSummaryStatistics LowYearGrp = carsGroupedByYearThreshold.get(false).stream()
                .collect(Collectors.summarizingDouble ( (car) -> car.GetYear()));

        System.out.println ("Average Year for high "+ HighYearGrp.getAverage ());
        System.out.println ("Highest Year for high "+ HighYearGrp.getMax ());
        System.out.println ("Lowest Year for high "+ HighYearGrp.getMin ());
        System.out.println ("No of cars for high "+ HighYearGrp.getCount ());

        System.out.println ("Average Year for low "+ LowYearGrp.getAverage ());
        System.out.println ("Highest Year for low "+ LowYearGrp.getMax ());
        System.out.println ("Lowest Year for low "+ LowYearGrp.getMin ());
        System.out.println ("No of cars for low "+ LowYearGrp.getCount ());





        //Domination count based Sorting
        for(Car car:CarList){
            car.setDominationCount(CarList);
        }
        Map<Boolean, List<Car>> carsGroupedByDomThreshold =
                CarList.stream()
                        .collect(Collectors.partitioningBy(
                                (Car car)->car.getDominationCount ()>1));


        DoubleSummaryStatistics HighDomGrp = carsGroupedByDomThreshold.get(true).stream()
                .collect(Collectors.summarizingDouble ( (car) -> car.getDominationCount()));
        DoubleSummaryStatistics LowDomGrp = carsGroupedByDomThreshold.get(false).stream()
                .collect(Collectors.summarizingDouble ( (car) -> car.getDominationCount()));

        System.out.println ("Average Domination Count for high "+ HighDomGrp.getAverage ());
        System.out.println ("Highest Domination Count for high "+ HighDomGrp.getMax ());
        System.out.println ("Lowest Domination Count for high "+ HighDomGrp.getMin ());
        System.out.println ("No of cars for high "+ HighDomGrp.getCount ());

        System.out.println ("Average Domination Count for low "+ LowDomGrp.getAverage ());
        System.out.println ("Highest Domination Count for low "+ LowDomGrp.getMax ());
        System.out.println ("Lowest Domination Count for low "+ LowDomGrp.getMin ());
        System.out.println ("No of cars for low "+ LowDomGrp.getCount ());


    }
}
