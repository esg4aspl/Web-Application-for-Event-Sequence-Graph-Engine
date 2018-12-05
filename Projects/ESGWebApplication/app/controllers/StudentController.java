package controllers;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import play.mvc.Controller;
import play.mvc.Result;

public class StudentController extends Controller{
	public Result dbConnection()
	{
		String localHostName = play.Configuration.root().getString("mongo.local.hostname");
		Integer  localPort = play.Configuration.root().getInt("mongo.local.port");
		MongoClient mongoClient= new MongoClient(localHostName,localPort);
		DB db= mongoClient.getDB("MyDatabase");
		DBCollection dbCollection= db.getCollection("Students");
		BasicDBObject basicDBObject=new BasicDBObject();
		basicDBObject.put("name", "sercan");
		basicDBObject.put("age", 23);
		dbCollection.insert(basicDBObject);
		return ok(play.libs.Json.toJson(basicDBObject));
		//new project
	}
}
