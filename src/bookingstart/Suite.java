package bookingstart;

//import java.time.LocalDate;

public class Suite extends HotelRoom {
	private double price = 80.0;
	private String description = "Suite";
/*	
	Suite(LocalDate arrival, LocalDate leaving, String room) {
		super(arrival, leaving, room);
	}
*/
	
	@Override
	public double getPrice() {
		return price;
	}
	@Override
	public String getDescription() {
		return description;
	}

}
