package bookingstart;

import java.sql.SQLException;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import java.time.LocalDate;
import java.util.HashMap;

public class Reservation {
	// connectie met database
	   static final String JDBC_DRIVER =
	      "com.mysql.jdbc.Driver";
	   static final String DB_URL =
	      "jdbc:mysql://localhost/hotel_booking";
	   static final String DB_USER = "root";
	   static final String DB_PASS = "";
	   private JdbcRowSet rowSet = null;
	   public HashMap<Integer, Integer> allRooms = new HashMap<>();
	   
	// creating rowset
	   public Reservation(LocalDate date_wanted_checkout, LocalDate date_wanted_checkin, String roomType) {
	      try {
	         Class.forName(JDBC_DRIVER);
	         rowSet = RowSetProvider.newFactory().createJdbcRowSet();
	         rowSet.setUrl(DB_URL);
	         rowSet.setUsername(DB_USER);
	         rowSet.setPassword(DB_PASS);
	         rowSet.setCommand("SELECT k.Roomnr, k.Type, r.Bookingnr FROM kamers k"
	         		+ " LEFT JOIN reservering r ON (k.Roomnr = r.Roomnr)"
	        		 // checkout is tussen een al geboekte periode
	         		+ " AND ((r.checkin < '" + date_wanted_checkout + "' AND r.checkout >= '" + date_wanted_checkout + "')"
	         		 // checkin is tussen een al geboekte periode
	         		+ " OR (r.checkin <= '" + date_wanted_checkin + "' AND r.checkout > '" + date_wanted_checkin + "'))"
	         		// voor het geselecteerde roomtype
	         		+ " WHERE (k.Type = '" + roomType + "')");
	         rowSet.execute();
			}
	      catch (SQLException | ClassNotFoundException ex) {
	          System.out.println(roomType);
	    	  ex.printStackTrace();
	      }
	   } 
	   
	   
	   public HashMap<Integer, Integer> getAllRooms() {
		   	try{
		   		while(rowSet.next()) {
		   			allRooms.put(rowSet.getInt("k.roomnr"), rowSet.getInt("r.bookingnr"));	         	
		      	}
		    }
		    catch (SQLException ex) {
		      	ex.printStackTrace();
		    }
		    return allRooms;  
	   }
	   

	   
	   
}
