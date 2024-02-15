/** Each activity has a name, a description, a cost and a capacity. Each activity is available at one destination only.
  Each passenger can sign up for zero or more activity at each destination of the travel package.
  Once an activity has reached it's capacity no more passengers can sign up for it.*/
package com.example.travelManagement.entity;

import java.util.ArrayList;
import java.util.List;

public class Activity {
    private String name;
    private String description;
    private double cost;
    private int capacity;
    private int currentEnrollment;
    private List<Passenger> passengers;

    public Activity(String name, String description, double cost, int capacity) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
        this.currentEnrollment = 0;
        this.passengers=new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean signUp(Passenger passenger) {
        if (!isFull()) {
            currentEnrollment++;
            this.passengers.add(passenger);
            return true;
        } else {
            System.out.println("Activity is full. Cannot sign up more passengers.");
            return false;
        }
    }

    public boolean isFull() {
        return currentEnrollment >= capacity;
    }

    public int getAvailableSlots() {
        return capacity - currentEnrollment;
    }
    public int getCurrentEnrollment()
    {
        return this.currentEnrollment;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }
}