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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import Model.*;
import java.util.ArrayList;

public class TemplateController implements Initializable, EventHandler<ActionEvent> {
	
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
			goToView("../View/Settings.fxml");
		}
		
		else if( cartBtn == e.getSource()) {

			passVar();
			goToView("../View/Cart.fxml");
		}
		
		if( e.getSource() == leftBtn ) {
			
		}
		
		if( e.getSource() == rightBtn ) {
			
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		System.out.print("Switched Views!");
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