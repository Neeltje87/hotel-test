package bookingstart;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import java.time.LocalDate;

import javafx.geometry.*;
public class MessageBox
{
	public static void confirm(String message, String title, int roomNr, double price, LocalDate checkIn, LocalDate checkOut)
	{
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(title);
		stage.setMinWidth(250);
		Label lbl = new Label();
		lbl.setText(message);
		Button btnBook = new Button();
		Button btnCancel = new Button();
		btnBook.setText("Book room");
		btnCancel.setText("Cancel");
		btnBook.setOnAction(e -> {new Book(roomNr, price, checkIn, checkOut); stage.close();});
		btnCancel.setOnAction(e -> stage.close());
		VBox pane = new VBox(20);
		pane.getChildren().addAll(lbl, btnBook, btnCancel);
		pane.setAlignment(Pos.CENTER);
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.showAndWait();
	}
}
