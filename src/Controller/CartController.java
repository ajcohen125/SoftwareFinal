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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import Model.*;
import java.util.ArrayList;

public class CartController implements Initializable, EventHandler<ActionEvent> {
	
	private final int STANDARD = 0;
	private final int EXPEDITED = 0;
	
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
	TextField emailTextField;
	@FXML
	TextField deliveryAddressTextField;
	@FXML
	TextField cardNumberTextField;
	@FXML
	ComboBox<String> deliveryMethodComboBox;
	@FXML
	Label totalLabel;
	@FXML
	Label messageLabel;
	@FXML
	Button placeOrderBtn;
	
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

			goToView("../View/Cart.fxml");
		}
		
		else if( leftBtn == e.getSource()) {
			goToView(MainController.backwardView);
		}
		
		else if( rightBtn == e.getSource()) {
			forwardTrick();
			goToView(MainController.forwardView);
		}
		
		else if ( deleteBtn == e.getSource()) {
			
		}
		
		else if( addBtn == e.getSource()) {
			System.out.println("addBtn");
			int val = Integer.parseInt(quantityTextField.getText());
			val += 1;
			quantityTextField.setText(val+"");
		}
		
		else if ( subBtn == e.getSource()) {
			int val = Integer.parseInt(quantityTextField.getText());
			
			if(val > 0) {
				val -= 1;
				quantityTextField.setText(val+"");
			}
		}
		
		else if( placeOrderBtn == e.getSource()) {
			
			if(MainController.isLoggedIn == true) {
				goToView("../View/Receipt.fxml");
			}
			
			else {
				messageLabel.setText("Please sign in");
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		System.out.println("Switched to Cart Controller!");
		setUpNavigationBar();
		quantityTextField.setEditable(false);
		
		if( true == MainController.isLoggedIn) {
			emailTextField.setText(MainController.user.getEmail());
			deliveryAddressTextField.setText(MainController.user.getAddress());
			String cardNumber = MainController.user.getPayment().getCcNum();
			cardNumberTextField.setText("XXXX-XXXX-XXXX-"+cardNumber.substring(cardNumber.length()-4));
			
			emailTextField.setEditable(false);
			deliveryAddressTextField.setEditable(false);
			cardNumberTextField.setEditable(false);
			
			loadCart();
		}
		
		// disallow interacting with components if not signed in
		else {
			deliveryMethodComboBox.setDisable(true);
		}
	}
	
	public void loadCart() {
		
		// create ObservableList from ArrayList
		ObservableList<Item> items = FXCollections.observableArrayList();
		items = Main.currentCart.getAssignmentTableList();
		
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