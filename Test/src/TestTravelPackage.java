import com.example.travelManagement.entity.Destination;
import com.example.travelManagement.entity.Passenger;
import com.example.travelManagement.entity.TravelPackage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TravelPackageTest {

    private TravelPackage travelPackage;
    private Destination destinationParis;
    private Destination destinationRome;
    private Passenger passengerJohn;
    private Passenger passengerJane;

    @BeforeEach
    void setUp() {
        // Initialize a travel package with a capacity of 2 passengers.
        travelPackage = new TravelPackage("European Tour", 2);

        // Create destinations
        destinationParis = new Destination("Paris");
        destinationRome = new Destination("Rome");

        // Create passengers
        passengerJohn = new Passenger("John Doe", 1);
        passengerJane = new Passenger("Jane Smith", 2);
    }

    @Test
    void testAddDestination() {
        assertTrue(travelPackage.getItinerary().isEmpty(), "Itinerary should be empty initially.");
        travelPackage.addDestination(destinationParis);
        assertEquals(1, travelPackage.getItinerary().size(), "Itinerary should contain one destination after adding.");
        assertTrue(travelPackage.getItinerary().contains(destinationParis), "Destination Paris should be present in the itinerary.");
    }

    @Test
    void testRemoveDestinationWithNoPassengers() {
        travelPackage.addDestination(destinationParis);
        assertFalse(travelPackage.getItinerary().isEmpty(), "Itinerary should not be empty after adding a destination.");
        travelPackage.removeDestination(destinationParis);
        assertTrue(travelPackage.getItinerary().isEmpty(), "Itinerary should be empty after removing the destination.");
    }

    @Test
    void testRemoveDestinationWithPassengers() {
        travelPackage.addDestination(destinationParis);
        travelPackage.addPassenger(passengerJohn);
        assertEquals(1, travelPackage.getPassengers().size(), "Should have one passenger enrolled.");
        travelPackage.removeDestination(destinationParis);
        assertEquals(1, travelPackage.getItinerary().size(), "Itinerary should still contain one destination after attempting to remove when passengers are enrolled.");
    }

    @Test
    void testAddPassengerWithinCapacity() {
        assertTrue(travelPackage.getPassengers().isEmpty(), "Passengers list should be empty initially.");
        travelPackage.addPassenger(passengerJohn);
        assertEquals(1, travelPackage.getPassengers().size(), "Passengers list should contain one passenger after adding.");
        assertTrue(travelPackage.getPassengers().contains(passengerJohn), "Passenger John should be present in the passengers list.");
    }

    @Test
    void testAddPassengerExceedingCapacity() {
        travelPackage.addPassenger(passengerJohn);
        travelPackage.addPassenger(passengerJane);
        assertEquals(2, travelPackage.getPassengers().size(), "Passengers list should contain two passengers after adding.");
        Passenger passengerAlex = new Passenger("Alex Brown", 3);
        travelPackage.addPassenger(passengerAlex);
        assertEquals(2, travelPackage.getPassengers().size(), "Passengers list should not exceed the capacity.");
        assertFalse(travelPackage.getPassengers().contains(passengerAlex), "Passenger Alex should not be added as the capacity is full.");
    }

    @Test
    void testAddPassengerAlreadyEnrolled() {
        travelPackage.addPassenger(passengerJohn);
        assertEquals(1, travelPackage.getPassengers().size(), "Passengers list should contain one passenger after adding.");
        travelPackage.addPassenger(passengerJohn);
        assertEquals(1, travelPackage.getPassengers().size(), "Passengers list should still contain only one passenger after attempting to add the same passenger again.");
    }
}