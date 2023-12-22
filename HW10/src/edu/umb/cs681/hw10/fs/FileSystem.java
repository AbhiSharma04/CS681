package edu.umb.cs681.hw10.fs;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem {

    private Directory rootDirectory;
    private static ReentrantLock lock = new ReentrantLock ();
    private static AtomicReference<FileSystem> instance = new AtomicReference<> ();

    private FileSystem(){
    }
    public static FileSystem getFileSystem(){

            if ( instance.get () == null ) {
                instance.compareAndSet(null, new FileSystem());
            }
            return instance.get ();

        }


    public Directory getRootDirs(){

        lock.lock ();
        try {
            return rootDirectory;
        } finally {
            lock.unlock ();
        }

    }
    public void appendRootDir(Directory root) {
        lock.lock ();
        try{
        this.rootDirectory = root;}
        finally {
            lock.unlock ();
        }
    }


}