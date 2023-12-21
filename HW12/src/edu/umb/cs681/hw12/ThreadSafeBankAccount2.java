package edu.umb.cs681.hw12;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ThreadSafeBankAccount2 implements BankAccount {
    private double balance = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition sufficientFundsCondition = lock.newCondition();
    private Condition belowUpperLimitFundsCondition = lock.newCondition();

    public void deposit(double amount){
        lock.lock();
        try{
            System.out.println("Lock obtained");
            System.out.println(Thread.currentThread().threadId() +
                    " (d): current balance: " + balance);
            while(balance >= 300){
                System.out.println(Thread.currentThread().threadId() +
                        " (d): await(): Balance exceeds the upper limit.");
                belowUpperLimitFundsCondition.await();
            }
            balance += amount;
            System.out.println(Thread.currentThread().threadId() +
                    " (d): new balance: " + balance);
            sufficientFundsCondition.signalAll();
        }
        catch (InterruptedException exception){
            exception.printStackTrace();
        }
        finally{
            lock.unlock();
            System.out.println("Lock released");
        }
    }

    public void withdraw(double amount){
        lock.lock();
        try{
            System.out.println("Lock obtained");
            System.out.println(Thread.currentThread().threadId() +
                    " (w): current balance: " + balance);
            while(balance <= 0){
                System.out.println(Thread.currentThread().threadId() +
                        " (w): await(): Insufficient funds");
                sufficientFundsCondition.await();
            }
            balance -= amount;
            System.out.println(Thread.currentThread().threadId() +
                    " (w): new balance: " + balance);
            belowUpperLimitFundsCondition.signalAll();
        }
        catch (InterruptedException exception){
            exception.printStackTrace();
        }
        finally{
            lock.unlock();
            System.out.println("Lock released");
        }
    }

    public double getBalance() { return this.balance; }

    public static void main(String[] args) {
        edu.umb.cs681.hw12.ThreadSafeBankAccount2 bankAccount = new edu.umb.cs681.hw12.ThreadSafeBankAccount2 ();
        for(int i = 0; i < 5; i++){


            DepositRunnable dr= new DepositRunnable (bankAccount);
            WithdrawRunnable wr= new WithdrawRunnable (bankAccount);

            Thread thread1= new Thread (dr);
            Thread thread2=new Thread (wr);
            try {
                Thread.sleep (3000);
            } catch (InterruptedException e) {
                System.out.println (e.getMessage ());
            }
            thread1.start ();
            thread2.start ();

            try {
                Thread.sleep (2000);
            } catch (InterruptedException e) {
                System.out.println (e.getMessage ());
            }
            dr.setDone ();
            wr.setDone ();

            thread1.interrupt ();
            thread2.interrupt ();
            try {
                thread1.join ();
            } catch (InterruptedException e) {
                System.out.println (e.getMessage ());
            }
            try {
                thread2.join ();
            } catch (InterruptedException e) {
                System.out.println (e.getMessage ());
            }


        }

    }
}
