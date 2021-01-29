package controller.identification;

import javafx.beans.property.SimpleStringProperty;

public class RGB {
	private SimpleStringProperty rgb;
	
	public RGB(String rgb) {
		this.rgb = new SimpleStringProperty(rgb);
	}
	

	public String getRgb() {
		return rgb.get();
	}

	public void setRgb(String rgb) {
		this.rgb= new SimpleStringProperty(rgb);
	}
}
