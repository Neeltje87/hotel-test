package bookingstart;

public class Tv extends RoomDecorator {
	Tv(Room typeRoom) {
		super(typeRoom);
		extraPrice = 10.0;
	}
	
	private String Description = "Tv";
	
	public double getExtraPrice() {
		return extraPrice;
	}
	
	public String getExtraDescription() {
		return Description + "\n";
	}
}
