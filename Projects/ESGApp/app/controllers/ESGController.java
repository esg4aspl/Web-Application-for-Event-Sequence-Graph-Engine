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
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import DataAccessLayer.DataBaseConnection;
import DataAccessLayer.DataFileOperation;
import models.ESG;
import play.mvc.Controller;
import play.mvc.Result;

public class ESGController extends Controller{
	///
	//TODO send responsejson file as a string to turkan
	//TODO smthing
	private DataBaseConnection dbConnection=new DataBaseConnection();
	private DataFileOperation fileOperation=new DataFileOperation();
	private List<ESG> esgList=new ArrayList<>();
	private List<String> graphList;
	private ArrayList<ESG> ESGHistory = new ArrayList<ESG>();
	//Turkanin requestinden gelcek olan esgyi kaydet eventindan sonraki json
	public ESG requestESGFromUI() throws JsonParseException, JsonMappingException, IOException
	{
		String rootJson=request().body().asText();
		ESG esg = new ObjectMapper().readValue(rootJson, ESG.class) ;
		return esg;
	}
	public Result saveESGToMongoDB() throws JsonParseException, JsonMappingException, IOException
	{
		ESG esg	= requestESGFromUI();
		BasicDBObject basicDBObject = new BasicDBObject("name", esg.getName());          
		DBCollection dbCollection = dbConnection.connectDB();   
		basicDBObject.put("id", esg.getId());
		basicDBObject.put("name", esg.getName());
		basicDBObject.put("xmlVersion", esg.getXmlVersion());
		basicDBObject.put("vertices", esg.getObjectVertices());
		basicDBObject.put("edges", esg.getObjectEdges());
		dbCollection.save(basicDBObject); 
		return ok("Inserted");
	}

	public Result readDataFromMongoDB() throws org.codehaus.jackson.JsonParseException, org.codehaus.jackson.map.JsonMappingException, IOException 
	{
		String esgDocument = "";
		DBCollection collection= dbConnection.connectDB();
		DBCursor cursor= collection.find();
		while(cursor.hasNext())
		{
			esgDocument=cursor.next().toString();
			ESG esg = new ObjectMapper().readValue(esgDocument, ESG.class); 
			esgList.add(esg);
		}

		return ok(esgDocument+"");
	}
	public Result sendESGNamesToUI() throws org.codehaus.jackson.JsonParseException, org.codehaus.jackson.map.JsonMappingException, IOException
	{
		String esgName="";
		readDataFromMongoDB();
		for(ESG e: esgList)
		{
			esgName += /*e.getId()+*/ " " + e.getName() + "\n";
		}
		return ok(esgName);
	}

	public Result requestedESGByNameFromUI() throws org.codehaus.jackson.JsonParseException, org.codehaus.jackson.map.JsonMappingException, IOException
	{
		String esgName=request().body().asText();
		String esgDate="";
		readDataFromMongoDB();
		for(ESG e:esgList)
		{
			if (e.getName().equals(esgName)) 
			{
				esgDate=e.getId();
				return ok(e.toString());
			}
		}
		return ok("Requested esg name doesn't match in db");
	}

	public Result findNamesWithQueryMongoDB()
	{
		String esgName=request().body().asText();
		BasicDBObject searchByNameQuery = new BasicDBObject();

		searchByNameQuery.put("name", esgName);
		DBCursor cursor = dbConnection.connectDB().find(searchByNameQuery);

		while(cursor.hasNext()) {

			ESG esg;
			try {
				esg = new ObjectMapper().readValue(cursor.next().toString(), ESG.class);
				ESGHistory.add(esg);
			} catch (IOException e) {
				e.printStackTrace();
			} 

		}
		String s="";
		for(ESG e: ESGHistory)
		{
			s+=e.toString()+"\n";
		}
		return ok(s);
	}

	public Result findCurrent() throws ParseException
	{
		//String esgName=request().body().asText();
		findNamesWithQueryMongoDB();
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
