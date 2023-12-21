package edu.umb.cs681.hw08;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem {

    private static FileSystem instance = null;
    private Directory rootDirectory;
    private static ReentrantLock lock = new ReentrantLock ();

    private FileSystem(){
    }
    public static FileSystem getFileSystem(){
        lock.lock ();
        try {
            if ( instance == null ) {
                instance = new FileSystem ();
            }
            return instance;

        }
        finally {
            lock.unlock ();
        }

    }

    public Directory getRootDirs(){
        return rootDirectory;
    }
    public void appendRootDir(Directory root) {
        this.rootDirectory = root;
    }

}