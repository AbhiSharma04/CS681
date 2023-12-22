package edu.umb.cs681.hw13.Part1;

import java.util.HashMap;
import java.util.Map;

public class IndianRailwaysTicketingSystem {

    private Map<String, IndianRail> Rails;

    public IndianRailwaysTicketingSystem() {
        this.Rails = new HashMap<> ();
        IndianRail indianRailA=new IndianRail ("RailA", 3);
        IndianRail indianRailB=new IndianRail ("RailB", 2);
        Rails.put("RailA", indianRailA);
        Rails.put("RailB", indianRailB);
    }

    public void bookTicket(String trainName, int seats) {
        IndianRail rail = Rails.get(trainName);
        if (rail != null && rail.getSeats() >= seats) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println (e.getMessage ());
            }
            rail.setSeats(rail.getSeats() - seats);
            System.out.println("Booking " + seats + " seats on " + trainName + ". Seats left: " + rail.getSeats());
        } else {
            System.out.println("Cannot book " + seats + " seats on " + trainName);
        }
    }
}


