package edu.umb.cs681.hw10.fs;

import java.time.LocalDateTime;

public class Link extends FSElement {

    private FSElement target;

    public Link(Directory ParentDir, String name, int size, LocalDateTime creationTime, FSElement target){
        super(ParentDir,name,size,creationTime);
        this.target=target;

    }
    public boolean isLink() {
        return true;
    }

    public FSElement getTarget(){
        lock.lock ();
        try{
        return this.target;}
        finally {
            lock.unlock ();
        }
    }

    @Override
    boolean isDirectory() {
        return false;
    }

    public boolean isFile(){
        return false;
    }
}