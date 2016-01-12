package bookingstart;

public class Booking {
	public static void main(String args[]) {
		Room basicRoom = new BasicRoom();
		basicRoom = new Slaapbank(basicRoom);
		System.out.println("Price of " + basicRoom.getDescription() + " = " + basicRoom.getPrice());
		Room decoratedRoom = new Slaapbank(new Tv(new Suite()));
		System.out.println("Price of " + decoratedRoom.getDescription() + " = " + decoratedRoom.getPrice());
	}
}
