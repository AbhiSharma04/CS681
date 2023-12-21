package edu.umb.cs681.hw11.fs;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public class File extends FSElement {

    private ReentrantLock lock = new ReentrantLock();

    public File (Directory Parent, String name, int size, LocalDateTime creationTime) {
        super (Parent, name, size, creationTime);
    }

    public String getName () {
        lock.lock ();
        try {
            return this.name;
        } finally {
            lock.unlock ();
        }
    }

    public boolean isFile () {
        return true;
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

    @Override
    boolean isLink() {
        return false;

    }

}