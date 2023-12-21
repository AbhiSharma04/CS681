package edu.umb.cs681.hw08;

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
        return this.target;
    }

    @Override
    boolean isDirectory() {
        return false;
    }

    public boolean isFile(){
        return false;
    }
}