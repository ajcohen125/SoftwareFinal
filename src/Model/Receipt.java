package Model;

import java.util.*;

import Database.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Receipt {

    public String email;
    public int receiptNum;
    public ArrayList<Item> itemList;
    public double totalCost;
    public String shipping;
    public double creditUsed;

	public Receipt(){

        this.email = null;
        this.receiptNum = -1;
        this.itemList = new ArrayList<Item>();
        this.totalCost = 0;
    }

    public Receipt(String email, int receiptNum, ArrayList<Item>itemList, double totalCost, String shipping, double credit){

        this.email = email;
        this.receiptNum = receiptNum;
        this.itemList = itemList;
        this.totalCost = totalCost;
        this.shipping = shipping;
        this.creditUsed = credit;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public int getReceiptNum() {
      return receiptNum;
    }

    public void setReceiptNum(int receiptNum) {
      this.receiptNum = receiptNum;
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
    
    public String toString() {
    	
    	String s = "";
    	
    	// s += "Thing: "+value
    	// s += "Thing: "+value
    	
    	return s;
    }
    
    public double getCreditUsed() {
		return creditUsed;
	}

	public void setCreditUsed(double creditUsed) {
		this.creditUsed = creditUsed;
	}
	
    public String marshall() {
    	String str ="";
    	
    	for (Item i: this.itemList) {
    		String id = i.ID;
    		String name = i.name;
    		double price = i.price;
    		int quant = i.quantity;
    		
    		str += id + "," + name + "," +  price + "," + quant + ":";
    	}
    	return str;
    }
    
    public static ArrayList<Item> unmarshall(String str) {
    	Item temp = new Item();
    	ArrayList<Item> items = new ArrayList<Item>();
    	
    	String[] strSplit = str.split(":");
    	
    	for(String i: strSplit) {
    		String[] iSplit = i.split(",");
    		
    		temp.ID = iSplit[0];
    		temp.name = iSplit[1];
    		temp.price = Double.parseDouble(iSplit[2]);
    		temp.quantity = Integer.parseInt(iSplit[4]);
    		
    		items.add(temp);
    	}
    	
    	return items;
    }
    
    public void writeToDb() {
    	String sql = "";
    	String marshalled = marshall();
    	
    	sql = "INSERT INTO Receipt (email, receiptNum, itemList, totalCost, shipping, creditUsed) VALUES ("
    			+ "'" + this.email + "', "
    			+ "'" + this.receiptNum + "', "
    			+ "'" + marshalled + "', "
    			+ this.totalCost + ", "
    			+ "'" + this.shipping + "', "
    			+ this.creditUsed + ");";
    	
    	int r = DataBase.update(sql);
    	
    	if (r == -2) {
    		System.out.println("Could not add reciept");
    	}
    	else {
    		System.out.println("Added reciept");
    	}
    }
    
    public ObservableList<Item> getAssignmentTableList(){
		ObservableList<Item> tableList = FXCollections.observableArrayList();
			
		for(int i = 0; i < this.getItemList().size(); i++) {
			tableList.add(itemList.get(i));
		}
		
		return tableList;
	}
}
