package Model;

import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    
    	double total = 0;
    	
    	for(Item i: itemList) {
    		
    		total += i.getQuantity() * i.getPrice();
    	}
    	
    	return total;
    }

    public void setTotalCost(double totalCost) {
      this.totalCost = totalCost;
    }
    
	// returns true if the current item is in the cart
	public boolean hasItem(String wantedID) {
		
		for(Item i: itemList) {
			
			if( i.getID() == wantedID) {
				return true;
			}
		}
		
		return false;
	}
	
	// returns the quantity of the item that are in the cart
	public int getItemQuantity(String wantedID) {
		
		for(Item i: itemList) {
			
			if( i.getID() == wantedID) {
				return i.getQuantity();
			}
		}
		
		return 0;
	}
	
    public void addItem(Item itemToAdd) {
    	
    	itemList.add(itemToAdd);
    }
    
    public void removeItem(String removeID) {
    	Iterator<Item> iter = itemList.iterator();
    	
    	while (iter.hasNext()) {
			Item temp = iter.next();
    		if( temp.getID() == removeID) {
    			iter.remove();
    		}
		}
    }
    
    public void updateItemQuantity(String wantedID, int increaseInQuantity) {

		for(Item i: itemList) {
			
			if( i.getID() == wantedID) {
				
				i.setQuantity(increaseInQuantity);
				return;
			}
		}
    }
    
    public ObservableList<Item> getItemTableList(){
		ObservableList<Item> tableList = FXCollections.observableArrayList();
			
		for(int i = 0; i < this.getItemList().size(); i++) {
			tableList.add(itemList.get(i));
		}
		
		return tableList;
	}
    
    public String toString() {
    	
    	String s = "";
    	
    	s += "\n--- CART ---\n";
    	
    	for( Item i: itemList) {
    		s += i.getName() + ": " + i.getQuantity() + "\n";
    	}
    	
    	s += "\n";
    	
    	return s;
    }
    
    public void writeToDb() {
    	// preparedstatement = adfasdfjs("insert into table (name, email ...) values(?, ?, ? ...")
        // preparedstatement.insertString(1, name)
    }
}