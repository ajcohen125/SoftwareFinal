package Model;

public class Customer {

    public String name;
    public String email;
    public String password;
    public String address;
    public Payment payment;
    public double credit;

    public Customer(){

        this.name = null;
        this.email = null;
        this.password = null;
        this.address = null;
        this.payment = null;
        this.credit = 0;
    }

    // If an argument was not provided, pass in null when creating an instance of Customer
    public Customer(String name, String email, String password, String address, Payment payment, double credit){

        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.payment = payment;
        this.credit = credit;
    }
    
    // String ccNum, String expDate, String name, String CVV, String address
    public static Customer dummyCustomer() {
    	
    	Payment p = new Payment("111122223333", "12/34", "John A Smith", "123", "123 Nunya Biz");
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

// System.out.print("Name: %s", this.name);