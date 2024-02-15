/** A gold passenger has a balance.
 * Each time a gold passenger signs up for an activity, a 10% discount is applied on the cost of the activity and the discounted amount is deducted from their balance.
 * They cannot sign up for an activity if they do not have sufficient balance. */
package com.example.travelManagement.entity;
import java.util.List;

public class GoldPassenger extends Passenger{
    private double balance;
    private double discount;
    public GoldPassenger(String name, int passengerNumber, double balance,double discount)
    {
        super(name, passengerNumber);
        this.balance=balance;
        this.discount=discount;
    }

    public double getDiscount() {
        return discount;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public boolean signUpForActivity(Activity activity, Destination destination) {
        double reqBalance= activity.getCost()*(1-(discount/100));
        if(reqBalance<=balance)
        {
            if(super.signUpForActivity(activity, destination))
            {
                balance -= reqBalance;
                return true;
            }
        }
        else {
            System.out.println(this.getName()+" has Insuffiient balance");
        }
        return false;

    }
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
                        System.out.println("Activity:"+activity.getName()+" Destination:"+destination.getName()+" Price:"+(activity.getCost()*(1-this.discount/100)));
                    }
                }
            }
        }

    }
}
