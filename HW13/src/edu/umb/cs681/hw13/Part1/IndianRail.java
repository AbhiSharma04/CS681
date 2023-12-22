package edu.umb.cs681.hw13.Part1;

public class IndianRail {


    private String name;
    private int seats;

    public IndianRail(String name, int seats) {
        this.name = name;
        this.seats = seats;
    }

    public String getName() {
        return name;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}