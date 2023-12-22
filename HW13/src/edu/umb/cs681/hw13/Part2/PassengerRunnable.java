package edu.umb.cs681.hw13.Part2;

import java.util.concurrent.locks.ReentrantLock;

class PassengerRunnable implements Runnable {
    private IndianRailwaysTicketingSystem bookingManager;
    private String preferredRail;
    private int seats;

    private ReentrantLock lock = new ReentrantLock ();
    private boolean done = false;

    public PassengerRunnable(IndianRailwaysTicketingSystem manager, String trainName, int seats) {
        this.bookingManager = manager;
        this.preferredRail = trainName;
        this.seats = seats;
    }

    public void run() {
        lock.lock();
        try {
            bookingManager.bookTicket(preferredRail, seats);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args)  {
        IndianRailwaysTicketingSystem manager = new IndianRailwaysTicketingSystem();

        PassengerRunnable passengerRunnable1= new PassengerRunnable (manager,"RailA",4);
        PassengerRunnable passengerRunnable2= new PassengerRunnable (manager, "RailB", 2);
        PassengerRunnable passengerRunnable3= new PassengerRunnable (manager, "RailA", 2);


        Thread passenger1 = new Thread(passengerRunnable1);
        Thread passenger2 = new Thread(passengerRunnable2);
        Thread passenger3 = new Thread(passengerRunnable3);


        passenger1.start();
        passenger2.start();
        passenger3.start ();


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println (e.getMessage ());
        }


        manager.setDone();


        try {
            passenger1.join();
        } catch (InterruptedException e) {
            System.out.println (e.getMessage ());
        }
        try {
            passenger2.join();
        } catch (InterruptedException e) {
            System.out.println (e.getMessage ());
        }
        try {
            passenger3.join ();
        } catch (InterruptedException e) {
            System.out.println (e.getMessage ());
        }


    }
}