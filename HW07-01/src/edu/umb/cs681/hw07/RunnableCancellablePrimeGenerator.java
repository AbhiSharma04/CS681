package edu.umb.cs681.hw07;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeGenerator extends RunnablePrimeGenerator{

    private ReentrantLock lock = new ReentrantLock ();
    private boolean done = false;

    public RunnableCancellablePrimeGenerator(long from, long to) {
        super(from, to);
    }

    public void setDone(){
        lock.lock();
        try{
            done = true;
        }finally{
            lock.unlock(); }
    }

    public void generatePrimes(){
        for(long n = from; n <= to; n++){
            lock.lock();
            try{
                if(done) {
                    System.out.println ("set done");
                    this.primes.clear ();
                    break;
                }
                if(isPrime(n)){this.primes.add(n);}
            }finally{
                lock.unlock(); }
        }
    }

    public static void main (String[] args) {
        RunnableCancellablePrimeGenerator gen = new RunnableCancellablePrimeGenerator(1,100);
        Thread thread = new Thread(gen);
        thread.start();
        gen.setDone();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gen.GetPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
        System.out.println("\n" + gen.GetPrimes().size() + " prime numbers are found.");
    }

}


