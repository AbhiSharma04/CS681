package edu.umb.cs681.hw13.Part2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class IndianRailwaysTicketingSystem {

    private Map<String, IndianRail> Rails;
    private final ReentrantLock lock = new ReentrantLock ();
    private boolean done = false;

    public void setDone(){
        lock.lock();
        try {
            done = true;
        }
        finally {
            lock.unlock();
        }
    }

    public IndianRailwaysTicketingSystem() {
        this.Rails = new HashMap<> ();

        IndianRail indianRailA=new IndianRail ("RailA", 4);
        IndianRail indianRailB=new IndianRail ("RailB", 3);
        Rails.put("RailA", indianRailA);
        Rails.put("RailB", indianRailB);
    }

    public void bookTicket(String trainName, int seats) {
        lock.lock();
        try {
            if (done) return;
            IndianRail rail = Rails.get(trainName);

            if (rail != null) {
                System.out.println("total available tickets in " + trainName + " " + rail.getSeats());

                if (rail.getSeats() >= seats) {
                    rail.setSeats(rail.getSeats() - seats);
                    System.out.println("Booking " + seats + " seats on " + trainName);
                    System.out.println("Ticket booked in "+ rail.getName ()+"   Seat lefts:" +rail.getSeats());
                } else {
                    System.out.println("Cannot book " + seats + " seats on " + trainName);
                }
            } else {
                System.out.println("Train " + trainName + " not found");
            }
        } finally {
            lock.unlock();
        }
    }

}


