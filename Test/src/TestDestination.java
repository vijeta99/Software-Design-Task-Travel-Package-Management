import com.example.travelManagement.entity.Activity;
import com.example.travelManagement.entity.Destination;
import com.example.travelManagement.entity.Passenger;
import com.example.travelManagement.travelManagement.ActivityDestinationManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestDestination {

    private Destination destination;
    private Activity activity1;
    private Activity activity2;

    @BeforeEach
    void setUp() {
        destination = new Destination("Paris");
        activity1 = new Activity("Eiffel Tower Tour", "A tour of the Eiffel Tower", 50.0, 20);
        activity2 = new Activity("Louvre Museum Visit", "A visit to the Louvre Museum", 30.0, 15);
    }

    @Test
    void testAddActivity() {
        assertTrue(destination.getActivities().isEmpty(), "Activities list should be empty initially.");
        destination.addActivity(activity1);
        assertEquals(1, destination.getActivities().size(), "Activities list should contain one activity after adding.");
        assertTrue(destination.getActivities().contains(activity1), "Activity1 should be present in the activities list.");
    }

    @Test
    void testRemoveActivityWithZeroEnrollment() {
        destination.addActivity(activity1);
        System.out.println(destination.getActivities());
        destination.removeActivity(activity1);
        System.out.println(destination.getActivities());
       assertTrue(destination.getActivities().isEmpty(), "Activities list should be empty after removing the activity with zero enrollment.");
    }

    @Test
    void testRemoveActivityWithNonZeroEnrollment() {
        destination.addActivity(activity2);
        assertEquals(1, destination.getActivities().size(), "Activities list should contain one activity after adding.");
        // Simulate enrolling a passenger
        activity2.signUp(new Passenger("John Doe",1)); // Assuming Passenger class exists and signUp method increases enrollment
        destination.removeActivity(activity2);
        assertEquals(1, destination.getActivities().size(), "Activities list should still contain one activity after attempting to remove an activity with non-zero enrollment.");
        assertTrue(destination.getActivities().contains(activity2), "Activity2 should still be present in the activities list after attempting to remove it with non-zero enrollment.");
    }
}