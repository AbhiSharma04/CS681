package edu.umb.cs681.hw11.fs.util;

import edu.umb.cs681.hw11.fs.Directory;
import edu.umb.cs681.hw11.fs.FSVisitor;
import edu.umb.cs681.hw11.fs.File;
import edu.umb.cs681.hw11.fs.Link;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class FileCrawlingVisitor implements FSVisitor {

    private ReentrantLock lock = new ReentrantLock ();

    LinkedList<File> fileLinkedList= new LinkedList<File>();
    @Override
    public void visit (Link link) {
        return;
    }

    @Override
    public void visit (File file) {
        lock.lock ();
        try {
            fileLinkedList.add (file);
        } finally {
            lock.unlock ();
        }
    }

    @Override
    public void visit (Directory directory) {
        return;
    }

    public LinkedList<File> getFilesFromCrawling(){
        lock.lock ();
        try{
        return fileLinkedList;}
        finally {
            lock.unlock ();
        }
    }
}
