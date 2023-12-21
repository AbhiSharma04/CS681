package edu.umb.cs681.hw16.fs;


import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicReference;

public class FileSystem {

    private LinkedList<Directory> rootDirectory;
    //private static ReentrantLock lock = new ReentrantLock ();
    private static AtomicReference<FileSystem> instance = new AtomicReference<> ();

    private FileSystem(){
        this.rootDirectory = new LinkedList<>();
    }
    public static FileSystem getFileSystem(){

            if ( instance.get () == null ) {
                instance.compareAndSet(null, new FileSystem ());
            }
            return instance.get ();


    }
    public LinkedList<Directory> getRootDirs(){
        return rootDirectory;
    }

    public void  appendRootDir(Directory root) {
        rootDirectory.add(root);
        root.setParent(null);
    }




}
