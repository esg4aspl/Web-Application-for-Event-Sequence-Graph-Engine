package controllers;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import play.mvc.Controller;
import play.mvc.Result;

public class DBCController extends Controller {
	private String localHostName;
	private Integer localPort;
	private MongoClient mongoClient;
	private DB db;
	public DBCController ()
	{
		this.localHostName=play.Configuration.root().getString("mongo.local.hostname");
		this.localPort=play.Configuration.root().getInt("mongo.local.port");
		
	}
	public void connectToDB()
	{
		this.mongoClient=new MongoClient(localHostName,localPort);
		this.db=mongoClient.getDB("MyDatabase");
	}
	
	public DBCollection getCollection(String collectionName)
	{
		DBCollection dbCollection=db.getCollection(collectionName);
		return dbCollection;
	}
	
	public void saveObjectToDB(String collectionName, Object object)
	{
		//TODO
	}
	public void deleteObjectFromDB(String collectionName, Object object)
	{
		//TODO
	}
}
