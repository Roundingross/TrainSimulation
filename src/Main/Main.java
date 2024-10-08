package Main;

/**
 * Main class to set starting attributes for the simulation and to run.
 * @author Sam Cain
 */

public class Main {
	public static void main(String[] args) {
		// Set start attributes for simulation
		String[] stationNames = {"Station A", "Station B", "Station C", "Station D", "Station E"};
		int[] travelTimes = {1, 1, 1, 1, 1};
		int trainInterval = 10;
		int initialPassengers = 15;
		
		// Start simulation
		TrainRoute route = new TrainRoute(stationNames, travelTimes, trainInterval, initialPassengers);
		route.startSimulation(60, 100);
	}
}
