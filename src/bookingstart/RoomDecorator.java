package bookingstart;

public abstract class RoomDecorator implements Room {
	public Room typeRoom;
	double extraPrice;
	
	abstract double getExtraPrice();
	abstract String getExtraDescription();
	
	public RoomDecorator(Room typeRoom) {
		this.typeRoom = typeRoom;
	}
	
	@Override
	public double getPrice() {
		return typeRoom.getPrice() + extraPrice;
	}
	
	@Override
	public String getDescription() {
		return typeRoom.getDescription();
	}

}
