package bookingstart;

//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;

public abstract class HotelRoom implements Room {

//	private LocalDate arrivalDate;
//	private LocalDate leavingDate;
    //private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	protected String room;
	
	abstract public double getPrice();
	abstract public String getDescription();
/*
	HotelRoom(LocalDate arrival, LocalDate leaving, String room) {
		arrivalDate = arrival;
		leavingDate = leaving;
		this.room = room;	
	}
*/	
/*	
	@Override
	public String getRoom() {
		return room;
	}
	
	
	@Override
	public LocalDate getArrivalDate() {
		return arrivalDate;
	}
	
	@Override
	public LocalDate getLeavingDate() {
		return leavingDate;
	}
*/	
}
