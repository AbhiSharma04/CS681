package edu.umb.cs681.hw09;
import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer {
    public RunnableCancellableInterruptiblePrimeFactorizer (long dividend, long from, long to) {
        super (dividend, from, to);
    }

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

            try {
                Thread.sleep(1000);
            }catch(InterruptedException e) {
                System.out.println(e);
                continue;
            }
        }
    }


    public static void main(String[] args) {
        RunnableCancellableInterruptiblePrimeFactorizer gen =
                new RunnableCancellableInterruptiblePrimeFactorizer(11,2,100);
        Thread thread = new Thread(gen);
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gen.setDone();
        thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n" + "Final result: " + gen.getPrimeFactors() + "\n");
    }

}
