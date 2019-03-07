package controllers;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.Binary;
import org.codehaus.jackson.map.deser.std.JsonNodeDeserializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import DataAccessLayer.DataBaseConnection;
import DataAccessLayer.DataFileOperation;
import models.Root;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class ESGController extends Controller{
///
	//TODO  searching
	//TODO save as the graph with new name as a new graph in the db.
	//TODO return the case file to json
	
	private DataBaseConnection dbConnection=new DataBaseConnection();
	private DataFileOperation fileOperation=new DataFileOperation();
	private List<Root> rootList=new ArrayList<>();
	private List<String> graphList;

	// send responsejson file as a string to turkan. get data from db
	public Result readData()
	{
		String esgJson=	dbConnection.readDataFromDB();
		esgJson=(esgJson.substring(0, esgJson.length()-1))+"]";
		return ok(esgJson);
	}
	public Result saveESGToDB() throws Exception
	{
		graphList=dbConnection.getItems();
		for(String s:graphList)
		{
			rootList.add(dbConnection.parseRootFromJSON(fileOperation.convertStringToJsonNode(s)));		
		}
		JsonNode rootJson=request().body().asJson();
		if(isTheSameRoot(rootList, rootJson)!=null)
		{

			dbConnection.updateDataInDB(rootJson,isTheSameRoot(rootList, rootJson));
			return ok("Graph "+fileOperation.getRequestRootID(rootJson)+" updated");
		}
		else
			dbConnection.insertDataToDB(rootJson);			

		return ok(fileOperation.getRequestRootID(rootJson)+"th graph saved to DB");
	}
	
	public Result saveJPEGToDB () throws IOException
	{
		try {
			//dbConnection.saveJPEGToDB();
			//return ok(dbConnection.showPhotos().toString());
			 /*String filename = "photoName.jpg";
	            String empname ="firstPhoto";
	            insert(empname,filename, dbConnection.connectDB());
	             
	            String destfilename = "destfile.jpg";
	            *//** Retrieves record where name = empname, including his photo. 
	              * Retrieved photo is stored at location filename 
	              **//*
	            return ok(retrieve(empname, destfilename, dbConnection.connectDB()));
			*/
			dbConnection.saveJPEGToDB();
			
			return ok(dbConnection.showPhotos().toString());
		} catch (Exception e) {
			return ok("jpeg is not saved to db.");
		}
		
	}
	   
    void insert(String empname, String filename, DBCollection collection)
    {
        try
        {
            File imageFile = new File(filename);
            FileInputStream f = new FileInputStream(imageFile);
 
            byte b[] = new byte[f.available()];
            f.read(b);
 
            Binary data = new Binary(b);
            BasicDBObject o = new BasicDBObject();
            o.append("name",empname).append("photo",data);
            collection.insert(o);
            System.out.println("Inserted record.");
 
            f.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    String retrieve(String name, String filename, DBCollection collection)
    {
        byte c[] = null;
        try
        {
            DBObject obj = collection.findOne(new BasicDBObject("name", name));
            String n = (String)obj.get("name");
            c = (byte[])obj.get("photo");
            FileOutputStream fout = new FileOutputStream(filename);
            fout.write(c);
            fout.flush();
            fout.close();
            
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return "Photo of "+name+" retrieved and stored at "+filename + "\n "+c.toString();
    }

	public Root isTheSameRoot(List<Root> rootList,JsonNode rootJson)///name ve idye göre 
	{
		Root root=null;
		for(Root r:rootList)
		{
			if(r.getId().equals(fileOperation.getRequestRootID(rootJson)))
				root=r;
			else
				root=null;
		}
		return root;
	}
}
