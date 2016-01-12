package bookingstart;

public class Slaapbank extends RoomDecorator {
	
	Slaapbank(Room typeRoom) {
		super(typeRoom);
		extraPrice = 20.0;
	}
	
	private String Description = "Slaapbank";
	
	public double getExtraPrice() {
		return extraPrice;
	}
	
	public String getExtraDescription() {
		return  Description + "\n";
	}
	

}
