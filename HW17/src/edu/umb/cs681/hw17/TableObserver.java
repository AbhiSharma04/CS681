package edu.umb.cs681.hw17;

public class TableObserver implements Observer<edu.umb.cs681.hw17.StockEvent> {
    @Override
    public void update(Observable sender, StockEvent event) {

        System.out.println ("Table Stock ticker=" +event.ticker () + "quote:- "+ event.quote ());

    }
}
