package Controler;

import java.util.ArrayList;

abstract public class Observables{
    private ArrayList<Observers> observers;
    public Observables() {
        this.observers = new ArrayList<Observers>();
    }
    public void addObserver(Observers o) {
        observers.add(o);
    }
    public void notifyObservers() {
        for(Observers o : observers) {
            o.update();
        }
    }
}
