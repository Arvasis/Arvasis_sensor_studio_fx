package controller.identification;

import javafx.beans.property.SimpleStringProperty;

public class Pixel {
	
private SimpleStringProperty colX;
private SimpleStringProperty colY;
	
	public Pixel(String x,String y) {
		this.colX = new SimpleStringProperty(x);
		this.colY = new SimpleStringProperty(y);
	}
	

	public String getColX() {
		return colX.get();
	}

	public void setColX(String x) {
		this.colX= new SimpleStringProperty(x);
	}
	
	public String getColY() {
		return colY.get();
	}

	public void setColY(String y) {
		this.colY= new SimpleStringProperty(y);
	}

}
