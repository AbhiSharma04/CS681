package edu.umb.cs681.hw14;



public class LineChartObserver implements Observer<StockEvent> {
    @Override
    public void update (Observable<StockEvent> sender, StockEvent event) {
        System.out.println ("Line Chart Stock ticker= " + event.ticker () + " quote:- "+ event.quote ());
    }
}
