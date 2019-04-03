package controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import dataAccessLayer.DBConnections;
import dataAccessLayer.DBOperations;
import models.ESG;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class ESGController extends Controller{
	protected DBOperations dbOperation = new DBOperations();
	protected DBConnections db = new DBConnections();
	private List<ESG> esgList=new ArrayList<>();
	private ArrayList<ESG> ESGHistory = new ArrayList<ESG>();
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

			return ok(compareESGName(esg.getName())+"");

		} catch (Exception e) {
			return ok(e.getMessage());
		}
	}

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

	public Result openAll() throws ParseException, org.codehaus.jackson.JsonParseException, org.codehaus.jackson.map.JsonMappingException, IOException
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

	public Result showHistoryByESGName() throws JsonParseException, JsonMappingException, IOException
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

	public Result openESGByName() throws JsonParseException, JsonMappingException, IOException, ParseException
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
	public Result searchByName()
	{

		return ok("");
	}
}
