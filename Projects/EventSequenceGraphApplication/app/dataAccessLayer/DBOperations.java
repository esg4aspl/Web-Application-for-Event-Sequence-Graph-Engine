package dataAccessLayer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import models.ESG;

public class DBOperations {
	protected static DBConnections db = new DBConnections();

	public void saveESG(ESG esg) throws ParseException
	{
		ArrayList<ESG> ESGHistory = getESGHistoryList(esg.getName());
		if ( ESGHistory.size() < 10) 
		{

			saveESGToMongoDB(esg);
		}
		else
		{
			deleteESGFromMongoDB(getOldestESG(esg.getName()));
			saveESGToMongoDB(esg);
		}
	}
	
	public void saveNewESG(ESG esg) throws ParseException, JsonParseException, JsonMappingException, IOException
	{
		saveESGToMongoDB(esg);
	}
	
	/*public void saveNewESGInHistory(ESG esg) throws ParseException, JsonParseException, JsonMappingException, IOException
	{
		esg.setId(getNewestESG().getId()+1);
		saveESGToMongoDB(esg);
	}*/
	
	public void saveESGToMongoDB(ESG esg)
	{
		DBCollection dbCollection = db.collection();
		BasicDBObject basicDBObject = new BasicDBObject();          
		basicDBObject.put("id", esg.getId());
		basicDBObject.put("dateTime", esg.getDateTime());
		basicDBObject.put("type", esg.getType());
		basicDBObject.put("typeName", esg.getTypeName());
		basicDBObject.put("name", esg.getName());
		basicDBObject.put("xmlVersion", esg.getXmlVersion());
		basicDBObject.put("vertices", esg.getObjectVertices());
		basicDBObject.put("edges", esg.getObjectEdges());
		basicDBObject.put("generatedTestCases", esg.getGeneratedTestCases());

		dbCollection.save(basicDBObject); 
	}

	public void deleteESGFromMongoDB(ESG oldestesg)
	{
		BasicDBObject searchByNameQuery = new BasicDBObject();
		searchByNameQuery.put("name", oldestesg.getName());
		db.collection().remove(searchByNameQuery.append("id", oldestesg.getId()));

	}

	public ESG findESGByName(String esgName) throws ParseException
	{
		BasicDBObject searchByNameQuery = new BasicDBObject();
		searchByNameQuery.put("name", esgName);
		DBCursor cursor = db.collection().find(searchByNameQuery);
		ESG eSG = getOldestESG(esgName);
		return eSG;
	}

	public ArrayList<ESG> getESGHistoryList(String esgName)
	{
		ArrayList<ESG> ESGHistory =  new ArrayList<>();
		BasicDBObject searchByNameQuery = new BasicDBObject();
		searchByNameQuery.put("name", esgName);
		DBCursor cursor = db.collection().find(searchByNameQuery).sort(new BasicDBObject("_id",1)).limit(100);

		while(cursor.hasNext()) 
		{
			try 
			{
				ESG esg = new ObjectMapper().readValue(cursor.next().toString(), ESG.class);
				ESGHistory.add(esg);
			} catch (IOException e) 
			{
				e.printStackTrace();
			} 
		}
		return ESGHistory;
	}

	public ArrayList<ESG> getAllESGList() throws org.codehaus.jackson.JsonParseException, org.codehaus.jackson.map.JsonMappingException, IOException 
	{
		String esgDocument = "";
		ArrayList<ESG> ESGList = new ArrayList<>();
		DBCursor cursor= db.collection().find().sort(new BasicDBObject("_id",1)).limit(100);
		while(cursor.hasNext())
		{
			ESG esg = new ObjectMapper().readValue(cursor.next().toString(), ESG.class); 
			ESGList.add(esg);
		}

		return ESGList;
	}

	public ESG getOldestESG(String esgName) throws ParseException
	{
		ArrayList<ESG> ESGHistory = getESGHistoryList(esgName);
		ESG esgOldest = ESGHistory.get(0);
		return esgOldest;
	}
	
	public ESG getNewestESG() throws ParseException, JsonParseException, JsonMappingException, IOException
	{
		ArrayList<ESG> ESGHistory = getAllESGList();
		ESG esgNewest = ESGHistory.get(ESGHistory.size()-1);
		return esgNewest;
	}

	public boolean isCurrent(String d1, String d2) throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		Date date1 = sdf.parse(d1);
		Date date2 = sdf.parse(d2);
		boolean bool = false;

		if (date1.compareTo(date2) > 0) {
			bool = true;
		} else if (date1.compareTo(date2) < 0) {
			bool = false;
		} 
		return bool;
	}
}