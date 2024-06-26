package edu.umb.cs681.hw17;

import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class Observable<T> {

    private ConcurrentLinkedQueue<Observer<T>> observers = new ConcurrentLinkedQueue<>();

    public void addObserver(Observer<T> o) {
        observers.add(o);
    }

    public void clearObservers() {
        observers.clear();
    }

    public int countObservers() {
        return observers.size();
    }

    public void removeObserver(Observer<T> o) {
        observers.remove(o);
    }

    public void notifyObservers(T event) {
        observers.forEach(observer -> observer.update(this, event));
    }
}
