package edu.umb.cs681.hw15;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable extends Observable {


    private ReentrantLock lockTQ = new ReentrantLock ();

    private Map<String, Double> map = new HashMap<>();

    public void changeQuote(String t, double q){
        lockTQ.lock ();
        try{
            map.put(t,q);}
        finally {
            lockTQ.unlock ();
        }
        this.notifyObservers(new StockEvent (t,q));}
}




