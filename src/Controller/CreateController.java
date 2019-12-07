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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import Model.*;
import java.util.ArrayList;

// TODO
// Write updateAccountInfo() and createAccount()

public class CreateController implements Initializable, EventHandler<ActionEvent> {
	
	ArrayList<String>options = new ArrayList<String>();
	private String curFxml = "../View/Settings.fxml";
	
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
	TextField accountEmailTextField;
	@FXML
	PasswordField accountPasswordField;
	@FXML
	PasswordField accountConfirmPasswordField;
	
	// Credit Card Information
	@FXML
	TextField cardNumberTextField;
	@FXML
	TextField cardFullNameTextField;
	@FXML
	TextField cardAddressTextField;
	@FXML
	PasswordField cardCVVPasswordField;
	@FXML
	TextField cardExpDateTextField;
		
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
			
			// already here
		}
		
		else if( cartBtn == e.getSource()) {

			goToView("../View/Cart.fxml");
		}
		
		else if( leftBtn == e.getSource()) {
			goToView(MainController.backwardView);
		}
		
		else if( rightBtn == e.getSource()) {
			forwardTrick();
			goToView(MainController.forwardView);
		}
		
		else if ( updateBtn == e.getSource()) {
			System.out.println("Update button pressed");
			
			if( true == allFieldsProvided()) {
				
				if( accountPasswordField.getText().trim() == accountConfirmPasswordField.getText().trim()) {
					createAccount();
				}
				
				else {
					messageLabel.setText("Password do not match");
				}
			}
			else {
				messageLabel.setText("Some fields missing");
			}
		}
	}
	
	// set the variables in MainController before switching views
	public void passVar() {
		MainController.selectedOption = optionsComboBox.getSelectionModel().getSelectedIndex();
	}
	
	// code to simplify changing views
	public void goToView(String xmlPath) {
		
		try {
			passVar();
			MainController.backwardView = curFxml;
			MainController.forwardView = xmlPath;
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
		String temp = MainController.forwardView;
		MainController.forwardView = MainController.backwardView;
		MainController.backwardView = temp;
	}
	
	// TODO
	// TODO
	// TODO
	// TODO
	// TODO
	
	// Data is only saved locally
	// Changes need to be pushed to the database
	
	// updates user info
	public void createAccount() {
		
		// Payment p = new Payment("111122223333", "12/34", "John A Smith", "123", "123 Nunya Biz");
		
    	Payment p = new Payment(cardNumberTextField.getText().trim(), 
    			                cardExpDateTextField.getText().trim(),
    			                cardFullNameTextField.getText().trim(),
    			                cardCVVPasswordField.getText().trim(),
    			                cardAddressTextField.getText().trim());
    	
    	// Customer c = new Customer("John Smith", "johnsmith@gmail.com", "drowssap", "UTSA blvd", p, 10);
    	
    	Customer c = new Customer(accountNameTextField.getText().trim(),
    			                  accountEmailTextField.getText().trim(),
    			                  accountPasswordField.getText().trim(),
    			                  accountAddressTextField.getText().trim(),
    			                  p,
                                  0);
    			                  
    	// TODO
    	// TODO
    	// TODO
    	// TODO
    	// TODO
    	
    	// This newly created account needs to be added to the database
	}
	
	// checks to see if all fields were provided
	// to make life simple, all field will be required when creating an account
	public boolean allFieldsProvided() {
		
		if( accountNameTextField.getText().isEmpty() ||
			accountAddressTextField.getText().isEmpty() ||
			accountEmailTextField.getText().isEmpty() ||
			cardNumberTextField.getText().isEmpty() ||
			cardFullNameTextField.getText().isEmpty() ||
			cardAddressTextField.getText().isEmpty() ||
			cardCVVPasswordField.getText().isEmpty() ||
			cardExpDateTextField.getText().isEmpty())
		{
			// some fields missing
			return false;
		}
		
		// all field provided
		return true;
			
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		updateBtn.setText("Sign Up");
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
		optionsComboBox.getSelectionModel().select(MainController.selectedOption);
	}
	
}