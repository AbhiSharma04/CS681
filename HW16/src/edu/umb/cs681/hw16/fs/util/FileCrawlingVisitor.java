package edu.umb.cs681.hw16.fs.util;

import edu.umb.cs681.hw16.fs.Link;
import edu.umb.cs681.hw16.fs.Directory;
import edu.umb.cs681.hw16.fs.FSVisitor;
import edu.umb.cs681.hw16.fs.File;

import java.util.LinkedList;

public class FileCrawlingVisitor implements FSVisitor {

    LinkedList<File> fileLinkedList= new LinkedList<File>();
    @Override
    public void visit (Link link) {
        return;
    }

    @Override
    public void visit (File file) {
        fileLinkedList.add(file);
    }

    @Override
    public void visit (Directory directory) {
        return;
    }

    public LinkedList<File> getFilesFromCrawling(){
        return fileLinkedList;
    }
}
