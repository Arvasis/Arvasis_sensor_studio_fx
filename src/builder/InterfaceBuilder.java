package builder;

import java.awt.image.BufferedImage;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;

import globals.Globals;
import interfacebuilder.FiltersFrame;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class InterfaceBuilder {

	private Class cls;
	private ObservableList<String> functionNames;
//	private String[] functionNameAsArray;
	// private Method[] methods;
	private ArrayList<Method> methods = new ArrayList<Method>();
	private GridPane content;

	public InterfaceBuilder(Class cls) {
		this.cls = cls;
		// this.methods = cls.getMethods();
		functionNames = FXCollections.observableArrayList();
		setFunctionsStartsWithApply(cls);
		// functionNameAsArray=new String[methods.length];
	}

	public void setFunctionsStartsWithApply(Class cls) {
		Method[] allMethods = cls.getMethods();
		// methods=new Method[allMethods.length];
		
		int i = 0;
		for (Method method : allMethods) {
			if (method.getName().startsWith("apply")) {
				methods.add(method);
				functionNames.add(method.getName());
				i++;
			}
		}
	}

	/*
	 * public String[] getFunctionsNameAsArray() {
	 * 
	 * int i = 0; for (Method method : cls.getMethods()) {
	 * functionNameAsArray[i]=method.getName(); i++; } return functionNameAsArray; }
	 */
	public ObservableList<String> getFunctionsName() {
		return functionNames;
	}

	public ObservableList<String> getFunctionsName(Class cls) {

		int i = 0;
		for (Method method : cls.getMethods()) {
			functionNames.add(method.getName());
			i++;
		}
		return functionNames;
	}

	public void buildFrame(Method method) {
		String text = method.getName();
		String cleanText = text.replaceAll("\\d+", "").replaceAll("(.)([A-Z])", "$1 $2");
		cleanText = cleanText.substring(0, 1).toUpperCase() + cleanText.substring(1);
		FiltersFrame frame = new FiltersFrame(cleanText) {

			@Override
			public void btnApplyAddMouseListener() {
				btnApply.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						try {
							selectedMethod=method;
							processString=getFunctionString(method);
							
							//Object image=Globals.mainController.getImage();
							Object image=Globals.tree.getImageForProcess();
							Object img=Globals.runScript(image,processString);
							Globals.mainController.setImage(img);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		};

		content = buildFrameContent(method);
		if (content != null) {
			frame.setContentPane(content);
			frame.getBtnApply().setTranslateY(content.getPrefHeight());

		}

	}

	public GridPane buildFrameContent(Method method) {
		GridPane grd = null;
		Parameter[] parameters = method.getParameters();
		if (parameters.length > 1) {
			grd = new GridPane();
			int i = -1, j = 0;
			for (Parameter param : method.getParameters()) {

				if (i == -1) {
					i++;
					continue;
				}
				String arg = "arg" + i;
				grd.add(new Label(param.getName() + ":"), 0, i);

				if (param.getType() == int.class || param.getType() == Integer.class) {
					Spinner<Integer> sp = new Spinner<Integer>(0, 255, 0, 1);
					sp.setUserData(arg);
					sp.setEditable(true);
					grd.add(sp, 1, i);

				}
				if (param.getType() == String.class) {
					TextField field = new TextField();
					field.setUserData(arg);
					grd.add(field, 1, i);
				}
				if (param.getType() == float.class || param.getType() == Float.class) {
					Spinner<Float> sp = new Spinner<Float>(0.0, 255.0, 0.0, 0.1);
					sp.setUserData(arg);
					sp.setEditable(true);
					grd.add(sp, 1, i);
				}
				if (param.getType() == double.class || param.getType() == Double.class) {
					Spinner<Double> sp = new Spinner<Double>(0.0, 255.0, 0.0, 0.1);
					sp.setEditable(true);
					sp.setUserData(arg);
					grd.add(sp, 1, i);
				}
				if (param.getType() == boolean.class || param.getType() == Boolean.class) {
					CheckBox chbox = new CheckBox();
					chbox.setUserData(arg);
					grd.add(chbox, 1, i);
				}
				i++;
			}
			grd.setPrefWidth(400);
			grd.setPrefHeight(parameters.length * 25);
			grd.setHgap(50);
			grd.setVgap(10);
			grd.setPadding(new Insets(10, 10, 10, 10));
		}

		return grd;
	}

	public String getFunctionString(Method func) {

		String processString = "image=Packages." + cls.getName() + "." + func.getName() + "(";
		for (Parameter param : func.getParameters()) {
			if (param.getType() == BufferedImage.class || param.getType() == int[][].class
					|| param.getType() == boolean[][].class) {
				processString += "image,";
			}
		}
		if (content != null) {

			for (Node node : content.getChildren()) {
				String val = null;
				if (node.getUserData() != null) {
					if (node.getClass().toString().contentEquals("class javafx.scene.control.TextField")) {
						TextField t = (TextField) node;
						val = t.getText();
					}
					if (node.getClass().toString().contentEquals("class javafx.scene.control.Spinner")) {

						Spinner sp = (Spinner) node;
						val = "" + sp.getValue();
					}
					if (node.getClass().toString().contentEquals("class javafx.scene.control.CheckBox")) {
						CheckBox t = (CheckBox) node;
						val = "" + t.isSelected();
					}

					if (node.getClass().toString().contentEquals("class javafx.scene.control.Slider")) {
						Slider s = (Slider) node;
						val = "" + s.getValue();
					}
					processString += val + ",";
				}

			}
		}
		processString = processString.substring(0, processString.length() - 1);
		processString += ");";
		return processString;
	}

	public Method getMethodFromIndex(int index) {
		return methods.get(index);
	}

	public ArrayList<Method> getMethods() {
		return methods;
	}

	public void setMethods(ArrayList<Method> methods) {
		this.methods = methods;
	}

}
