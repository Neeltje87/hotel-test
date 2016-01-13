package bookingstart;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

import javafx.application.*;

import javafx.stage.*;
import javafx.util.Callback;
import javafx.scene.*;

import javafx.scene.layout.*;

import javafx.scene.control.*;

import javafx.geometry.*;

import javafx.scene.text.*;





public class RoomOrder extends Application

{

    public static void main(String[] args)

    {

        launch(args);

    }



   	Stage stage;

   	// datum selecteerder
   	DatePicker checkInDatePicker;
   	DatePicker checkOutDatePicker;
   	
    // Room type radio buttons
   	
    RadioButton rdoBasic;

    RadioButton rdoSuite;


	// Decorator check boxes

    CheckBox chkSlaapbank;

    CheckBox chkTv;

    CheckBox chkAllInclusive;


    @Override public void start(Stage primaryStage)

    {

		stage = primaryStage;

		// ----- Create the top pane -----


		Text textHeading = new Text("Book your room");

		textHeading.setFont(new Font(20));

		HBox paneTop = new HBox(textHeading);

		paneTop.setPadding(new Insets(20, 10, 20, 10));


		// ---------- Create the order pane ----------

		// Create date picker pane
		
		Label lblIn = new Label("check in date");
		Label lblOut = new Label("check out date");
		checkInDatePicker = new DatePicker();
		checkOutDatePicker = new DatePicker();
		
		checkInDatePicker.setValue(LocalDate.now());
	    
			// calculate the length of stay and present in tooltip
		final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
	      @Override
	      public DateCell call(final DatePicker datePicker) {
	        return new DateCell() {
	          @Override
	          public void updateItem(LocalDate item, boolean empty) {
	            super.updateItem(item, empty);

	            long p = ChronoUnit.DAYS.between(checkInDatePicker.getValue(), item);
	            if (p>0){
	            	setTooltip(new Tooltip("You're about to stay for " + p + " days"));
	            }
	            
	          }
	        };
	      }
	    };
	    
	    checkOutDatePicker.setDayCellFactory(dayCellFactory);
	    checkOutDatePicker.setValue(checkInDatePicker.getValue().plusDays(1));

	    // label en input onder elkaar (verticaal) en checkin en checkout naast elkaar (horizontaal)
		VBox paneIn = new VBox(lblIn, checkInDatePicker);
		VBox paneOut = new VBox(lblOut, checkOutDatePicker);
		
		HBox paneDuration = new HBox(paneIn, paneOut);
		paneDuration.setSpacing(10);
		
		// Create the roomtype pane

		Label lblSize = new Label("Room Type");

		rdoBasic = new RadioButton("Basic");

		rdoSuite = new RadioButton("Suite");


		rdoBasic.setSelected(true);

		ToggleGroup groupSize = new ToggleGroup();

		rdoBasic.setToggleGroup(groupSize);

		rdoSuite.setToggleGroup(groupSize);


		VBox paneSize = new VBox(lblSize, rdoBasic, rdoSuite);

		paneSize.setSpacing(10);


		// Create the extra's pane

		Label lblToppings = new Label("Extra's");

		chkSlaapbank = new CheckBox("Slaapbank");

		chkTv = new CheckBox("Tv");

		chkAllInclusive = new CheckBox("All Inclusive");



		FlowPane paneToppings = new FlowPane(Orientation.VERTICAL,

			chkSlaapbank, chkTv, chkAllInclusive);

		paneToppings.setPadding(new Insets(10, 0, 10, 0));

		paneToppings.setHgap(20);

		paneToppings.setVgap(10);

		paneToppings.setPrefWrapLength(100);


		VBox paneTopping = new VBox(lblToppings, paneToppings);

		// Add the roomtype and extra's pane to the order pane

		HBox paneBooking = new HBox(40, paneSize, paneTopping);

		// Create the center pane

		VBox paneCenter = new VBox(40, paneDuration, paneBooking);

		paneCenter.setPadding(new Insets(0,10, 0, 10));


		// ---------- Create the bottom pane ----------


		Button btnOK = new Button("OK");

		btnOK.setPrefWidth(80);

		btnOK.setOnAction(e -> btnOK_Click() );



		Button btnCancel = new Button("Cancel");

		btnCancel.setPrefWidth(80);

		btnCancel.setOnAction(e -> btnCancel_Click() );



		Region spacer = new Region();



		HBox paneBottom = new HBox(10, spacer, btnOK, btnCancel);

		HBox.setHgrow(spacer, Priority.ALWAYS);

		paneBottom.setPadding(new Insets(20, 10, 20, 10));



		// ---------- Finish the scene ----------



		BorderPane paneMain = new BorderPane();

		paneMain.setTop(paneTop);

		paneMain.setCenter(paneCenter);

		paneMain.setBottom(paneBottom);



        // Create the scene and the stage



		Scene scene = new Scene(paneMain);

		primaryStage.setScene(scene);

		primaryStage.setTitle("Room Reservation");

		primaryStage.show();

	}

// create actions to execute on button click

	public void btnOK_Click()

	{
		// get desired booking dates
		LocalDate checkIn = checkInDatePicker.getValue();
		LocalDate checkOut = checkOutDatePicker.getValue();
		
		// Add the room type

		HotelRoom roomType = new BasicRoom();
		Double price = roomType.getPrice();

		if (rdoBasic.isSelected()) {
			roomType = new BasicRoom();
		}

		if (rdoSuite.isSelected()) {
			roomType = new Suite();
		}
			
		String booking = roomType.getDescription();
		// check availability (checkout, checkin, roomtype)
		// if booking is 0 then room is free
		Reservation rooms = new Reservation(checkOut, checkIn, booking);
		HashMap<Integer, Integer> reservations = rooms.getAllRooms();
		
		// calculate duration of stay
		Period period = Period.between(checkIn, checkOut);
		int duration = period.getDays();
		
		
		// continue if free room found
		if(reservations.containsValue(0)) {
			
			// Add the extra's
			String extras = "";

			if (chkSlaapbank.isSelected()) {
				RoomDecorator room = new Slaapbank(roomType);
				price += room.getExtraPrice();
				extras += room.getExtraDescription();
			}
			if (chkAllInclusive.isSelected()) {
				RoomDecorator room = new Allinclusive(roomType);
				price += room.getExtraPrice();
				extras += room.getExtraDescription();
			}
			if (chkTv.isSelected()) {
				RoomDecorator room = new Tv(roomType);
				price += room.getExtraPrice();
				extras += room.getExtraDescription();
			}

			if (extras == "") {
				booking += " with no extras";
			} else {
				booking += " with: \n" + extras;
			}
			
			// calculate total price
			price = duration * price;
			
			booking += "\n Total price = " + price;
		
		} else {	
			// if no free room found
			booking += " \n Sorry no room available for given time period";
		}
		// Display the message

		MessageBox.show(booking, "Booking pending");
		//System.out.println("Price of " + booking);
		
		

	}



	public void btnCancel_Click()

	{

		stage.close();

	}



}

