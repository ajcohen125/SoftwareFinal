package Model;

import java.util.*;

public class Receipt {

    public String email;
    public int receiptNum;
    public ArrayList<Item> itemList;
    public double totalCost;

    public Receipt(){

        this.email = null;
        this.receiptNum = -1;
        this.itemList = new ArrayList<Item>();
        this.totalCost = 0;
    }

    public Receipt(String email, int receiptNum, ArrayList<Item>itemList, double totalCost){

        this.email = email;
        this.receiptNum = receiptNum;
        this.itemList = itemList;
        this.totalCost = totalCost;
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
    
    public void writeToDb() {
    	// preparedstatement = adfasdfjs("insert into table (name, email ...) values(?, ?, ? ...")
        // preparedstatement.insertString(1, name)
    }
}
