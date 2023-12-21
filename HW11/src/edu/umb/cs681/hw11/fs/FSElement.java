package edu.umb.cs681.hw11.fs;

import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

abstract class FSElement {
    protected String name ;
    protected Directory ParentDir;
    protected int size;
    protected LocalDateTime creationTime;
    protected ReentrantLock lock = new ReentrantLock ();

    public FSElement(Directory ParentDir, String name , int size, LocalDateTime creationTime){
        this.ParentDir= ParentDir;
        this.name= name ;
        this.size= size;
        this.creationTime=creationTime;
    }
    public Directory getParent(){
        lock.lock ();
        try {
            return this.ParentDir;
        }
        finally {
            lock.unlock ();
        }

    }
    public void setParent(Directory Parent) {
        lock.lock ();
        try {
            this.ParentDir = Parent;
        }
        finally {
            lock.unlock ();
        }

    }
    public int getSize(){
        lock.lock ();
        try {
            return this.size;
        }
        finally {
            lock.unlock ();
        }
    }

    public String getName(){
        lock.lock ();
        try {
            return this.name;
        }
        finally {
            lock.unlock ();
        }
    }
    abstract boolean isDirectory();

    abstract boolean isLink();
    void accept(FSVisitor v){

    }
}