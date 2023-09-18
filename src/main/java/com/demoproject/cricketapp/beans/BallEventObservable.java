package com.demoproject.cricketapp.beans;

public interface BallEventObservable {
    void addObserver(BallEventObserver observer);
    void removeObserver(BallEventObserver observer);
    void notifyObservers(BallEvent ballEvent);
}