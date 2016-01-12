package bookingstart;

public class Allinclusive extends RoomDecorator {
	Allinclusive(Room typeRoom) {
		super(typeRoom);
		extraPrice = 30.0;
	}
	private String Description = "all-inclusive";
	
	public double getExtraPrice() {
		return extraPrice;
	}
	
	public String getExtraDescription() {
		return Description + "\n";
	}
}
