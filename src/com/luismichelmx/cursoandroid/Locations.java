package com.luismichelmx.cursoandroid;

public class Locations {

	private int icon;
	private String location;
	private String city;
	
	public Locations( int icon, String location, String city){
		
		this.setIcon(icon);
		this.setLocation(location);
		this.setCity(city);
		
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}
