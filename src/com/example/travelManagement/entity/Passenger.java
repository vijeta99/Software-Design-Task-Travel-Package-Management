/** This is a base class for the passengers. A passenger can be a standard, gold or premium passenger. */
package com.example.travelManagement.entity;

import com.example.travelManagement.travelManagement.PassengerPackageManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Passenger {
    private String name;
    private int passengerNumber;
    private Map<Destination, List<Activity>> signedUpActivities;

    public Passenger(String name, int passengerNumber) {
        this.name = name;
        this.passengerNumber = passengerNumber;
        this.signedUpActivities = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public int getPassengerNumber() {
        return passengerNumber;
    }

    public boolean signUpForActivity(Activity activity, Destination destination) {
        if(this.signedUpActivities.containsValue(activity)) {
           System.out.println("Passenger already signed up for this activity");
           return false;
        }
        else {
            if (activity.signUp(this)) {

                if (destination != null) {
                    signedUpActivities.computeIfAbsent(destination, k -> new ArrayList<>()).add(activity);
                    return true;
                }
            }
        }
        return false;
    }
    public List<TravelPackage> getEnrolledTravelPackages(Passenger passenger)
    {
        PassengerPackageManager passengerPackageManager=PassengerPackageManager.getInstance();
       return passengerPackageManager.getTravelPackages(this.getPassengerNumber());
    }

    public void printDetails() {
        System.out.println("Name: " + name);
        System.out.println("Passenger Number: " + passengerNumber);
        }

}
