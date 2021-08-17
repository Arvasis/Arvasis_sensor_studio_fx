package scriptEngine;

import javax.script.ScriptEngine;


import arvasis.script.ArvasisScriptEngine;

public class ArvasisJavaScriptEngine2 extends ArvasisScriptEngine {
	
	protected String script, variables="";
	private ScriptEngine engine;

	public ArvasisJavaScriptEngine2(){
		//this.engine = new NashornScriptEngineFactory().getScriptEngine();
	}
	@Override
	public Object getVar(String varName) {
		return this.engine.get(varName);

	}

	@Override
	public void initVar(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void putVar(String varName, Object val) {
		// TODO Auto-generated method stub
		if(this.variables.equals(""))
			this.variables += "var " + varName + "=" + val + ";";
		else
			this.variables += "\r\n" + varName + "=" + val + ";";		
	}

	@Override
	public void runScript(String arg0) throws Exception {
		this.script = script;
		engine.eval(this.variables + "\r\n" + this.script);
	}

}
