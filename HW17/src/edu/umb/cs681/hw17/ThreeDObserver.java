package edu.umb.cs681.hw17;

public class ThreeDObserver implements Observer<edu.umb.cs681.hw17.StockEvent> {
    @Override
    public void update(Observable sender, StockEvent event) {

        System.out.println ("Three Stock ticker=" +  event.ticker () + "quote:- "+ event.quote ());

    }
}
