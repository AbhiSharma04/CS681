package edu.umb.cs681.hw16.fs.util;

import edu.umb.cs681.hw16.fs.Link;
import edu.umb.cs681.hw16.fs.Directory;
import edu.umb.cs681.hw16.fs.FSVisitor;
import edu.umb.cs681.hw16.fs.File;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

public class FileCrawlingVisitor implements FSVisitor {

    private ReentrantLock lock = new ReentrantLock ();

    ConcurrentLinkedQueue<File> fileLinkedList= new ConcurrentLinkedQueue<> ();
    @Override
    public void visit (Link link) {
        return;
    }

    @Override
    public void visit (File file) {
        lock.lock ();
        try{
        fileLinkedList.add(file);}
        finally {
            lock.unlock ();
        }
    }

    @Override
    public void visit (Directory directory) {
        return;
    }

    public ConcurrentLinkedQueue<File> getFilesFromCrawling(){
        lock.lock ();
        try{
        return fileLinkedList;}
        finally {
            lock.unlock ();
        }
    }
}
