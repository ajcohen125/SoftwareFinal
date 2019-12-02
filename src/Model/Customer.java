package Model;

public class Customer{

    public String name;
    public String email;
    public String password;
    public String address;
    public Payment payment;
    public double credit;


    public Customer(String name, String email, String pw, String addr, Payment p, double cred){
        this.name = name;
        this.email = email;
        this.password = pw;
        this.address = addr;
        this.payment = p;
        this.credit = cred;

    }

    public void writeToDb(){
        preparedstatement = adfasdfjs("insert into table (name, email ...) values(?, ?, ? ...")

                preparedstatement.insertString(1, name)

    }

    public void changePw(String newPw){

    }

    public void printCustomer(){
        System.out.printf("Name: %s", this.name);
    }





}