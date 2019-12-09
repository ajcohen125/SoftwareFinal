package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import Model.*;
import java.util.ArrayList;


/* TODO
 * 
 * Main
 * Home page
 * 
 * VIEWS THAT DEAL WITH ACCOUNT INFORMATION
 * 
 * LoginController
 * Write validateCredentials()
 * 
 * SettingsController
 * Write updateAccountInfo() and createAccount()
 * 
 * VIEWS WHAT DEAL WITH ITEMS STUFF
 * 
 * SearchController
 * Upload item data to the TableView
 * We had trouble displaying sample data
 * Most of the TableView data is there, but something is off
 * 
 * CartController
 * 
 */
public class MainController implements Initializable, EventHandler<ActionEvent> {
	
	// VARIABLES WE WANT TO ACCESS ACROSS ALL VIEWS
	// THESE VARIABLES ARE EXCLUSIVE TO THE MAIN CONTROLLER
	// Ex: Main.selectedOption
	// Default vaulues for these variables are set in initialize when the application is launched
	
	public static final int ALL = 0;
	public static final int PRODUCE = 1;
	public static final int GRAINS = 2;
	public static final int DRINKS = 3;
	public static final int SNACKS = 4;
	
	private String curFxml = "../View/Main.fxml";
	
	ArrayList<String>options = new ArrayList<String>();
	
	@FXML
	Button leftBtn;
	@FXML
	Button rightBtn;
	@FXML
	Button homeBtn;
	@FXML
	Button loginBtn;
	@FXML
	Button searchBtn;
	@FXML
	Button settingsBtn;
	@FXML
	Button cartBtn;
	@FXML
	ComboBox<String> optionsComboBox;
	@FXML
	TextField searchBox;
	
	@FXML
	Button allBtn;
	@FXML
	Button produceBtn;
	@FXML
	Button grainsBtn;
	@FXML
	Button drinksBtn;
	@FXML
	Button snacksBtn;
		
	@Override
	public void handle(ActionEvent e) {
				
		if( e.getSource() == homeBtn ) {
			
			goToView("../View/Main.fxml");
		}
		
		else if( loginBtn == e.getSource()) {

			goToView("../View/Login.fxml");
		}
		
		else if( searchBtn == e.getSource()) {

			goToView("../View/Search.fxml");
		}
		
		else if( settingsBtn == e.getSource()) {
					
			if(true == Main.isLoggedIn) {
				goToView("../View/Settings.fxml");
			}
			
			// redirect user to the login menu if they are not signed in
			// you need to be signed in to change your account settings
			else {
				goToView("../View/Login.fxml");
			}
		}
		
		else if( cartBtn == e.getSource()) {

			goToView("../View/Cart.fxml");
		}
		
		else if( leftBtn == e.getSource()) {
			
			goToView(Main.backwardView);
		}
		
		else if( rightBtn == e.getSource()) {
			forwardTrick();
			goToView(Main.forwardView);
		}
		
		else if( allBtn == e.getSource() ) {
			optionsComboBox.getSelectionModel().select(ALL);
			goToView("../View/Search.fxml");
		}
		
		else if( produceBtn == e.getSource() ) {
			optionsComboBox.getSelectionModel().select(PRODUCE);
			goToView("../View/Search.fxml");
		}
		
		else if( grainsBtn == e.getSource() ) {
			optionsComboBox.getSelectionModel().select(GRAINS);
			goToView("../View/Search.fxml");
		}
		
		else if( drinksBtn == e.getSource() ) {
			optionsComboBox.getSelectionModel().select(DRINKS);
			goToView("../View/Search.fxml");
		}
		
		else if( snacksBtn == e.getSource() ) {
			optionsComboBox.getSelectionModel().select(SNACKS);
			goToView("../View/Search.fxml");
		}
	}
	
	// set the variables in Main before switching views
	public void passVar() {
		Main.selectedOption = optionsComboBox.getSelectionModel().getSelectedIndex();
	}
	
	// code to simplify changing views
	public void goToView(String xmlPath) {
		
		try {
			passVar();
			Main.backwardView = curFxml;
			Main.forwardView = xmlPath;
			Parent root = FXMLLoader.load(getClass().getResource(xmlPath));
			Main.stage.setScene(new Scene(root, 1200, 800));
			Main.stage.show();
		}
		
		catch(IOException error) {
			System.out.print("\n\n\tError: Could not change scenes\n\n");
			error.printStackTrace();
		}
	}
	
	public void forwardTrick() {
		String temp = Main.forwardView;
		Main.forwardView = Main.backwardView;
		Main.backwardView = temp;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		System.out.println("Switched to Main View!");
		setUpNavigationBar();
		setUpQuickSelectButtons();
		
		// if the program was launched for the first time
		if( true == Main.justLaunched ) {
			
			Main.user = null;
			Main.selectedOption = 0;
			Main.selectedItem = null;
			Main.justLaunched = false;
			Main.cart = new Cart();
			Main.receiptList = new ArrayList<Receipt>();
		}
		
		else {
			
		}
	}
	
	public void setUpQuickSelectButtons() {
		
		allBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../Images/Food/Cereal.png"), allBtn.getPrefWidth(), allBtn.getPrefHeight(), true, true)));
		produceBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../Images/Food/Oranges.png"), produceBtn.getPrefWidth(), produceBtn.getPrefHeight(), true, true)));
		grainsBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../Images/Food/Bread.png"), grainsBtn.getPrefWidth(), grainsBtn.getPrefHeight(), true, true)));
		drinksBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../Images/Food/Sweet Tea.png"), drinksBtn.getPrefWidth(), drinksBtn.getPrefHeight(), true, true)));
		snacksBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../Images/Food/Candy.png"), snacksBtn.getPrefWidth(), snacksBtn.getPrefHeight(), true, true)));
	}
	
	public void setUpNavigationBar() {
		
		// Navigation Tabs
		leftBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../Images/leftArrow.png"), leftBtn.getPrefWidth()-30, leftBtn.getPrefHeight()-30, true, true)));
		rightBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../Images/rightArrow.png"), rightBtn.getPrefWidth()-30, rightBtn.getPrefHeight()-30, true, true)));
		homeBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../Images/logo.png"), homeBtn.getPrefWidth()-30, homeBtn.getPrefHeight()-30, true, true)));
		searchBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../Images/search.png"), searchBtn.getPrefWidth()-30, searchBtn.getPrefHeight()-30, true, true)));
		settingsBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../Images/settings.png"), settingsBtn.getPrefWidth()-30, settingsBtn.getPrefHeight()-30, true, true)));
		cartBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../Images/cart.png"), cartBtn.getPrefWidth(), cartBtn.getPrefHeight()-10, true, true)));
		
		if(Main.isLoggedIn == false) {
			loginBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../Images/person.png"), loginBtn.getPrefWidth()-10, loginBtn.getPrefHeight()-30, true, true)));
		}
		
		else {
			loginBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../Images/login.png"), loginBtn.getPrefWidth()-10, loginBtn.getPrefHeight()-30, true, true)));
		}
		
		// Search Options
		options.add("All");
		options.add("Produce");
		options.add("Grains");
		options.add("Drinks");
		options.add("Snacks");
		
		// set options for combo box
		ObservableList<String> observableOptions = FXCollections.observableArrayList(options);
		optionsComboBox.setItems(observableOptions);
		optionsComboBox.getSelectionModel().select(Main.selectedOption);	
	}
	
}