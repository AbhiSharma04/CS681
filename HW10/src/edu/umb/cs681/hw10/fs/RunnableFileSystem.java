package edu.umb.cs681.hw10.fs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class RunnableFileSystem implements Runnable{

    private final AtomicBoolean done = new AtomicBoolean (false);

    public void setDone(){
        done.set(true); }

    @Override
    public void run() {
        FileSystem fs = FileSystem.getFileSystem();
        System.out.println("Thread " + Thread.currentThread().getName ()+ ": " + fs);

        while (!done.get()) {
            fs= TestFixtureInitializer.createFS();
            System.out.println ("root directory is "+ fs.getRootDirs ().getName ());
            System.out.println ("root 1st child is "+ fs.getRootDirs ().getChildren ().getFirst ().getName ());
            System.out.println ("root 2nd child is "+ fs.getRootDirs ().getChildren ().get (1).getName ());
            System.out.println ("root 3rd child is "+ fs.getRootDirs ().getChildren ().get (2).getName ());


            System.out.println("Thread " + Thread.currentThread().threadId () + " is running");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        System.out.println("Thread " + Thread.currentThread().threadId () + " is terminating");
    }


        public static void main(String[] args) {

            List<RunnableFileSystem> FSAccess = new ArrayList<> ();
            List<Thread> threads = new ArrayList<>();

            for (int i = 0; i < 12; i++) {
                RunnableFileSystem runnableFileSystem = new RunnableFileSystem();
                Thread thread = new Thread(runnableFileSystem, "FS-Thread-" + i);
                FSAccess.add(runnableFileSystem);
                threads.add(thread);
                thread.start();
            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException (e);
            }


            for (RunnableFileSystem task : FSAccess) {
                task.setDone();
            }

            for (Thread thread : threads) {
                if (thread.isAlive()) {
                    thread.interrupt();
                    System.out.println("Thread " + thread.threadId () + " is interrupted");

                }
            }


            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    System.out.println (e.getMessage ());
                }
            }

            System.out.println("All threads have terminated");
        }





}

