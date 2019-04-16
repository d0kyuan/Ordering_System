package Console_System;

public class Menu {

    private String name;
    private int price;
    private int foodid;
    public Menu(String name, int price,int foodid) {
        this.name = name;
        this.price = price;
        this.foodid = foodid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getprice() {
        return price;
    }

    public void setprice(int price) {
        this.price = price;
    }
    public int getfoodid() {
        return price;
    }

    public void setfoodid(int foodid) {
        this.foodid = foodid;
    }
    @Override
    public String toString() { 
        return name; 
    }    
}
