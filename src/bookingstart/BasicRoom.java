package bookingstart;

//import java.time.LocalDate;


public class BasicRoom extends HotelRoom {
	private double price = 50.0;
	private String description = "BasicRoom";
/*	
	BasicRoom(LocalDate arrival, LocalDate leaving, String room) {
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
