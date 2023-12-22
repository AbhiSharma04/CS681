package edu.umb.cs681.hw10.fs;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement {
    Directory ParentDir;
    LinkedList<FSElement> FSELement_List = new LinkedList<FSElement>();


    public Directory(Directory ParentDir, String name, int size, LocalDateTime creationTime) {
        super(ParentDir, name, size, creationTime);
        this.ParentDir=ParentDir;
    }
    public String getName(){
        lock.lock ();
        try{
        return this.name;}
        finally {
            lock.unlock ();
        }
    }

    public LinkedList<FSElement> getChildren(){
        lock.lock ();
        try{
            return this.FSELement_List;}
        finally {
            lock.unlock ();
        }
    }
    LinkedList<Directory> Directory_List = new LinkedList<Directory>();
    LinkedList<File> File_List = new LinkedList<File>();
    LinkedList<Link> Link_list = new LinkedList<Link>();

    public void appendChild(FSElement child){

        lock.lock ();
        try {

            FSELement_List.add (child);
            if ( child instanceof Directory ) {
                Directory_List.add ((Directory) child);
            } else if ( child instanceof Link ) {
                Link_list.add ((Link) child);
            } else if ( child instanceof File ) {
                File_List.add ((File) child);
            }
        } finally {
            lock.unlock ();
        }

    }
    public int countChildren(){
        lock.lock ();
        try{
        return FSELement_List.size();}
        finally {
            lock.unlock ();
        }
    }
    public LinkedList<Directory> getSubDirectories(){
        lock.lock ();
        try{
        return this.Directory_List;}
        finally {
            lock.unlock ();
        }
    }
    public Directory getParent(){
        lock.lock ();
        try{
        return this.ParentDir;}
        finally {
            lock.unlock ();
        }
    }

    public LinkedList<File> getFiles(){
        lock.lock ();
        try{
        return this.File_List;}
        finally {
            lock.unlock ();
        }
    }

    public LinkedList<Link> getLinks(){
        lock.lock ();
        try{
        return this.Link_list;}
        finally {
            lock.unlock ();
        }
    }

    public  int getTotalSize(){

        lock.lock ();
        try{

        int  totalSize = 0;
        for (FSElement child : FSELement_List) {
            if (child instanceof File) {
                totalSize += ((File) child).getSize();

            } else if (child instanceof Directory) {
                totalSize += ((Directory) child).getTotalSize();
            }
        }
        return totalSize;}
        finally {
            lock.unlock ();
        }

    }
    @Override
    public boolean isDirectory() {
        return true;

    }

    @Override
    boolean isLink() {
        return false;
    }

}