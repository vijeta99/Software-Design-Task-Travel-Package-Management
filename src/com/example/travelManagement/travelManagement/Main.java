package com.example.travelManagement.travelManagement;
import com.example.travelManagement.entity.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Main {
    static HashMap<Activity, Destination>activityDestinationMap;
    public static void main(String[] args)throws IOException{
        //Activities
        Activity activity1=new Activity("Sightseeing", "Sight seeing in a Hatchback", 1000, 10);
        Activity activity2=new Activity("Trekking","2km long trek",800,5);
        Activity activity3=new Activity("River rafting","10Km rafting",1200,10);
        Activity activity4=new Activity("Bunjee Jumping","83m bungee jumping",1500,5);
        Activity activity5=new Activity("Scuba Diving","40m scuba diving in Goa",800,5);
        Activity activity6=new Activity("Paragliding","Paragliding in Goa",2000,1);

        //Destinations
        Destination destination1=new Destination("Manali");
        Destination destination2=new Destination("Rishikesh");
        Destination destination3=new Destination("Goa");

        //Add activities to destination
        destination1.addActivity(activity2);
        destination1.addActivity(activity2);
        destination2.addActivity(activity3);
        destination2.addActivity(activity4);
        destination3.addActivity(activity5);
        destination3.addActivity(activity6);

        TravelPackage travelPackage1=new TravelPackage("Manali Package",10);
        TravelPackage travelPackage2=new TravelPackage("Rishikesh Adventures",20);
        TravelPackage travelPackage3=new TravelPackage("Goa Package",7);

        travelPackage1.addDestination(destination1);
        travelPackage2.addDestination(destination2);
        travelPackage3.addDestination(destination3);

        Passenger passenger1=new StandardPassenger("John",1,1500);
        Passenger passenger2=new GoldPassenger("Blake",2,1000,10);
        Passenger passenger3=new PremiumPassenger("Guy",3);

        //subscribe to travel packages

        travelPackage1.addPassenger(passenger1);
        travelPackage2.addPassenger(passenger2);
        travelPackage3.addPassenger(passenger3);

        passenger1.signUpForActivity(activity1, destination1);
        passenger1.signUpForActivity(activity2,destination1);

        passenger2.signUpForActivity(activity3, destination2);
        passenger2.signUpForActivity(activity4,destination2);

        passenger3.signUpForActivity(activity5, destination3);
        passenger3.signUpForActivity(activity6,destination3);

        System.out.println("Itinerary for: "+travelPackage1.getName());
        travelPackage1.printItinerary();
        System.out.println("----------------------------------------------------");
        System.out.println("Passengers' list for package "+travelPackage1.getName());
        travelPackage1.printPassengerList();
        System.out.println("----------------------------------------------------");
        System.out.println("Passengers' details:");
        passenger1.printDetails();







    }


}
