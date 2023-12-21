package edu.umb.cs681.hw14;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable extends Observable {


    private ReentrantLock lock = new ReentrantLock ();

    private Map<String, Double> map = new HashMap<>();

    public void changeQuote(String t, double q){
        lock.lock ();
        try{
        map.put(t,q);
        this.notifyObservers(new StockEvent (t,q));}
        finally {
            lock.unlock ();
        }
    }



}
