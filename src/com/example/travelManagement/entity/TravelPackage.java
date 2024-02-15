/** Each travel package has a name, a passenger capacity, an itinerary (list of destinations) and a list of it's passenger.*/
package com.example.travelManagement.entity;

import com.example.travelManagement.travelManagement.PassengerPackageManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TravelPackage class
public class TravelPackage {


    private final String name;
    private final int passengerCapacity;
    private List<Destination> itinerary;
    private List<Passenger> passengers;

    public TravelPackage(String name, int passengerCapacity) {
        this.name = name;
        this.passengerCapacity = passengerCapacity;
        this.itinerary = new ArrayList<>();
        this.passengers = new ArrayList<>();
    }
    public String getName() {
        return name;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public List<Destination> getItinerary() {
        return itinerary;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void addDestination(Destination destination) {
        itinerary.add(destination);
    }

    public void removeDestination(Destination destination)
    {
        if(this.passengers.isEmpty())
        {
            this.itinerary.removeIf(itr -> itr == destination);
        }
        else
        {
            System.out.println("Cannot remove itinerary as passengers have already enrolled for this package");
        }
    }
    public void addPassenger(Passenger passenger) {
        if (passengers.size() < passengerCapacity ) {
            PassengerPackageManager instance=PassengerPackageManager.getInstance();
            if(!this.passengers.contains(passenger))
            {
                passengers.add(passenger);
                instance.addTravelPackage(passenger,this);
            }
            else {
                System.out.println("Passenger is already enrolled in the package");
            }

        } else {
            System.out.println("Travel package is full. Cannot add more passengers.");
        }
    }

    public void printItinerary() {
        System.out.println("Travel Package: " + name);
        for (Destination destination : this.getItinerary()) {
            System.out.println("Destination: " + destination.getName());
            for (Activity activity : destination.getActivities()) {
                System.out.println("  Activity: " + activity.getName());
                System.out.println("    Description: " + activity.getDescription());
                System.out.println("    Cost: " + activity.getCost());
                System.out.println("    Capacity: " + activity.getCapacity());
            }
        }
    }

    public void printPassengerList() {
        System.out.println("Package Name: " + name);
        System.out.println("Passenger Capacity: " + passengerCapacity);
        System.out.println("Number of Passengers Enrolled: " + passengers.size());
        for (Passenger passenger : passengers) {
            System.out.println("  Passenger Name: " + passenger.getName());
            System.out.println("  Passenger Number: " + passenger.getPassengerNumber());
        }
    }

    public void printAvailableActivities() {
        for (Destination destination : this.getItinerary()) {
            for (Activity activity : destination.getActivities()) {
                if (!activity.isFull()) {
                    System.out.println("Activity: " + activity.getName() + " at " + destination.getName());
                    System.out.println("  Available Slots: " + activity.getAvailableSlots());
                }
            }
        }
    }
}
