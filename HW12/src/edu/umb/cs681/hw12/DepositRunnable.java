package edu.umb.cs681.hw12;

import java.util.concurrent.atomic.AtomicBoolean;

public class DepositRunnable implements Runnable {

    private ThreadSafeBankAccount2 bankAccount;

    private AtomicBoolean done=new AtomicBoolean (false);
    public DepositRunnable (ThreadSafeBankAccount2 bankAccount) {

        this.bankAccount = bankAccount;

    }


        public void setDone(){
            done.set(true); }

        @Override
        public void run () {

            while(true){
                if(done.get ()){
                    System.out.println ("(d)Flag set to true process stopped");
                    break;}
                bankAccount.deposit(100);

                try {
                    Thread.sleep(1000);
                }catch(InterruptedException e) {
                    System.out.println("(d)Exception thrown due to interruption "+e.getMessage ());
                    continue;
                }}


        }




    }

