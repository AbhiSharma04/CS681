package edu.umb.cs681.hw13.Part1;

class PassengerRunnable implements Runnable {
    private IndianRailwaysTicketingSystem bookingManager;
    private String preferredRail;
    private int seats;

    public PassengerRunnable(IndianRailwaysTicketingSystem manager, String trainName, int seats) {
        this.bookingManager = manager;
        this.preferredRail = trainName;
        this.seats = seats;
    }

    public void run() {
        bookingManager.bookTicket(preferredRail, seats);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        IndianRailwaysTicketingSystem manager = new IndianRailwaysTicketingSystem();


        PassengerRunnable passengerRunnable1= new PassengerRunnable (manager,"RailA",1);
        PassengerRunnable passengerRunnable2= new PassengerRunnable (manager, "RailA", 1);
        PassengerRunnable passengerRunnable3= new PassengerRunnable (manager, "RailA", 2);
        PassengerRunnable passengerRunnable4= new PassengerRunnable (manager, "RailB", 3);


        Thread passenger1 = new Thread(passengerRunnable1);
        Thread passenger2 = new Thread(passengerRunnable2);
        Thread passenger3 = new Thread(passengerRunnable3);
        Thread passenger4 = new Thread(passengerRunnable4);

        passenger1.start();
        passenger2.start();
        passenger3.start();
        passenger4.start ();




        try {
            passenger1.join();
            passenger2.join();
            passenger3.join();
            passenger4.join ();
        } catch (InterruptedException e) {
            System.out.println (e.getMessage ());
        }
    }
}