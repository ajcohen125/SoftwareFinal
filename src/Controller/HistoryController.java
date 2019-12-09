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

public class HistoryController implements Initializable, EventHandler<ActionEvent> {
	
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
	TableView<Receipt> allReceiptsTableView;
	@FXML
	TableColumn<Receipt, Integer> receiptNumberCol;
	@FXML
	TableColumn<Receipt, Double> totalCostCol;
	
	@FXML
	TableView<Item> selectedReceiptTableView;
	@FXML
	TableColumn<Item, String> nameCol;
	@FXML
	TableColumn<Item, String> idCol;
	@FXML
	TableColumn<Item, Double> priceCol;
	@FXML
	TableColumn<Item, Integer> quantityCol;
	@FXML
	TableColumn<Item, Double> itemTotalCol;
	
	@FXML
	Label deliveryLabel;
	@FXML
	Label storeCreditUsedLabel;
	@FXML
	Label totalCostLabel;
	
	// LOCAL
	Receipt selectedReceipt;
	ObservableList<Item> items;
	ObservableList<Receipt> receiptHistory;
		
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
		
		loadReceipts();
		
		// what to do when the table is pressed
		allReceiptsTableView.setOnMousePressed(e -> {
			selectedReceipt = allReceiptsTableView.getSelectionModel().getSelectedItem();
			loadSelectedReceipt();
		});
	}
	
	public void loadReceipts() {
		
		// add options to the combo box
		receiptHistory = FXCollections.observableArrayList();
		receiptHistory = Main.user.getReceiptTableList();
		
		receiptNumberCol.setCellValueFactory(new PropertyValueFactory<Receipt, Integer>("receiptNum"));
		
		totalCostCol.setCellValueFactory(new PropertyValueFactory<Receipt, Double>("totalCost"));
		
		allReceiptsTableView.setItems(receiptHistory);
		allReceiptsTableView.getSelectionModel().select(null);
	}
	
	public void loadSelectedReceipt() {
		
		items = FXCollections.observableArrayList();
		items = selectedReceipt.getItemTableList();
		
		nameCol.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
		
		idCol.setCellValueFactory(new PropertyValueFactory<Item, String>("ID"));	
		
		priceCol.setCellValueFactory(new PropertyValueFactory<Item, Double>("price"));
		
		quantityCol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("quantity"));

		selectedReceiptTableView.setItems(items);
		selectedReceiptTableView.refresh();
		
		deliveryLabel.setText(selectedReceipt.getShipping());
		storeCreditUsedLabel.setText("$ "+selectedReceipt.getCreditUsed());
		totalCostLabel.setText("$ "+selectedReceipt.getTotalCost());
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