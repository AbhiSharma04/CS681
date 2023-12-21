package edu.umb.cs681.hw08;

public class RunnableFileSystem implements Runnable{


        @Override
        public void run() {
            FileSystem fs = FileSystem.getFileSystem();
            System.out.println("Thread " + Thread.currentThread().getName ()+ ": " + fs);
        }

        public static void main(String[] args) {
            Thread thread1 = new Thread(new RunnableFileSystem(),"a");
            Thread thread2 = new Thread(new RunnableFileSystem(),"b");
            Thread thread3 = new Thread(new RunnableFileSystem(),"c");

            thread1.start();
            thread2.start();
            thread3.start();
        }

}
