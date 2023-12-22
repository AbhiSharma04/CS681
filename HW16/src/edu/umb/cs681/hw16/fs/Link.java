package edu.umb.cs681.hw16.fs;


import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public class Link extends FSElement {

    private ReentrantLock lock = new ReentrantLock();

    protected FSElement target;

    public Link (Directory ParentDir, String name, int size, LocalDateTime creationTime, FSElement target) {
        super (ParentDir, name, size, creationTime);
        this.target = target;

    }

    @Override
    public boolean isDirectory () {
        return false;
    }

    @Override
    public void accept (FSVisitor v) {
        lock.lock();
        try {
            super.accept(v);
            v.visit(this);
        } finally {
            lock.unlock();
        }
    }

    public boolean isLink () {
        return true;
    }

    public FSElement getTarget () {
        lock.lock ();
        try{
        return this.target;}
        finally{
            lock.unlock ();
        }
    }


    public boolean isFile () {
        return false;
    }
}
 