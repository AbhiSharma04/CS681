package edu.umb.cs681.hw09;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer{

    private ReentrantLock lock = new ReentrantLock ();
    private boolean done = false;


    public RunnableCancellablePrimeFactorizer (long dividend, long from, long to) {
        super (dividend,from,to);
    }


    public boolean isDone () {
        return done;
    }

    public void setDone(){
        lock.lock();
        try{
            done = true;
        }finally{
            lock.unlock(); }
    }

    protected boolean isEven(long n){
        if(n%2 == 0){ return true; }
        else{ return false; }
    }

    @Override
    public void generatePrimeFactors() {
        long divisor = from;
        while( dividend != 1 && divisor <= to ){
            lock.lock();
            try{
                if (done) {
                    System.out.println("Stopped generating prime factors.");
                    return;
                }
                if( divisor > 2 && isEven(divisor)) {
                divisor++;
                continue;
                }
                if(dividend % divisor == 0) {
                factors.add(divisor);
                dividend /= divisor;
                }else {
                if(divisor==2){ divisor++; }
                else{ divisor += 2; }
                }
            }
            finally {
                lock.unlock ();
            }
        }
    }

    public static void main (String[] args) {
        // Factorization of 36 with a separate thread
        System.out.println("Factorization of 36");
        RunnableCancellablePrimeFactorizer runnable = new RunnableCancellablePrimeFactorizer (36, 2, (long)Math.sqrt(36));
        Thread thread = new Thread(runnable);
        //runnable.setDone ();
        System.out.println("Thread #" + thread.threadId() +
                " performs factorization in the range of " + runnable.getFrom() + "->" + runnable.getTo());
        thread.start();
        try {
            Thread.sleep(10);
            runnable.setDone();
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final result: " + runnable.getPrimeFactors() + "\n");
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Final result: " + runnable.getPrimeFactors() + "\n");

        // Factorization of 84 with two threads
        System.out.println("Factorization of 84");
        LinkedList<RunnableCancellablePrimeFactorizer> runnables = new LinkedList<RunnableCancellablePrimeFactorizer>();
        LinkedList<Thread> threads = new LinkedList<Thread>();

        runnables.add( new RunnableCancellablePrimeFactorizer(84, 2, (long)Math.sqrt(84)/2 ));
        runnables.add( new RunnableCancellablePrimeFactorizer(84, 1+(long)Math.sqrt(84)/2, (long)Math.sqrt(84) ));

        thread = new Thread(runnables.get(0));
        threads.add(thread);
        runnables.get(0).setDone ();
        System.out.println("Thread #" + thread.threadId() +
                " performs factorization in the range of " + runnables.get(0).getFrom() + "->" + runnables.get(0).getTo());

        thread = new Thread(runnables.get(1));
        threads.add(thread);
        runnables.get(1).setDone ();
        System.out.println("Thread #" + thread.threadId() +
                " performs factorization in the range of " + runnables.get(1).getFrom() + "->" + runnables.get(1).getTo());

        threads.forEach( (t)->t.start() );
        threads.forEach( (t)->{	try{t.join();}
        catch(InterruptedException e){e.printStackTrace(); }} );

        LinkedList<Long> factors = new LinkedList<Long>();
        runnables.forEach( (factorizer) -> factors.addAll(factorizer.getPrimeFactors()) );
        System.out.println("Final result: " + factors + "\n");

        runnables.clear();
        threads.clear();

        // Factorization of 2489 with two threads
        System.out.println("Factorization of 2489");
        runnables.add( new RunnableCancellablePrimeFactorizer(2489, 2, (long)Math.sqrt(2489)/2 ));
        runnables.add( new RunnableCancellablePrimeFactorizer(2489, 1+(long)Math.sqrt(2489)/2, (long)Math.sqrt(2489) ));

        thread = new Thread(runnables.get(0));
        threads.add(thread);
        runnables.get(0).setDone ();
        System.out.println("Thread #" + thread.threadId() +
                " performs factorization in the range of " + runnables.get(0).getFrom() + "->" + runnables.get(0).getTo());

        thread = new Thread(runnables.get(1));
        threads.add(thread);
        runnables.get(1).setDone ();
        System.out.println("Thread #" + thread.threadId() +
                " performs factorization in the range of " + runnables.get(1).getFrom() + "->" + runnables.get(1).getTo());

        threads.forEach( (t)->t.start() );
        threads.forEach( (t)->{	try{t.join();}
        catch(InterruptedException e){e.printStackTrace(); }} );

        LinkedList<Long> factors2 = new LinkedList<Long>();
        runnables.forEach( (factorizer) -> factors2.addAll(factorizer.getPrimeFactors()) );
        System.out.println("Final result: " + factors2);
    }
}

