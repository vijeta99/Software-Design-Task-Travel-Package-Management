/** This class is a singleton class which manages the activities and the destination to which an activity belongs. It stores the hashmap of activity and destination*/
package com.example.travelManagement.travelManagement;

import com.example.travelManagement.entity.Activity;

import com.example.travelManagement.entity.Destination;

import java.util.HashMap;
import java.util.Map;

public class ActivityDestinationManager {
    private static final ActivityDestinationManager INSTANCE=new ActivityDestinationManager();
    private Map<Activity, Destination> activityDestinationMap;
    private ActivityDestinationManager()
    {
        activityDestinationMap=new HashMap<>();
    }
    public static ActivityDestinationManager getInstance()
    {
        return INSTANCE;
    }
    public Destination getDestination(Activity activity)
    {
        return activityDestinationMap.get(activity);
    }

    public void removeActivityMap(Activity activity)
    {
        activityDestinationMap.remove(activity);
    }
    public void addActivity(Activity activity, Destination destination)
    {
        activityDestinationMap.put(activity, destination);
    }
    public void updateActivity(Activity activity, Destination newDestination)
    {
        activityDestinationMap.replace(activity,newDestination);
    }
}
