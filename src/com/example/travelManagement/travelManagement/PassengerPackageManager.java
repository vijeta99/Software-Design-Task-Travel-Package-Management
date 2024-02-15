/** This is a singleton class that which stres the hashmap of passengers and the list of travel packages passengers are enrolled in.*/
package com.example.travelManagement.travelManagement;

import com.example.travelManagement.entity.Passenger;
import com.example.travelManagement.entity.TravelPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PassengerPackageManager {
private static final PassengerPackageManager INSTANCE=new PassengerPackageManager();
private Map<Integer, List<TravelPackage>> passengerPackageMap;
private PassengerPackageManager()
{
    passengerPackageMap=new HashMap<>();
}
public static PassengerPackageManager getInstance()
{
    return INSTANCE;
}
public List<TravelPackage> getTravelPackages(int passengerNumber)
{
    return passengerPackageMap.get(passengerNumber);
}
public void removeTravelPackage(Passenger passenger, TravelPackage travelPackage)
{
    try
    {
       if(passengerPackageMap.get(passenger.getPassengerNumber()).remove(travelPackage))
       {
           System.out.println("Passenger removed");
       }
    }
    catch(Exception e)
    {
        System.out.println(e.getMessage());
    }
}
public void addTravelPackage(Passenger passenger, TravelPackage travelPackage)
{
    passengerPackageMap.computeIfAbsent(passenger.getPassengerNumber(), k -> new ArrayList<>());
    passengerPackageMap.get(passenger.getPassengerNumber()).add(travelPackage);

}
}
