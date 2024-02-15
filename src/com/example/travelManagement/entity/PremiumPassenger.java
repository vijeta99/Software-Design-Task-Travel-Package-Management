/** A premium passenger can sign up for activities for free.
 */
package com.example.travelManagement.entity;

public class PremiumPassenger extends Passenger{
    public PremiumPassenger(String name, int passengerNumber)
    {
        super(name,passengerNumber);
    }
    @Override
    public boolean signUpForActivity(Activity activity, Destination destination) {
        if(!activity.isFull()) {
            return super.signUpForActivity(activity, destination);
        }
        else {
            System.out.println("Activity is full. Premium passenger could not be signed up!");
        }
        return false;
    }
}
