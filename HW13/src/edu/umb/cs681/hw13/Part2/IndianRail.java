package edu.umb.cs681.hw13.Part2;

import java.util.concurrent.locks.ReentrantLock;

public class IndianRail {

    private String name;
    private int seats;
    private final ReentrantLock lock = new ReentrantLock();

    public IndianRail(String name, int seats) {
        this.name = name;
        this.seats = seats;
    }

    public String getName() {
        lock.lock();
        try {
            return name;
        } finally {
            lock.unlock();
        }
    }

    public int getSeats() {
        lock.lock();
        try {
            return seats;
        } finally {
            lock.unlock();
        }
    }

    public void setSeats(int seats) {
        lock.lock();
        try {
            this.seats = seats;
        } finally {
            lock.unlock();
        }
    }
}
