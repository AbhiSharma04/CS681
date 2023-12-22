package edu.umb.cs681.hw14;

public class ThreeDObserver implements Observer<StockEvent> {
    @Override
    public void update(Observable sender, StockEvent event) {

        System.out.println ("Three Stock ticker= " +  event.ticker () + " quote:- "+ event.quote ());

    }
}
