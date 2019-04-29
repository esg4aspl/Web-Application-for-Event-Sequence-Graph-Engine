package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.mvc.*;
import play.mvc.BodyParser;
import play.mvc.Result;
import play.libs.Json;

public class PersonController extends Controller{
	private StudentController st=new StudentController();
	/* burcu@burcu-X556UQK:~/workspace/jsonPlay$ 
	 * curl --header "Content-type: application/json" --request POST --data '{"name": "Guillaume"}' http://localhost:9000/sayHello            
	public void jsonRepresentationOrParsing()
	{
		//Json representation
		Person person= new Person("Foo","Bar", 30);
		JsonNode personJson=Json.toJson(person);
		
		//parse the JSON as a JsonNode
		JsonNode json=Json.parse("{\"firstName\":\"Foo\", \"lastName\":\"Bar\", \"age\":13}");
		Person person1=Json.fromJson(json, Person.class);

	}
	*/
	public Result sendJSON() throws Exception
	{
		JsonNode rootJson=request().body().asJson();
		st.dbConnection(rootJson);
		return ok("saved to mongoDB");
	}
	
	
	
	
	public Result sayHello() {
	    JsonNode json = request().body().asJson();
	    if(json == null) {
	        return badRequest("Expecting Json data");
	    } else {
	        
	        String name = json.findPath("name").textValue();
	        if(name == null) {
	            return badRequest("Missing parameter [name]");
	        } else {
	        	st.writeDataToJSON(json.toString());
	            return ok("it is done");
	        }
	    }
	}
	
	@BodyParser.Of(BodyParser.Json.class)////
 	public Result sayHelloWithBodyParser() throws Exception
	{
		
		
		JsonNode json= request().body().asJson();
		//String s=request().body().;
		String name=json.findPath("name").textValue();
		if(name==null)
		{
			return badRequest("Missing parameter [name]");
		}else {
			return ok("Hello" + name);
		}
	}
	
	public Result sayHelloWithTestPlain()
	{
		ObjectNode result= Json.newObject();
		result.put("exampleField1","foobar");
		result.put("exampleField2","Hello World!");
		return ok(result);
	}
}

