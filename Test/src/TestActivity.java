import com.example.travelManagement.entity.Activity;
import com.example.travelManagement.entity.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestActivity {

    private Activity activity;
    private Passenger passenger;

    @BeforeEach
    void setUp() {
        // Assuming an activity with name "Hiking", description "Mountain hiking", cost 50.0, and capacity of 5
        activity = new Activity("Hiking", "Mountain hiking", 50.0, 5);
        passenger = new Passenger("John",1); // Assuming Passenger is a valid class
    }

    @Test
    void testActivityProperties() {
        assertEquals("Hiking", activity.getName(), "Activity name should match");
        assertEquals("Mountain hiking", activity.getDescription(), "Activity description should match");
        assertEquals(50.0, activity.getCost(), "Activity cost should match");
        assertEquals(5, activity.getCapacity(), "Activity capacity should match");
    }

    @Test
    void testSignUpPassengerWhenNotFull() {
        boolean signedUp = activity.signUp(passenger);
        assertTrue(signedUp, "Passenger should be signed up successfully");
        assertEquals(1, activity.getCurrentEnrollment(), "Current enrollment should increase");
        assertTrue(activity.getPassengers().contains(passenger), "Passenger should be in the list of passengers");
    }

    @Test
    void testSignUpPassengerWhenFull() {
        // Fill the activity to its capacity
        for (int i = 0; i < activity.getCapacity(); i++) {
            activity.signUp(new Passenger("John",1)); // Assuming Passenger is a valid class
        }
        // Try to sign up another passenger
        boolean signedUp = activity.signUp(passenger);
        assertFalse(signedUp, "Passenger should not be signed up if the activity is full");
        assertEquals(activity.getCapacity(), activity.getCurrentEnrollment(), "Current enrollment should equal capacity");
        assertFalse(activity.getPassengers().contains(passenger), "Passenger should not be in the list of passengers");
    }

    @Test
    void testIsFull() {
        // Fill the activity to its capacity
        for (int i = 0; i < activity.getCapacity(); i++) {
            activity.signUp(new Passenger("John",1)); // Assuming Passenger is a valid class
        }
        assertTrue(activity.isFull(), "Activity should be full when capacity is reached");
    }

    @Test
    void testGetAvailableSlots() {
        // Sign up two passengers
        activity.signUp(new Passenger("John",1)); // Assuming Passenger is a valid class
        activity.signUp(new Passenger("Mary",2)); // Assuming Passenger is a valid class
        assertEquals(3, activity.getAvailableSlots(), "Available slots should be capacity minus current enrollment");
    }
}