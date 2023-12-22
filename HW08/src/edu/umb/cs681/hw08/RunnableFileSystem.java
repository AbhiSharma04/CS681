package edu.umb.cs681.hw08;

public class RunnableFileSystem implements Runnable{


        @Override
        public void run() {
            FileSystem fs = FileSystem.getFileSystem();
            System.out.println("Thread " + Thread.currentThread().getName ()+ ": " + fs);
        }

        public static void main(String[] args) {

            RunnableFileSystem fs1= new RunnableFileSystem ();
            RunnableFileSystem fs2= new RunnableFileSystem ();
            RunnableFileSystem fs3= new RunnableFileSystem ();
            Thread thread1= new Thread (fs1,"a");
            Thread thread2= new Thread (fs1,"b");
            Thread thread3= new Thread (fs1,"c");



            thread1.start();
            thread2.start();
            thread3.start();
        }

}
