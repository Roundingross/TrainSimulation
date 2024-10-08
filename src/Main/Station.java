package Main;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Station class to hold station info.
 * @author Sam Cain
 */

public class Station {
	// Attributes
	String name;
	Queue<Passenger> passengerQueue;
	
	// Constructor
	public Station(String name) {
		this.name = name;
		this.passengerQueue = new LinkedList<>();
	}
	
	public void addPassengerToQueue(Passenger passenger) {
		passengerQueue.add(passenger);
	}
	
	public Queue<Passenger> getPassengerQueue() {
		return passengerQueue;
	}
}
