package edu.umb.cs681.hw12;

import java.util.concurrent.atomic.AtomicBoolean;

public class WithdrawRunnable implements Runnable {


    private ThreadSafeBankAccount2 bankAccount;

    private AtomicBoolean done=new AtomicBoolean (false);
    public WithdrawRunnable (ThreadSafeBankAccount2 bankAccount) {
        this.bankAccount = bankAccount;
    }



    public void setDone(){
        done.set(true); }

    @Override
    public void run () {

        while(true){
            if(done.get ()){
                System.out.println ("(w)Flag set to true process stopped ");
                break;}
            bankAccount.withdraw(100);

        try {
            Thread.sleep(1000);
        }catch(InterruptedException e) {
            System.out.println("(w)Exception thrown due to interruption "+e.getMessage ());
            continue;
        }}


    }


    }
