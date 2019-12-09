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
	//item lists
	public static ArrayList<Item> itemList;
	public static ArrayList<Item> produceList;
	public static ArrayList<Item> drinkList;
	public static ArrayList<Item> grainList;
	public static ArrayList<Item> snackList;
	
	//public static ArrayList<Receipt> receiptList;
	public static Cart currentCart;
	public static Customer user; 
	public static Cart cart;
	public static Item curItem;
	public static int selectedOption;           // currently unused
	public static Item selectedItem;
	public static String backwardView;
	public static String forwardView;
	public static boolean isLoggedIn = false;      // used in various Controllers
	public static boolean justLaunched = true;     // only used in the MainController to see if the app was just launched
	
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
		itemList = new ArrayList<Item>();
		produceList = new ArrayList<Item>();
		drinkList = new ArrayList<Item>();
		grainList = new ArrayList<Item>();
		snackList = new ArrayList<Item>();
		
		currentCart = new Cart();
		
		String sql = "SELECT * FROM Items;";
		java.sql.ResultSet r = DataBase.select(sql);
		try {
			while(r.next()){
				Item itemTest = new Item();
				itemTest.ID = r.getString(1);
				itemTest.name  = r.getString(2);
				itemTest.price = r.getDouble(3);
				itemTest.quantity =  r.getInt(4);
				itemTest.category = r.getString(5);
				itemList.add(itemTest);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error in MYSQL");
			e.printStackTrace();
		}
		
		for(Item temp: itemList){
			System.out.println(temp.name);
			switch(temp.category) {
			case "Produce":
				produceList.add(temp);
				break;
			case "Drinks":
				drinkList.add(temp);
				break;
			case "Snacks":
				snackList.add(temp);
				break;
			case "Grains":
				grainList.add(temp);
				break;
			}
		}
		
		//TODO: remove this line when we get the full cart set up
		currentCart.itemList = itemList; //FOR TESTING ONLY
		
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
