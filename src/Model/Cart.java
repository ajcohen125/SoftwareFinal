package Model;

import java.util.*;

public class Cart {

    public ArrayList<Item> itemList;
    public double totalCost;

    public Cart(){

        this.itemList = new ArrayList<Item>();
        totalCost = 0;
    }

    public ArrayList<Item> getItemList() {
      return itemList;
    }

    public void setItemList(ArrayList<Item> itemList) {
      this.itemList = itemList;
    }

    public double getTotalCost() {
      return totalCost;
    }

    public void setTotalCost(double totalCost) {
      this.totalCost = totalCost;
    }
    
    public void addItem(int ID) {
    	
    	// TODO
    	// TODO
    	// TODO
    	// TODO
    	// TODO
    	
    }
    
    public void removeItem(int ID) {
    	
    	// TODO
    	// TODO
    	// TODO
    	// TODO
    	// TODO
    	
    }
    
    public void updateItemQuantity(int ID, int newQuantity) {
    	
    	// TODO
    	// TODO
    	// TODO
    	// TODO
    	// TODO
    	
    }
    
    public String toString() {
    	
    	String s = "";
    	
    	// s += "Thing: "+value
    	// s += "Thing: "+value
    	
    	return s;
    }
    
    public void writeToDb() {
    	// preparedstatement = adfasdfjs("insert into table (name, email ...) values(?, ?, ? ...")
        // preparedstatement.insertString(1, name)
    }
}