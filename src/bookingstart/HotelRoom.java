package bookingstart;


public abstract class HotelRoom implements Room {

	protected String room;
	
	abstract public double getPrice();
	abstract public String getDescription();
	

}
