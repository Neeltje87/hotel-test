package bookingstart;

import java.time.LocalDate;
import java.util.HashMap;

import javafx.application.*;

import javafx.stage.*;

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



		HBox paneOrder = new HBox(50, paneSize, paneTopping);



		// Create the center pane



		VBox paneCenter = new VBox(20, paneOrder);

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
		
		// Add the room type

		HotelRoom roomType = new BasicRoom();
		Double price = roomType.getPrice();

		if (rdoBasic.isSelected()) {
			roomType = new BasicRoom();
		}

		if (rdoSuite.isSelected()) {
			roomType = new Suite();
		}

		// check availability...
		Availability rooms = new Availability(LocalDate.of(2016, 1, 13));
		HashMap<Integer, Integer> allRooms = rooms.getAllRooms();
		System.out.println(allRooms);
		
			String booking = roomType.getDescription();

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

			booking += "\n Total price = " + price;

			// Display the message

			MessageBox.show(booking, "Booking pending");
			//System.out.println("Price of " + booking);
		
		

	}



	public void btnCancel_Click()

	{

		stage.close();

	}



}

