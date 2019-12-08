package Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
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

public class ItemController implements Initializable, EventHandler<ActionEvent> {
	
	ArrayList<String>options = new ArrayList<String>();
	private String curFxml = "../View/Item.fxml";
	
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
	Label itemNameLabel;
	@FXML
	Label itemPriceLabel;
	@FXML
	TextField quantityTextField;
	@FXML
	Button addBtn;
	@FXML
	Button subBtn;
	@FXML
	Button addToCartBtn;
	@FXML
	ImageView itemImageView;
	@FXML
	Label itemLabel;
	@FXML
	Label messageLabel;
	@FXML
	Label inCartLabel;
	
	int quantityAlreadyInCart;
		
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
			goToView(MainController.backwardView);
		}
		
		else if( rightBtn == e.getSource()) {
			forwardTrick();
			goToView(MainController.forwardView);
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
		
		else if( addToCartBtn == e.getSource()) {
			
			Item item = MainController.curItem;
			Cart cart = MainController.cart;
			
			int quantityWanted = Integer.parseInt(quantityTextField.getText());
			
			// if the item is not in the cart
			if( false == cart.hasItem(item.getID()) ) {
				
				// item is successfully added to the cart
				if( quantityWanted <= item.getQuantity() ) {
					
					cart.getItemList().add(item.cartItem(quantityWanted));
					goToView("../View/Search.fxml");
				}
				
				// item is not added to the cart
				else {
					messageLabel.setText("Unfortunately, there are only "+item.getQuantity()+" in stock.");
				}
			}
			
			// if the item is already in the cart
			else {
				
				// item is successfully added to the cart
				if( quantityWanted + quantityAlreadyInCart <= item.getQuantity() ) {
					
					System.out.println(quantityWanted +" + "+ quantityAlreadyInCart + " <= "+ item.getQuantity());
					
					cart.updateItemQuantity(item.getID(), quantityWanted);
					goToView("../View/Search.fxml");
				}
				
				// item is not added to the cart
				else {
					messageLabel.setText("Unfortunately, there are only "+item.getQuantity()+" in stock.");
				}
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
		
		System.out.print("Switched Views!");
		displayItem();
		setUpNavigationBar();
		
	    quantityAlreadyInCart = 0;
		if( MainController.cart.hasItem(MainController.curItem.getID()) ){
			
			quantityAlreadyInCart = MainController.cart.getItemQuantity(MainController.curItem.getID());
			inCartLabel.setText("You already have "+quantityAlreadyInCart+" in your cart.");
		}
		
		System.out.println(MainController.cart.toString());
	}
	
	public void displayItem() {
		
		Item i = MainController.curItem;
		System.out.println("Item name is: "+i.getName());
		itemNameLabel.setText(i.getName());
		itemPriceLabel.setText("$"+i.getPrice());
		itemImageView.setImage(new Image(getClass().getResourceAsStream("../Images/Food/"+i.getName()+".png"), 3000, 3000, true, true));
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