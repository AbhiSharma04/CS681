package edu.umb.cs681.hw15;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable extends Observable {


    private static ReentrantLock lock = new ReentrantLock ();

    public static Map<String, Double> map = new HashMap<>();

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
