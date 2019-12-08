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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import Model.*;
import java.util.ArrayList;

public class CartController implements Initializable, EventHandler<ActionEvent> {
	
	public static final int STANDARD = 0;
	public static final int EXPEDITED = 1;
	
	ArrayList<String>options = new ArrayList<String>();
	private String curFxml = "../View/Cart.fxml";
	
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
	@FXML
	Button deleteBtn;
	@FXML
	TextField quantityTextField;
	@FXML
	Button addBtn;
	@FXML
	Button subBtn;
	
	@FXML
	RadioButton creditCardRadioBtn;
	@FXML
	RadioButton paypalRadioBtn;
	@FXML
	TextField emailTextField;
	@FXML
	TextField deliveryAddressTextField;
	@FXML
	TextField cardNumberTextField;
	@FXML
	TextField paypalEmailTextField;
	@FXML
	TextField paypalPasswordField;
	@FXML
	CheckBox useStoreCreditCheckBox;
	
	@FXML
	ComboBox<String> deliveryMethodComboBox;
	@FXML
	Label totalLabel;
	@FXML
	Label messageLabel;
	@FXML
	Button placeOrderBtn;
	@FXML
	Label storeCreditLabel;
	@FXML
	Label totalCostLabel;
	
	@FXML
	TableView<Item> results;	
	@FXML
	TableColumn<Item, String> idCol;
	@FXML
	TableColumn<Item, String> nameCol;
	@FXML
	TableColumn<Item, Double> priceCol;
	@FXML
	TableColumn<Item, Integer> quantityCol;
	
	ToggleGroup paymentOptionToggleGroup;
	ObservableList<Item> items ;
	Item selectedItem;
	
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
		
		else if ( deleteBtn == e.getSource()) {
			
			Main.cart.removeItem(selectedItem.getID());
			
			// TODO ...maybe
			// TODO ...maybe
			// TODO ...maybe
			// TODO ...maybe
			// TODO ...maybe
			
			// idk if the observable list needs to be changed
			
		}
		
		else if( addBtn == e.getSource()) {
			System.out.println("addBtn");
			int val = Integer.parseInt(quantityTextField.getText());
			val += 1;
			quantityTextField.setText(val+"");
			Main.cart.updateItemQuantity(selectedItem.getID(), val);
		}
		
		else if ( subBtn == e.getSource()) {
			int val = Integer.parseInt(quantityTextField.getText());
			
			if(val > 0) {
				val -= 1;
				quantityTextField.setText(val+"");
				Main.cart.updateItemQuantity(selectedItem.getID(), val);
				System.out.println("new quantity is: "+val);
			}
		}
		
		else if( placeOrderBtn == e.getSource()) {
			
			if(Main.isLoggedIn == true) {
				generateReceipt();
				goToView("../View/Receipt.fxml");
			}
			
			else {
				messageLabel.setText("Please sign in");
			}
		}
		
		else if( creditCardRadioBtn == e.getSource()) {
			
			paypalEmailTextField.setText("");
			paypalPasswordField.setText("");
			paypalEmailTextField.setDisable(true);
			paypalPasswordField.setDisable(true);	
			cardNumberTextField.setDisable(false);
		}
		
		else if( paypalRadioBtn == e.getSource()) {
			
			cardNumberTextField.setDisable(true);
			paypalEmailTextField.setDisable(false);
			paypalPasswordField.setDisable(false);
		}
		
		else if( useStoreCreditCheckBox == e.getSource()) {
			
			// this will make the total cost reflect the shipping cost
			updateTotalCostLabel();
		}
	}
	
	public void generateReceipt() {
		
		double finalCost = Double.parseDouble(totalCostLabel.getText().substring(2));
		double creditUsed = 0;
		
		// UNCOMMENT ME!
		/*
		if( useStoreCreditCheckBox.isSelected()) {
			
			if( Main.user.getCredit() < COST OF ITEMS) {
				creditUsed = Main.user.getCredit();
			}
			
			// user had more credit than needed
			else {
				creditUsed = COST OF ITEMS
				Main.user.setCredit(Main.user.getCredit()-creditUsed);
			}
		}
		*/
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
		
		System.out.println("Switched to Cart Controller!");
		setUpNavigationBar();
		quantityTextField.setEditable(false);
		
		// if logged in, fill out the TextFields with user information
		if( true == Main.isLoggedIn) {
			emailTextField.setText(Main.user.getEmail());
			deliveryAddressTextField.setText(Main.user.getAddress());
			String cardNumber = Main.user.getPayment().getCcNum();
			cardNumberTextField.setText("XXXX-XXXX-XXXX-"+cardNumber.substring(cardNumber.length()-4));
			emailTextField.setEditable(false);
			deliveryAddressTextField.setEditable(false);
			cardNumberTextField.setEditable(false);
			storeCreditLabel.setText("$ "+Main.user.getCredit());
		}
		
		// disallow interacting with components if not signed in
		else {/*
			addBtn.setDisable(true);
			subBtn.setDisable(true);
			deleteBtn.setDisable(true);
			deliveryMethodComboBox.setDisable(true);
			emailTextField.setDisable(true);
			deliveryAddressTextField.setDisable(true);
			cardNumberTextField.setDisable(true);
			paypalEmailTextField.setDisable(true);
			paypalPasswordField.setDisable(true);
			useStoreCreditCheckBox.setDisable(true);*/
			paypalEmailTextField.setDisable(true);
			paypalPasswordField.setDisable(true);	
			storeCreditLabel.setText("$ 0");
		}
		
		// what to do when the table is pressed
		results.setOnMousePressed(e -> {
			selectedItem = results.getSelectionModel().getSelectedItem();
			quantityTextField .setText(selectedItem.getQuantity()+"");
		});
		
		// initial set the payment option to credit card
		paymentOptionToggleGroup = new ToggleGroup();
		creditCardRadioBtn.setToggleGroup(paymentOptionToggleGroup);
		paypalRadioBtn.setToggleGroup(paymentOptionToggleGroup);
		creditCardRadioBtn.setSelected(true);
		
		// delivery options
		ArrayList<String>deliveryOptions = new ArrayList<String>();
		deliveryOptions.add("Standard Shipping ($4.00)");
		deliveryOptions.add("Expedited Shipping ($7.00)");
		
		// add options to the combo box
		ObservableList<String> observableDeliveryOptions = FXCollections.observableArrayList(deliveryOptions);
		deliveryMethodComboBox.setItems(observableDeliveryOptions);
		deliveryMethodComboBox.getSelectionModel().selectFirst();
		
		// changed delivery method
		deliveryMethodComboBox.getSelectionModel().selectedItemProperty().addListener(e ->{
			updateTotalCostLabel();
		});
		
		updateTotalCostLabel();
		loadCart();
	}
	
	public void updateTotalCostLabel() {
		
		double totalCost = 0;
		
		// total cost of items
		totalCost += Main.cart.getTotalCost();
		
		// cost of delivery
		if( deliveryMethodComboBox.getSelectionModel().getSelectedIndex() == STANDARD ) {
			totalCost += 4;
		}
		
		else {
			totalCost += 7;
		}
		
		// store credit
		if( useStoreCreditCheckBox.isSelected()) {
			
			totalCost -= Main.user.getCredit();
			
			// don't worry about this
			// generateReceipt() will calculate how much store credit was actually used
			if( totalCost < 0 ) {
				totalCost = 0;
			}
		}
		
		totalCostLabel.setText("$ "+totalCost);
	}
	
	public void loadCart() {
		
		// create ObservableList from ArrayList
		items = FXCollections.observableArrayList();
		items = Main.cart.getAssignmentTableList();
		
		idCol.setCellValueFactory(new PropertyValueFactory<Item, String>("ID"));
		
		nameCol.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
		
		priceCol.setCellValueFactory(new PropertyValueFactory<Item, Double>("price"));
		
		quantityCol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("quantity"));

		results.setItems(items);
		
		results.getSortOrder().add(nameCol);
		
		results.getSelectionModel().select(null);
		
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
		optionsComboBox.getSelectionModel().selectFirst();
		optionsComboBox.getSelectionModel().select(Main.selectedOption);
	}
	
}