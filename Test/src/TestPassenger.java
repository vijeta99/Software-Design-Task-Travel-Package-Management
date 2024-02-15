import com.example.travelManagement.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestPassenger {

    private Destination destination;
    private Activity activity;
    private Activity activity2;
    private StandardPassenger standardPassenger;
    private GoldPassenger goldPassenger;
    private PremiumPassenger premiumPassenger;

    @BeforeEach
    void setUp() {
        activity = new Activity("Eiffel Tower Tour", "A tour of the Eiffel Tower", 100.0, 20);
      destination= new Destination("Paris");
       destination.addActivity(activity);
       activity2 = new Activity("Eiffel Tower Lunch", "Lunch at the Eiffel Tower", 10.0, 1);
        destination.addActivity(activity2);
        standardPassenger = new StandardPassenger("Alice", 1, 200.0);
        goldPassenger = new GoldPassenger("Bob", 2, 200.0, 10.0);
        premiumPassenger = new PremiumPassenger("Charlie", 3);
        //make activity2 full of passengers
        activity2.signUp(standardPassenger);

    }

    @Test
    void testStandardPassengerSignUpForActivityWithSufficientBalance() {
        assertTrue(standardPassenger.signUpForActivity(activity, destination),
                "Standard passenger should be able to sign up if balance is sufficient");
        assertEquals(100.0, standardPassenger.getBalance(),
                "Balance should be deducted by the cost of the activity");
    }

    @Test
    void testStandardPassengerSignUpForActivityWithInsufficientBalance() {
        StandardPassenger poorPassenger = new StandardPassenger("Dan", 4, 50.0);
        assertFalse(poorPassenger.signUpForActivity(activity, destination),
                "Standard passenger should not be able to sign up if balance is insufficient");
        assertEquals(50.0, poorPassenger.getBalance(),
                "Balance should not change if sign up fails");
    }

    @Test
    void testGoldPassengerSignUpForActivityWithSufficientBalance() {
        assertTrue(goldPassenger.signUpForActivity(activity, destination),
                "Gold passenger should be able to sign up if balance is sufficient after discount");
        assertEquals(110.0, goldPassenger.getBalance(),
                "Balance should be deducted by the discounted cost of the activity");
    }

    @Test
    void testGoldPassengerSignUpForActivityWithInsufficientBalance() {
        GoldPassenger poorGoldPassenger = new GoldPassenger("Eve", 5, 50.0, 10.0);
        assertFalse(poorGoldPassenger.signUpForActivity(activity, destination),
                "Gold passenger should not be able to sign up if balance is insufficient even after discount");
        assertEquals(50.0, poorGoldPassenger.getBalance(),
                "Balance should not change if sign up fails");
    }

    @Test
    void testPremiumPassengerSignUpForActivity() {
        assertTrue(premiumPassenger.signUpForActivity(activity, destination),
                "Premium passenger should be able to sign up for free");
    }

    @Test
    void testPremiumPassengerSignUpForFullActivity() {
        assertFalse(premiumPassenger.signUpForActivity(activity2, destination),
                "Premium passenger should not be able to sign up for a full activity");
    }
    @Test
    void StandardPassengerSignUpForFullActivity() {
        StandardPassenger passenger = new StandardPassenger("John", 4, 50.0);
        assertFalse(passenger.signUpForActivity(activity2, destination),
                "Premium passenger should not be able to sign up for a full activity");
        assertEquals(50.0, passenger.getBalance(),
                "Balance should not change if sign up fails");
    }
    @Test
    void GoldPassengerSignUpForFullActivity() {
        GoldPassenger passenger = new GoldPassenger("Johnny", 4, 50.0,10);
        assertFalse(passenger.signUpForActivity(activity2, destination),
                "Premium passenger should not be able to sign up for a full activity");
        assertEquals(50.0, passenger.getBalance(),
                "Balance should not change if sign up fails");
    }
}