package test;

import java.awt.image.BufferedImage;

import javax.script.ScriptEngine;
import javax.script.ScriptException;


import arvasis.drawing.GraphicsIO;

public class EngineTest {
	public static void main(String[] args) {
		
	/*	BufferedImage img = null;
		try {
			img = GraphicsIO.readBufferedImage("C:\\Users\\Arvasis\\Documents\\KT\\img\\642969956309746.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
		ScriptEngine engine=new NashornScriptEngineFactory().getScriptEngine();
		try {
			engine.eval("print('a')");
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		System.out.println(engine);*/
		// 1. example
	/*	  Context cx = Context.enter();
		  String script = "function abc(x,y) {return x+y;}";
		    Context context = Context.enter();
		    try {
		        ScriptableObject scope = context.initStandardObjects();
		        Scriptable that = context.newObject(scope);
		        Function fct = context.compileFunction(scope, script, "script", 1, null);
		        Object result = fct.call(context, scope, that, new Object[] { 2, 3 });
		        System.out.println(Context.jsToJava(result, int.class));
		    } 
		    finally {
		        Context.exit();
		    }
		    */
		//2. example
		 /*   script = "3 + 2 * (4*5)";
		    context = Context.enter();

		    try{
		        Scriptable scope = context.initStandardObjects();
		        Object result = context.evaluateString(scope, script, "<cmd>", 1, null);
		        System.out.println(result);
		    }
		    finally{
		        Context.exit();
		    }*/
	
		
	}

}
