package bookingstart;

public class Suite extends HotelRoom {
	private double price = 80.0;
	private String description = "Suite";

	@Override
	public double getPrice() {
		return price;
	}
	@Override
	public String getDescription() {
		return description;
	}

}
