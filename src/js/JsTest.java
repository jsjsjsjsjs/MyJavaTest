package js;
/*
 * java执行js代码(不可调用浏览器函数 如alert())
 */
import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class JsTest {

	public static void main(String[] args) throws Exception {

		ScriptEngineManager manager = new ScriptEngineManager();

		ScriptEngine engine = manager.getEngineByName("js");

		engine.put("a", 1);

		engine.put("b", 5);

		Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);

		Object a = bindings.get("a");

		Object b = bindings.get("b");

		System.out.println("a = " + a);

		System.out.println("b = " + b);

		// 调用函数求和
		Object result = engine.eval("c = a + b;");
		System.out.println("a + b = " + result);
		
		
		// 直接打印
		engine.eval(" print('hello world!') ");
		System.out.println();
		
		// 不能调用浏览器中定义的函数 如alert()
		//engine.eval(" context.alert('alert hello world');");
		
		
		// 传参
		try{
			String name = "jwf";
			String script = " function say(name){ return 'hello, '+name; }";
			engine.eval(script);
			Invocable inv2 = (Invocable)engine;
			String res=(String)inv2.invokeFunction("say",name);
			System.out.println(res);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		

	}
	
	
}
