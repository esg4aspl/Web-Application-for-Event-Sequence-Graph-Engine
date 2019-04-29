package controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dataAccessLayer.DBConnections;
import dataAccessLayer.DBOperations;
import engine.EngineConnection;
import models.ESG;
import models.Edge;
import models.Vertex;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class ESGController extends Controller{
	protected DBOperations dbOperation = new DBOperations();
	protected DBConnections db = new DBConnections();
	private List<ESG> esgList=new ArrayList<>();
	private ArrayList<ESG> ESGHistory = new ArrayList<ESG>();
	private EngineConnection engine = new EngineConnection();
	
	//Generates ESG test cases from the engine
	public Result generateTestCases() throws JsonParseException, JsonMappingException, IOException, ParseException
	{
		ESG esg = requestESGFromUI();
		String esgFile = toStringForEngine(esg);
		JsonObject jsonObject = new JsonParser().parse(esgFile).getAsJsonObject();
		JSONObject json = new JSONObject(jsonObject.toString());
		String testCases = engine.parseJSONObjectForESGSimpleCreation(json);
		esg.setGeneratedTestCases(testCases);
		dbOperation.saveESGToMongoDB(esg);
		
		return ok(toStringForEngine(esg));
	}
	
	// save ESG
	public Result save() throws JsonParseException, JsonMappingException, IOException
	{
		try {
			ESG esg = requestESGFromUI();
			if(compareESGName(esg.getName()))
			{
				this.ESGHistory = dbOperation.getESGHistoryList(esg.getName());
				dbOperation.saveESG(esg);
			}
			else dbOperation.saveESGToMongoDB(esg);

			return ok(esg.getName()+"is saved.");

		} catch (Exception e) {
			return ok(e.getMessage());
		}
	}
	
	// saveas ESG
	public Result saveAs() throws JsonParseException, JsonMappingException, IOException
	{
		try {
			ESG esg = requestESGFromUI();
			esgList = dbOperation.getAllESGList();

			if(!compareESGName(esg.getName()))
			{
				dbOperation.saveESGToMongoDB(esg);
				return ok("it saved to db");
			}
			else return ok(esg.getId()+ " " + esg.getName() + "exist before. You can save in your history or you can save as with different name ");

		} catch (Exception e) {
			return ok(e.getMessage());
		}
	}

	// get exist ESGs
	public Result getExistESGs() throws ParseException, JsonParseException, JsonMappingException, IOException
	{
		String name="";
		this.esgList = dbOperation.getAllESGList();
		ArrayList<String> names = new ArrayList<>();

		for(ESG e : esgList)
		{
			if(!names.contains(e.getName()))
			{
				names.add(e.getName());
				name += e.getName()+"\n";
			}
		}
		return ok(name);
	}

	//get user ESG History
	public Result getHistoryByESGName() throws JsonParseException, JsonMappingException, IOException
	{
		String esgName=request().body().asText();
		String esgIdName="";
		this.ESGHistory = dbOperation.getESGHistoryList(esgName);
		for(ESG e : ESGHistory)
		{
			esgIdName += e.getId()+" " + e.getName() + "\n";
		}
		return ok(esgIdName);
	}

	// search an ESG with its Name
	public Result getESGByName() throws JsonParseException, JsonMappingException, IOException, ParseException
	{
		String esgName=request().body().asText();
		ESG searchingESG = dbOperation.findESGByName(esgName);
		return ok(Json.toJson(searchingESG));
	}



	//UI requestinden gelen stringi ESG objesine convert eder ve onu ESG objesi olarak dondurur.
	public ESG requestESGFromUI() throws JsonParseException, JsonMappingException, IOException
	{
		String rootJson=request().body().asText();
		ESG esg =  new ObjectMapper().readValue(rootJson, ESG.class) ;
		return esg;
	}

	//ESG Name List
	public Result sendESGNamesToUI() throws org.codehaus.jackson.JsonParseException, org.codehaus.jackson.map.JsonMappingException, IOException
	{
		String esgName="";
		this.esgList = dbOperation.getAllESGList();
		for(ESG e: esgList)
		{
			esgName += /*e.getId()+*/ " " + e.getName() + "\n";
		}
		return ok(esgName);
	}

	public boolean compareESGName(String esgName) throws org.codehaus.jackson.JsonParseException, org.codehaus.jackson.map.JsonMappingException, IOException
	{
		boolean bool=false;
		for(ESG e:esgList)
		{
			if (e.getName().equals(esgName)) 
			{
				bool=true;
				return bool;
			}
		}
		return bool;
	}

	public Result findCurrent() throws ParseException
	{
		//String esgName=request().body().asText();
		//findNamesWithQueryMongoDB();
		ESG esgCurrent =ESGHistory.get(0);
		for(int i=1; i<ESGHistory.size();i++)
		{
			if (isCurrent(ESGHistory.get(i).getId(), esgCurrent.getId())) {
				esgCurrent=ESGHistory.get(i);
			}
		}

		return ok(esgCurrent.toString());
	}

	public boolean isCurrent(String d1, String d2) throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		Date date1 = sdf.parse(d1);
		Date date2 = sdf.parse(d2);
		boolean bool = false;

		if (date1.compareTo(date2) > 0) {
			bool = true;
			return bool;
		} else if (date1.compareTo(date2) < 0) {
			return bool;
		} 
		return bool;
	}
	
	//Esg JsonObject structure for Dilek
	public String toStringForEngine(ESG esg) {
		/*{"id":"2019-12-17 04:53:31","name":"esg3","xmlVersion":"","vertices":[{"id":0,"event":"as"}],"edges":[{"id":0,"source":0,"target":1}]}		 */
		String esgString = "{\"id\":"+"\""+esg.getId()+"\""+","+"\"name\":"+"\""+esg.getName()+"\""+","+"\"vertices\":"+"[";
		for(Vertex v: esg.getVertexList())
		{
			esgString+="{"+"\""+"id"+"\""+":"+v.getId()+","+"\"event\":"+"\""+v.getEvent()+"\""+"},";
			/*if (esg.getVertexList().size()>0) {
				esgString += ",";
			}*/
			
		}
		esgString = esgString.substring(0, esgString.length() - 1);
		esgString+="],"+"\"edges\":[{";
		for(Edge e: esg.getEdgeList())
		{
			esgString+="\"id\":"+e.getId()+","+"\"source\":"+e.getSource()+","+"\"target\":"+e.getTarget();
		}
		esgString+="}]}";
		return esgString;
	}
	
	public Result searchByName()
	{

		return ok("");
	}
}