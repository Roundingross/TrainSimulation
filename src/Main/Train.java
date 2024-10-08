package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Train class to hold train info and functions to drop-off, board, and move train to each station.
 * @author Sam Cain
 */

public class Train {
	// Attributes
	int capacity;
	List<Passenger> passengersOnBoard;
	int currentStationIndex;
	
	// Constructor
	public Train(int capacity) {
		this.capacity = capacity;
		this.passengersOnBoard = new ArrayList<>();
		this.currentStationIndex = 0;
	}
	
	// Drop off passengers at each station
	public void dropOffPassengers(Station station) {
		int initialPassengerCount = passengersOnBoard.size();
		passengersOnBoard.removeIf(passenger -> passenger.endStation.equals(station.name));
		int passengersDroppedOff = initialPassengerCount - passengersOnBoard.size();
		System.out.println(passengersDroppedOff + " passengers got off at " + station.name);
		System.out.println("Passengers remaining on board: " + passengersOnBoard.size());
	}

	// Board passengers at each station
	public void boardPassengers(Station station) {
		int initialPassengerCount = passengersOnBoard.size();
		Queue<Passenger> stationQueue = station.getPassengerQueue();

		while (!stationQueue.isEmpty() && passengersOnBoard.size() < capacity) {
			Passenger passenger = stationQueue.poll();
			passengersOnBoard.add(passenger);
		}
		int passengersBoarded = passengersOnBoard.size() - initialPassengerCount;
		System.out.println(passengersBoarded + " passengers boarded at " + station.name);
		System.out.println("Total passengers on board: " + passengersOnBoard.size());
	}

	// Move train to next station
	public void moveToNextStation(TrainRoute route) {
		currentStationIndex = (currentStationIndex + 1) % route.stations.length;
		System.out.println("Train moved to " + route.stations[currentStationIndex].name);
	}
}
