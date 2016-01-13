package bookingstart;

import java.sql.Date;
import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

public class Book {
	
	// connectie met database
	   static final String JDBC_DRIVER =
	      "com.mysql.jdbc.Driver";
	   static final String DB_URL =
	      "jdbc:mysql://localhost/hotel_booking";
	   static final String DB_USER = "root";
	   static final String DB_PASS = "";
	   private JdbcRowSet rowSet = null;
	
	public Book(int roomNr, double price, Date checkIn, Date checkOut) {
		try {
	         Class.forName(JDBC_DRIVER);
	         rowSet = RowSetProvider.newFactory().createJdbcRowSet();
	         rowSet.setUrl(DB_URL);
	         rowSet.setUsername(DB_USER);
	         rowSet.setPassword(DB_PASS);
	         rowSet.setCommand("SELECT * FROM reservering");
	         rowSet.execute();
	         
	         // upload booking to database
	         rowSet.moveToInsertRow();
	         rowSet.updateInt("RoomNr", roomNr);
	         rowSet.updateDouble("TotalPrice", price);
	         rowSet.updateDate("checkin", checkIn);
	         rowSet.updateDate("checkout", checkOut);
	         rowSet.insertRow();
	         rowSet.moveToCurrentRow();
	         Status.status("Booking received", "Booking confirmation");     
		}
	    catch (SQLException | ClassNotFoundException ex) {
	    	Status.status("Booking failed", "Booking error");  
	    	ex.printStackTrace();
	    }
		
	}
}
