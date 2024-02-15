/**A standard passenger has a balance. And each time a standard passenger signs up for an activity the cost is deducted from their balance. They cannot sign up for an activity if they do not have sufficient balance.
 */
package com.example.travelManagement.entity;
import java.util.List;

public class StandardPassenger extends Passenger{
    private double balance;
    public StandardPassenger(String name, int passengerNumber, double balance)
    {
        super(name, passengerNumber);
        this.balance=balance;
    }

    @Override
    public boolean signUpForActivity(Activity activity,Destination destination) {

        if(balance >=activity.getCost()) {
            if(super.signUpForActivity(activity, destination)) {
                balance -= activity.getCost();
                return true;
            }
        }
        else
        {
            System.out.println(this.getName()+" has Insufficient Balance");
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Balance:"+this.balance);
       List<TravelPackage> travelPackages=super.getEnrolledTravelPackages(this);
        for(TravelPackage travelPackage:travelPackages)
        {
            List<Destination>destinations=travelPackage.getItinerary();
            for(Destination destination:destinations)
            {
                List<Activity>activities=destination.getActivities();
                for(Activity activity:activities)
                {
                    if(activity.getPassengers().contains(this))
                    {
                        System.out.println("Activity:"+activity.getName()+" Destination:"+destination.getName()+" Price:"+activity.getCost());
                    }
                }
            }
        }

    }
}
