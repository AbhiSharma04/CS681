package edu.umb.cs681.hw17;


import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class StockRunnable implements Runnable{
    private volatile boolean done = false;
    private final ReentrantLock lock = new ReentrantLock();

    public void setDone(){
        lock.lock();
        try {
            done = true;
        }
        finally {
            lock.unlock();
        }
    }
    @Override
    public void run () {

        while(true){

                if ( done ) {
                    System.out.println ("Thread terminated");
                    break;
                }
                LineChartObserver line = new LineChartObserver ();
                StockQuoteObservable s = new StockQuoteObservable ();
                TableObserver table = new TableObserver ();
                ThreeDObserver threeDObserver= new ThreeDObserver ();
                s.addObserver (line);
                s.addObserver (table);
                s.addObserver (threeDObserver);

                s.changeQuote ("Abhi", Math.random ());


            }
            try{

                Thread.sleep (100);
            } catch (InterruptedException e) {
                System.out.println ("flag interrupted");
            }
        }


    public static void main (String[] args)  {

        StockRunnable s=new StockRunnable ();


        for(int i=0;i<15;i++){

            Thread t= new Thread(s);
            t.start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println (e.getMessage ());
            }
            s.setDone ();
            t.interrupt ();
        }
    }
}
