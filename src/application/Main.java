package application;

import Model.*;
import Database.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
public class Main extends Application {
	
	public static Stage stage;
	public static AnchorPane layout;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			stage.setTitle("G9 Grocers");
			initLayout();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// itemList - an array list of EVERY ITEM we have
		// added a change so I can stage this
		ArrayList<Item> itemList = new ArrayList<Item>();
		String sql = "SELECT * FROM Items;";
		java.sql.ResultSet r = DataBase.select(sql);
		try {
			while(r.next()){
				Item itemTest = new Item("001", "Toothbrush", 4.00, 5);
				itemTest.ID = r.getString(1);
				itemTest.name  = r.getString(2);
				itemTest.price = r.getDouble(3);
				itemTest.quantity =  r.getInt(4);
				itemList.add(itemTest);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Item temp: itemList){
			System.out.println(temp.name);
		}
		
		// Test for inserting a customer into the DB
		Customer test = Customer.dummyCustomer();
		sql = "INSERT INTO Accounts (Username, "
				+ "Password, Email, "
				+ "Address, "
				+ "CCNum, CCName, "
				+ "ExpDate, CCV, "
				+ "BillingAddress) VALUES"
				+ " ('" + test.name + "', '"
				+ test.password + "', '"
				+ test.email + "', '"
				+ test.address + "', '"
				+ test.payment.ccNum + "', '"
				+ test.payment.name + "', '"
				+ test.payment.expDate + "', '"
				+ test.payment.CVV + "', '"
				+ test.payment.address
				+ "')";
		//System.out.println(sql);
		//int i = DataBase.update(sql);
		//System.out.println(i);
		
		// Try and delete newly created user
		//sql = "DELETE FROM Accounts WHERE Username = 'John Smith'";
		//int i = DataBase.update(sql);
		//System.out.println(i);
	}
	
	public void initLayout() {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation( Main.class.getResource("../View/Main.fxml") );
			layout = (AnchorPane) loader.load();
			Scene scene = new Scene( layout );
			stage.setScene( scene );
			stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
