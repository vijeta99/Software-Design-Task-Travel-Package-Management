import com.example.travelManagement.entity.Destination;
import com.example.travelManagement.entity.Passenger;
import com.example.travelManagement.entity.TravelPackage;
import com.example.travelManagement.travelManagement.PassengerPackageManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestTravelPackage {

    private TravelPackage travelPackage;
    private Passenger passenger;
    private Destination destination;
    private PassengerPackageManager passengerPackageManager;

    @BeforeEach
    void setUp() {
        // Assuming a package name "Holiday" and a capacity of 10 passengers
        travelPackage = new TravelPackage("Holiday", 10,);
        passenger = mock(Passenger.class);
        destination = mock(Destination.class);
        passengerPackageManager = mock(PassengerPackageManager.class);
        when(PassengerPackageManager.getInstance()).thenReturn(passengerPackageManager);
    }

    @Test
    void testAddDestination() {
        travelPackage.addDestination(destination);
        List<Destination> itinerary = travelPackage.getItinerary();
        assertTrue(itinerary.contains(destination), "Destination should be added to the itinerary");
    }

    @Test
    void testRemoveDestinationWithoutPassengers() {
        travelPackage.addDestination(destination);
        travelPackage.removeDestination(destination);
        List<Destination> itinerary = travelPackage.getItinerary();
        assertFalse(itinerary.contains(destination), "Destination should be removed from the itinerary when no passengers are enrolled");
    }

    @Test
    void testRemoveDestinationWithPassengers() {
        travelPackage.addDestination(destination);
        travelPackage.addPassenger(passenger);
        travelPackage.removeDestination(destination);
        List<Destination> itinerary = travelPackage.getItinerary();
        assertTrue(itinerary.contains(destination), "Destination should not be removed from the itinerary when passengers are enrolled");
    }

    @Test
    void testAddPassengerWithinCapacity() {
        travelPackage.addPassenger(passenger);
        List<Passenger> passengers = travelPackage.getPassengers();
        assertTrue(passengers.contains(passenger), "Passenger should be added to the package");
        verify(passengerPackageManager).addTravelPackage(passenger, travelPackage);
    }

    @Test
    void testAddPassengerExceedingCapacity() {
        // Fill the travel package to its capacity
        for (int i = 0; i < travelPackage.getPassengerCapacity(); i++) {
            travelPackage.addPassenger(mock(Passenger.class));
        }
        // Try to add one more passenger
        travelPackage.addPassenger(passenger);
        List<Passenger> passengers = travelPackage.getPassengers();
        assertFalse(passengers.contains(passenger), "Passenger should not be added when capacity is full");
    }

    @Test
    void testAddDuplicatePassenger() {
        travelPackage.addPassenger(passenger);
        travelPackage.addPassenger(passenger);
        List<Passenger> passengers = travelPackage.getPassengers();
        assertEquals(1, passengers.size(), "Duplicate passenger should not be added");
    }
}