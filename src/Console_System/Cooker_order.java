package Console_System;

public class Cooker_order {
		private String name;
		private String food;
	    private int schedule;

	 public Cooker_order(String name,String food, int schedule) {
	        this.name = name;
	        this.schedule = schedule;
	        this.food = food;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public int getschedule() {
	        return schedule;
	    }
	    public void setschedule(int schedule) {
	        this.schedule = schedule;
	    }
	    
	    public void setfood(String food) {
	        this.food = food;
	    }
	    public String getfood() {
	        return food;
	    }

	    
	    @Override
	    public String toString() { 
	        return name; 
	    }    
}
