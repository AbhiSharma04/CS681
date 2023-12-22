package edu.umb.cs681.hw10.fs;

import java.time.LocalDateTime;

public class File extends FSElement {

    public File(Directory ParentDir, String name, int size, LocalDateTime creationTime) {
        super(ParentDir, name, size, creationTime);
    }
    public String getName(){
        lock.lock ();
        try{
        return this.name;}
        finally {
            lock.unlock ();
        }
    }

    @Override
    public boolean isDirectory() {
    return false;
    }

    @Override
    boolean isLink() {
     return false;

    }

}