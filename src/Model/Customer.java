package Model;

import java.sql.SQLException;
import java.util.ArrayList;

import Database.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Customer {

    public String name;
    public String email;
    public String password;
    public String address;
    public Payment payment;
    public double credit;
    public ArrayList<Receipt> receiptList;

    public Customer(){

        this.name = null;
        this.email = null;
        this.password = null;
        this.address = null;
        this.payment = null;
        this.credit = 0;
        receiptList = new ArrayList<Receipt>();
    }

    // If an argument was not provided, pass in null when creating an instance of Customer
    public Customer(String name, String email, String password, String address, Payment payment, double credit){

        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.payment = payment;
        this.credit = credit;
        receiptList = new ArrayList<Receipt>();
    }
    
    // String ccNum, String expDate, String name, String CVV, String address
    public static Customer dummyCustomer() {
    	
    	Payment p = new Payment("111122223333", "12-34", "John A Smith", "123", "123 Nunya Biz");
    	Customer c = new Customer("John Smith", "johnsmith@gmail.com", "drowssap", "UTSA blvd", p, 10);
    	return c;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }

    public String getAddress() {
      return address;
    }

    public void setAddress(String address) {
      this.address = address;
    }

    public Payment getPayment() {
      return payment;
    }

    public void setPayment(Payment payment) {
      this.payment = payment;
    }

    public double getCredit() {
      return credit;
    }

    public void setCredit(double credit) {
      this.credit = credit;
    }
    
    public ArrayList<Receipt> getReceiptList() {
        return receiptList;
      }

    public void setReceiptList(ArrayList<Receipt> receiptList) {
        this.receiptList = receiptList;
      }
    
    public String toString() {
    	
    	String s = "";
    	
    	// s += "Thing: "+value
    	// s += "Thing: "+value
    	
    	return s;
    }
    
    public void writeToDb() {
    	String sql = "INSERT INTO Customer (name, email, password, address) VALUES ("
    			+ "'" + this.name + "', "
    			+ "'" + this.email + "', "
    			+ "'" + this.password + "', "
    			+ "'" + this.address + "');";
    	
    	int r = DataBase.update(sql);
    	
    	if (r == -2) {
    		System.out.println("Did not write Cusomter to database");
    	}
    	else {
    		System.out.println("Wrote customer to database");
    	}
    	
    	this.payment.writeToDb();
    }
    
    public void updateDB() {
    	String sql = "UPDATE Customer SET "
    			+ "name='" + this.name +"', "
    			+ "email='" + this.email +"', "
    			+ "password='" + this.password + "', "
    			+ "address='" + this.address + "', "
    			+ "credit=" + this.credit + " "
    			+ "WHERE name='" + this.name + "';";
    	
    	
    	int r = DataBase.update(sql);
    	
    	if (r == -2) {
    		System.out.println("Did not update customer in database");
    	}
    	else {
    		System.out.println("Updated customer in database");
    	}
    }
    
    public void getPaymentFromDB() {
    	Payment p = new Payment();
    	String sql= "SELECT * from Payment WHERE name='" + this.name +"';";
		
    	java.sql.ResultSet r = DataBase.select(sql);
    	
    	try {
			while(r.next()) {
				p.ccNum = r.getString(1);
				p.expDate = r.getString(2);
				p.name = r.getString(3);
				p.CVV = r.getString(4);
				p.address = r.getString(5);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setPayment(p);
    }
    
    public void getReceiptListFromDB() {
    	String sql = "SELECT * FROM Receipt WHERE email ='" + this.email + "';";
    	ArrayList<Receipt> tempList = new ArrayList<Receipt>();
    	
    	java.sql.ResultSet r = DataBase.select(sql);
    	
    	try {
			while(r.next()){
				Receipt temp = new Receipt();
				temp.email = r.getString(1);
				temp.receiptNum = r.getInt(2);
				temp.itemList = Receipt.unmarshall(r.getString(3));
				temp.totalCost = r.getDouble(4);
				temp.shipping = r.getString(5);
				temp.creditUsed = r.getDouble(6);
				
				tempList.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		setReceiptList(tempList);
    }
    
    public ObservableList<Receipt> getReceiptTableList(){
		ObservableList<Receipt> tableList = FXCollections.observableArrayList();
			
		for(int i = 0; i < this.getReceiptList().size(); i++) {
			tableList.add(receiptList.get(i));
		}
		
		return tableList;
	}
}
