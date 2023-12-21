package edu.umb.cs681.hw15;


import java.util.concurrent.locks.ReentrantLock;

public class StockRunnable implements Runnable{
    private boolean done = false;
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
            lock.lock ();
            try {

                if ( done ) {
                    System.out.println ("Thread terminated");
                    break;
                }
                LineChartObserver line = new LineChartObserver ();
                StockQuoteObservable s = new StockQuoteObservable ();
                s.addObserver (line);


                s.changeQuote ("Abhi", 24.0);
            }
            finally {
                lock.unlock ();
            }
            try{

                Thread.sleep (100);
            } catch (InterruptedException e) {
                System.out.println ("interrupted");
            }
        }

    }


    public static void main (String[] args)  {

        StockRunnable s=new StockRunnable ();


        for(int i=0;i<10;i++){

            Thread t= new Thread(s);
            t.start();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println (e.getMessage ());
            }
            s.setDone ();
            t.interrupt ();
        }
    }
}
