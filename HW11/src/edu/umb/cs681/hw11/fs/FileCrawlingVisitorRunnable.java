package edu.umb.cs681.hw11.fs;

import edu.umb.cs681.hw11.fs.util.FileCrawlingVisitor;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class FileCrawlingVisitorRunnable implements Runnable{

    private FSElement rootDirectory;
    private LinkedList<File> sharedList;
    private ReentrantLock lock = new ReentrantLock();
    private AtomicBoolean done = new AtomicBoolean(false);

    private ThreadLocal<FileCrawlingVisitor> threadLocalVisitor = ThreadLocal.withInitial(FileCrawlingVisitor::new);

    public FileCrawlingVisitorRunnable(FSElement rootDirectory, LinkedList<File> sharedList) {
        this.rootDirectory = rootDirectory;
        this.sharedList = sharedList;
    }

    public void setDone() {
        done.set(true);
    }
    @Override
    public void run () {
        rootDirectory.accept(threadLocalVisitor.get());
        System.out.println("Thread " + Thread.currentThread().threadId () + " is running");

        lock.lock();
        try {
            if (done.get()) {
                System.out.println("Flag set to true, process stopped");
                sharedList.addAll(threadLocalVisitor.get().getFilesFromCrawling());
            }
        } finally {
            lock.unlock();
        }

    }


    public static void main (String[] args) {

        FileSystem fs = TestFixtureInitializer.createFS();
        LinkedList<File> sharedList = new LinkedList<> ();

        LinkedList<FileCrawlingVisitorRunnable> filecrawlers = new LinkedList<>();
        LinkedList<Thread> threads = new LinkedList<>();


        for (Directory rootDir : fs.getRootDirs()) {
            FileCrawlingVisitorRunnable filecrawler = new FileCrawlingVisitorRunnable(rootDir, sharedList);
            Thread thread = new Thread(filecrawler);
            filecrawlers.add(filecrawler);
            threads.add(thread);
            thread.start();
        }


        for (FileCrawlingVisitorRunnable crawler : filecrawlers) {
            crawler.setDone();
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
                System.out.println("Exception thrown due to interruption "+e.getMessage ());
            }
        }

        System.out.println("Files found:");
        for (File file : sharedList) {
            System.out.println(file.getName());
        }

    }
}
