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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import Model.*;
import java.util.ArrayList;

// TODO
// Write updateAccountInfo() and createAccount()

public class SettingsController implements Initializable, EventHandler<ActionEvent> {
	
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
	
	// LOCAL
	
	// Both
	@FXML
	Button updateBtn;
	@FXML
	Label messageLabel;
	
	// Account Information
	@FXML
	TextField accountNameTextField;
	@FXML
	TextField accountAddressTextField;
	@FXML 
	TextField accountStoreCreditTextField;
	@FXML
	TextField accountEmailTextField;
	
	// Credit Card Information
	@FXML
	TextField cardNumberTextField;
	@FXML
	TextField cardFullNameTextField;
	@FXML
	TextField cardAddressTextField;
	@FXML
	TextField cardCCVTextField;
	@FXML
	TextField cardExpDateTextField;
		
	@Override
	public void handle(ActionEvent e) {
				
		if( e.getSource() == homeBtn ) {
			
			passVar();
			goToView("../View/Main.fxml");
		}
		
		else if( loginBtn == e.getSource()) {

			passVar();
			goToView("../View/Login.fxml");
		}
		
		else if( searchBtn == e.getSource()) {

			passVar();
			goToView("../View/Search.fxml");
		}
		
		else if( settingsBtn == e.getSource()) {
			
			passVar();
			
			if(true == MainController.isLoggedIn) {
				goToView("../View/Settings.fxml");
			}
			
			// redirect user to the login menu if they are not signed in
			// you need to be signed in to change your account settings
			else {
				goToView("../View/Login.fxml");
			}
		}
		
		else if( cartBtn == e.getSource()) {

			passVar();
			goToView("../View/Cart.fxml");
		}
		
		else if( leftBtn == e.getSource()) {
			
		}
		
		else if( rightBtn == e.getSource()) {
			
		}
		
		else if (updateBtn == e.getSource()) {
			System.out.println("Update button pressed");
			
			// if signed in
			if( true == MainController.isLoggedIn ) {
				
				if( true == allFieldsProvided()) {
					updateAccountInfo();
				}
			}
			// if creating an account
			else {
				
				if( true == allFieldsProvided()) {
					createAccount();
				}
			}
			
		}
	}
	
	// set the variables in MainController before switching views
	public void passVar() {
		MainController.selectedOption = optionsComboBox.getSelectionModel().getSelectedItem().toString();
	}
	
	// code to simplify changing views
	public void goToView(String xmlPath) {
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource(xmlPath));
			Main.stage.setScene(new Scene(root, 1200, 800));
			Main.stage.show();
		}
		
		catch(IOException error) {
			System.out.print("\n\n\tError: Could not change scenes\n\n");
			error.printStackTrace();
		}
	}
	
	// TODO
	// TODO
	// TODO
	// TODO
	// TODO
	
	// updates user info
	public void updateAccountInfo() {
		
	}
	
	// TODO
	// TODO
	// TODO
	// TODO
	// TODO
	
	// creates a new account
	public void createAccount() {
		
	}
	
	// creates a new account
	// called in initialize() if the user is signed in
	public void displayUserInfo() {
		
		System.out.println("displayUserInfo: comment code back in after user info is populated");
		
		// just to let the the program work until it is finished
		if(MainController.user == null ) {
			return;
		}
		
		accountNameTextField.setText(MainController.user.getName());
		accountAddressTextField.setText(MainController.user.getAddress()); 
		accountStoreCreditTextField.setText(MainController.user.getCredit()+"");
		accountEmailTextField.setText(MainController.user.getEmail());
		cardNumberTextField.setText(MainController.user.getPayment().getCcNum());
		cardFullNameTextField.setText(MainController.user.getPayment().getName());
		cardAddressTextField.setText(MainController.user.getPayment().getAddress());
		cardCCVTextField.setText(MainController.user.getPayment().getCcNum());
		cardExpDateTextField.setText(MainController.user.getPayment().getExpDate());
	}
	
	// checks to see if all fields were provided
	// to make life simple, all field will be required when creating an account
	public boolean allFieldsProvided() {
		
		if( accountNameTextField.getText() != null ||
			accountAddressTextField.getText() != null ||
			accountStoreCreditTextField.getText() != null ||
			accountEmailTextField.getText() != null ||
			cardNumberTextField.getText().isEmpty() ||
			cardFullNameTextField.getText().isEmpty() ||
			cardAddressTextField.getText().isEmpty() ||
			cardCCVTextField.getText().isEmpty() ||
			cardExpDateTextField.getText().isEmpty())
		{
			System.out.println("Some fields missing");
			return true;
		}
		
		System.out.println("All field provided");
		return false;
			
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		System.out.println("Switched to Settings View!");
		
		// if signed in
		if( true == MainController.isLoggedIn ) {
			displayUserInfo();
			updateBtn.setText("Update");
		}
		// the only way to get to the SettingsView while signed out is by pressing the 
		// SignUp link in the LoginView
		// Therefore, if the user is at this view and not signed in, they are signing up
		else {
			updateBtn.setText("Sign Up");
		}
		
		setUpNavigationBar();
	}
	
	public void setUpNavigationBar() {
		
		// Navigation Tabs
		leftBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../Images/leftArrow.png"), leftBtn.getPrefWidth()-30, leftBtn.getPrefHeight()-30, true, true)));
		rightBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../Images/rightArrow.png"), rightBtn.getPrefWidth()-30, rightBtn.getPrefHeight()-30, true, true)));
		homeBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../Images/logo.png"), homeBtn.getPrefWidth()-30, homeBtn.getPrefHeight()-30, true, true)));
		searchBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../Images/search.png"), searchBtn.getPrefWidth()-30, searchBtn.getPrefHeight()-30, true, true)));
		settingsBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../Images/settings.png"), settingsBtn.getPrefWidth()-30, settingsBtn.getPrefHeight()-30, true, true)));
		cartBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../Images/cart.png"), cartBtn.getPrefWidth(), cartBtn.getPrefHeight()-10, true, true)));
		
		if(MainController.isLoggedIn == false) {
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
		optionsComboBox.getSelectionModel().selectFirst();
		
	}
	
}