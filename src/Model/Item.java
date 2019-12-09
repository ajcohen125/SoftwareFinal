package Model;

public class Item {

    public String ID;
    public String name;
    public double price;
    public int quantity;
    public String category;
    
    public Item() {
    	this.ID = null;
    	this.name = null;
    	this.price = 0;
    	this.quantity = 0;
    	this.category = null;
    }

    public Item(String ID, String name, double price, int quantity, String category){

        this.ID = ID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }
    
    public Item(String ID, String name, double price, int quantity){

        this.ID = ID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

	// When an Item is added to the cart, the item should have the quantity the user wants to purchase
    // The item should not have the quantity of the inventory
    public Item cartItem(int cartQuantity) {
    	
    	return new Item(this.ID, this.name, this.price, cartQuantity);
    }

    public String getID() {
      return ID;
    }

    public void setID(String ID) {
      this.ID = ID;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public double getPrice() {
      return price;
    }

    public void setPrice(double price) {
      this.price = price;
    }

    public int getQuantity() {
      return quantity;
    }

    public void setQuantity(int quantity) {
      this.quantity = quantity;
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