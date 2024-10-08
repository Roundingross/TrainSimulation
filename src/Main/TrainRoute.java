package Main;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * TrainRoute class to hold the info needed for the train's route, generate passengers at each station, and to start the simulation.
 * @author Sam Cain
 */

public class TrainRoute {
	// Attributes
	Station[] stations;
	int[] travelTimes;
	int trainInterval;
	Random random = new Random();
	
	// Constructor
	public TrainRoute(String[] stationNames, int[] travelTimes, int trainInterval, int initialPassengers) {
		this.stations = new Station[stationNames.length];
		for (int i = 0; i < stationNames.length; i++) {
			stations[i] = new Station(stationNames[i]);
		}
		this.travelTimes = travelTimes;
		this.trainInterval = trainInterval;
		
		// Generate Initial Passengers
		System.out.println("\n--- Initializing Passengers ---");
		for (int i = 0; i < initialPassengers; i++) {
			generateRandomPassengers();
		}
		System.out.println("--- Passenger Initialization Complete ---\n");
	}

	
	public void generateRandomPassengers() {
		// Generate between 20 to 35 passengers at once
		int numPassengers = random.nextInt(16) + 20;  // 20 to 35 passengers

		// A map to store how many passengers were added to each station
		Map<String, Integer> stationPassengerCounts = new HashMap<>();
    
		for (int i = 0; i < numPassengers; i++) {
			int entryIndex = random.nextInt(stations.length);
			int destinationIndex;
			do {
				destinationIndex = random.nextInt(stations.length);
			} while (entryIndex == destinationIndex);

			Passenger passenger = new Passenger(stations[entryIndex].name, stations[destinationIndex].name);
			stations[entryIndex].addPassengerToQueue(passenger);

			// Update the station's passenger count in the map
			stationPassengerCounts.put(stations[entryIndex].name, stationPassengerCounts.getOrDefault(stations[entryIndex].name, 0) + 1);
		}

		// Print a summary of passengers generated at each station
		System.out.println("\n--- Passenger Generation Summary ---");
		for (String station : stationPassengerCounts.keySet()) {
			System.out.println(stationPassengerCounts.get(station) + " passengers added at " + station);
		}
	}

	public void startSimulation(int simulationTime, int trainCapacity) {
		Train train = new Train(trainCapacity);
		int time = 0;
		while (time < simulationTime) {
			if (time % trainInterval == 0) {
				System.out.println("\n========================");
				System.out.println("Train departs at time " + time);
				System.out.println("========================");
				for (int i = 0; i < stations.length; i++) {
					// Current station
					Station currentStation = stations[train.currentStationIndex];
					System.out.println("\nTrain arrived at " + currentStation.name);
                
					// Drop off passengers
					train.dropOffPassengers(currentStation);

					// Board new passengers
					train.boardPassengers(currentStation);
					System.out.println("Train departs " + currentStation.name + " with " + train.passengersOnBoard.size() + " passengers on board.");
				
					// Move to the next station
					train.moveToNextStation(this);
                
					// Simulate travel time between stations
					try {
						Thread.sleep(travelTimes[train.currentStationIndex] * 500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			// Randomly generate passengers at some interval
			if (random.nextInt(5) == 0) {
				System.out.println("\n--- Passenger Generation ---");
				generateRandomPassengers();
			}
			// Increment simulation time
			time++;

			// Simulate 1 second per loop iteration
			try {
				Thread.sleep(1000);  
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// Summary of the simulation
		System.out.println("\n========================");
		System.out.println("Simulation ended after " + simulationTime + " seconds.");
		System.out.println("========================");
		System.out.println("Passengers remaining on train: " + train.passengersOnBoard.size());

		for (Station station : stations) {
			System.out.println("Station " + station.name + " has " + station.getPassengerQueue().size() + " passengers waiting.");
		}
	}
}
