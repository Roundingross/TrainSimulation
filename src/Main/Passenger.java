package Main;

/**
 * Passenger class to hold passenger info.
 * @author Sam Cain
 */

public class Passenger {
	// Attributes
	String beginStation;
	String endStation;
	
	// Constructor
	public Passenger(String beginStation, String endStation) {
		this.beginStation = beginStation;
		this.endStation = endStation;
	}
	
	public String getBeginStation() {
		return beginStation;
	}
	
	public String getEndStation() {
		return endStation;
	}
}
