package dataAccessLayer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import models.ESG;

public class DBOperations {
	protected static DBConnections db = new DBConnections();
	private ESG oldestESG;
	private ESG esg;
	//protected BasicDBObject basicDBObject;

	public void saveESG(ESG esg) throws ParseException
	{
		ArrayList<ESG> ESGHistory = getESGHistoryList(esg.getName());
		this.oldestESG = getOldestESG(esg.getName());

		if (ESGHistory.size() < 10) {
			saveESGToMongoDB(esg);
		}
		else
		{
			deleteESGFromMongoDB(oldestESG);
			saveESGToMongoDB(esg);
		}
	}

	public void saveESGToMongoDB(ESG esg)
	{
		DBCollection dbCollection = db.collection();
		BasicDBObject basicDBObject = new BasicDBObject();          
		basicDBObject.put("id", esg.getId());
		basicDBObject.put("name", esg.getName());
		basicDBObject.put("xmlVersion", esg.getXmlVersion());
		basicDBObject.put("vertices", esg.getObjectVertices());
		basicDBObject.put("edges", esg.getObjectEdges());
		dbCollection.save(basicDBObject); 
	}

	public void deleteESGFromMongoDB(ESG oldestesg)
	{
		db.collection().remove(new BasicDBObject().append("id", oldestesg.getId()).append("name", oldestesg.getName()));
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
		BasicDBObject searchByNameQuery = new BasicDBObject();
		searchByNameQuery.put("name", esgName);
		DBCursor cursor = db.collection().find(searchByNameQuery);
		ArrayList<ESG> ESGHistory =  new ArrayList<>();
		
		while(cursor.hasNext()) 
		{
			try 
			{
				oldestESG = new ObjectMapper().readValue(cursor.next().toString(), ESG.class);
				ESGHistory.add(oldestESG);
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
		DBCollection collection=db.collection();
		ArrayList<ESG> ESGList = new ArrayList<>();
		DBCursor cursor= collection.find();
		
		while(cursor.hasNext())
		{
			esgDocument=cursor.next().toString();
			ESG esg = new ObjectMapper().readValue(esgDocument, ESG.class); 
			ESGList.add(esg);
		}

		return ESGList;
	}
	public ESG getOldestESG(String esgName) throws ParseException
	{
		ArrayList<ESG> ESGHistory = getESGHistoryList(esgName);
		ESG esgOldest = ESGHistory.get(0);
		for(int i=1; i< ESGHistory.size();i++)
		{
			if (!isCurrent(ESGHistory.get(i).getId(), esgOldest.getId())) {
				esgOldest = ESGHistory.get(i);
			}
		}
		return oldestESG;
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
