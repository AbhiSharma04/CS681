package edu.umb.cs681.hw03;

public class CarPriceResultHolder {

    private int numCarExamined;
    private double totalprice;


    public void totalprice(double price){
        totalprice+=price;
        numCarExamined++;
    }
    public double getAverage(){
        if(numCarExamined>0)
        return totalprice/numCarExamined;

        return 0;
    }
}
