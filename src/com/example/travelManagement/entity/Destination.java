/**Each destination has a name, and a list of the activities available at that destination. */
package com.example.travelManagement.entity;
import com.example.travelManagement.travelManagement.ActivityDestinationManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class Destination {
    private String name;
    private List<Activity> activities;

    public Destination(String name) {
        this.name = name;
        this.activities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    boolean isActivityAddableToDestination(Activity activity, Destination destination)
    {
        ActivityDestinationManager instance=ActivityDestinationManager.getInstance();
        if(instance.getDestination(activity)!=null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public void addActivity(Activity activity) {

        if(isActivityAddableToDestination(activity, this)) {
            ActivityDestinationManager instance=ActivityDestinationManager.getInstance();
            activities.add(activity);
            instance.addActivity(activity, this);
        }
    }
    public void removeActivity(Activity activity)
    {
        if(activity.getCurrentEnrollment()==0)
        {
            for(Activity itr:this.getActivities())
            {
                if(itr==activity)
                    this.activities.remove(itr);
            }
        }
        else {
            System.out.println("Activity cannot be removed with non zero enrollment");
        }
    }
}
