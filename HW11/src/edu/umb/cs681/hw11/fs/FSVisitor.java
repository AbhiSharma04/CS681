package edu.umb.cs681.hw11.fs;

public interface FSVisitor {
    void visit(Link  link);
    void visit(File file);
    void visit(Directory directory);
}
