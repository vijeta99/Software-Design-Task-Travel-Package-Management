import com.example.travelManagement.entity.Activity;
import com.example.travelManagement.entity.Destination;
import com.example.travelManagement.travelManagement.ActivityDestinationManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestDestination {

    private Destination destination;
    private Activity activity;
    private ActivityDestinationManager activityDestinationManager;

    @BeforeEach
    void setUp() {
        // Assuming a destination name "Paris"
        destination = new Destination("Paris");
        activity = mock(Activity.class);
        activityDestinationManager = mock(ActivityDestinationManager.class);
        when(ActivityDestinationManager.getInstance()).thenReturn(activityDestinationManager);
    }

    @Test
    void testAddActivityWhenAlreadyPresent() {
        when(activityDestinationManager.getDestination(activity)).thenReturn(destination);
        destination.addActivity(activity);
        List<Activity> activities = destination.getActivities();
        assertFalse(activities.contains(activity), "Activity should not be added if it's already associated with a destination");
    }

    @Test
    void testRemoveActivityWithZeroEnrollment() {
        when(activity.getCurrentEnrollment()).thenReturn(0);
        destination.getActivities().add(activity); // Directly adding for test purposes
        destination.removeActivity(activity);
        List<Activity> activities = destination.getActivities();
        assertFalse(activities.contains(activity), "Activity should be removed when current enrollment is zero");
    }

    @Test
    void testRemoveActivityWithNonZeroEnrollment() {
        when(activity.getCurrentEnrollment()).thenReturn(10);
        destination.getActivities().add(activity); // Directly adding for test purposes
        destination.removeActivity(activity);
        List<Activity> activities = destination.getActivities();
        assertTrue(activities.contains(activity), "Activity should not be removed when current enrollment is not zero");
    }
}