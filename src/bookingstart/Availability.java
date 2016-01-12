package bookingstart;

import java.sql.SQLException;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
//import java.time.LocalDate;
import java.util.HashMap;

public class Availability {
	// connectie met database
	   static final String JDBC_DRIVER =
	      "com.mysql.jdbc.Driver";
	   static final String DB_URL =
	      "jdbc:mysql://localhost/hotel_booking";
	   static final String DB_USER = "root";
	   static final String DB_PASS = "";
	   private JdbcRowSet rowSet = null;
	   public HashMap<Integer, String> RoomAvailability = new HashMap<>();
	   
	// creating rowset
	   public Availability() {
	      try {
	         Class.forName(JDBC_DRIVER);
	         rowSet = RowSetProvider.newFactory().createJdbcRowSet();
	         rowSet.setUrl(DB_URL);
	         rowSet.setUsername(DB_USER);
	         rowSet.setPassword(DB_PASS);
	         rowSet.setCommand("SELECT * FROM reservering");
	         rowSet.execute();
			}
	      catch (SQLException | ClassNotFoundException ex) {
	         ex.printStackTrace();
	      }
	   } 
	   
	   public HashMap<Integer, String> getRoomAvailability() {
		   	try{
		   		while(rowSet.next()) {
		   			RoomAvailability.put(rowSet.getInt("roomnr"), rowSet.getString("roomType"));	         	
		      	}
		    }
		    catch (SQLException ex) {
		      	ex.printStackTrace();
		    }
		    return RoomAvailability;  
	   }

	   
	   
}
