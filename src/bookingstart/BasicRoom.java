package bookingstart;



public class BasicRoom extends HotelRoom {
	private double price = 50.0;
	private String description = "BasicRoom";
	
	@Override
	public double getPrice() {
		return price;
	}
	
	@Override
	public String getDescription() {
		return description;
	}

}
