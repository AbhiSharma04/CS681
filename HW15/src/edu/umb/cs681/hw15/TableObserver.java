package edu.umb.cs681.hw15;


public class TableObserver implements Observer<StockEvent> {
    @Override
    public void update(Observable sender, StockEvent event) {

        System.out.println ("Table Stock ticker=" +event.ticker () + "quote:- "+ event.quote ());

    }
}
