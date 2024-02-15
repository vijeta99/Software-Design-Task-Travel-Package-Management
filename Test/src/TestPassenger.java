import com.example.travelManagement.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static jdk.jfr.internal.jfc.model.Constraint.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PassengerTest {

    private Activity activity;
    private Destination destination;
    private StandardPassenger standardPassenger;
    private GoldPassenger goldPassenger;
    private PremiumPassenger premiumPassenger;

    @BeforeEach
    void setUp() {
        activity = Mockito.mock(Activity.class);
        destination = Mockito.mock(Destination.class);
        when(activity.getCost()).thenReturn(100.0);
        when(activity.signUp(any(Passenger.class))).thenReturn(true);

        standardPassenger = new StandardPassenger("Alice", 1, 200.0);
        goldPassenger = new GoldPassenger("Bob", 2, 200.0, 10.0);
        premiumPassenger = new PremiumPassenger("Charlie", 3);
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
        when(activity.isFull()).thenReturn(true);
        assertFalse(premiumPassenger.signUpForActivity(activity, destination),
                "Premium passenger should not be able to sign up for a full activity");
    }
}