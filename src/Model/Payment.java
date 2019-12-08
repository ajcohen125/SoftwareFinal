package Model;

import Database.DataBase;

public class Payment {

    public String ccNum;
    public String expDate;
    public String name;
    public String CVV;
    public String address;

    public Payment() {
    	this.ccNum = null;
    	this.expDate = null;
    	this.name = null;
    	this.CVV = null;
    	this.address = null;
    }
    
    public Payment(String ccNum, String expDate, String name, String CVV, String address){

        this.ccNum = ccNum;
        this.expDate = expDate;
        this.name = name;
        this.CVV = CVV;
        this.address = address;
    }

    public String getCcNum() {
      return ccNum;
    }

    public void setCcNum(String ccNum) {
      this.ccNum = ccNum;
    }

    public String getExpDate() {
      return expDate;
    }

    public void setExpDate(String expDate) {
      this.expDate = expDate;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getCVV() {
      return CVV;
    }

    public void setCVV(String CVV) {
      this.CVV = CVV;
    }

    public String getAddress() {
      return address;
    }

    public void setAddress(String address) {
      this.address = address;
    }
    
    public String toString() {
    	
    	String s = "";
    	
    	// s += "Thing: "+value
    	// s += "Thing: "+value
    	
    	return s;
    }
    
    public void writeToDb() {
    	String sql = "INSERT INTO Payment (ccNum, expDate, name, cvv, address) VALUES ("
    			+ "'" + this.ccNum + "', "
    			+ "'" + this.expDate + "', "
    			+ "'" + this.name + "', "
    			+ "'" + this.CVV + "', "
    			+ "'" + this.address + "');";
    	
    	int r = DataBase.update(sql);
    	
    	if (r == -2) {
    		System.out.println("Did not write Payment to database");
    	}
    	else {
    		System.out.println("Wrote Payment to database");
    	}
    }
}