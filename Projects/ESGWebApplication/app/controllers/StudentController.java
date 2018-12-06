package controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;


import models.Edge;
import models.Root;
import models.Vertex;
import play.mvc.Controller;
import play.mvc.Result;
//import org.json.simple.JSONObject;

public class StudentController extends Controller{
	private List<Vertex> vertices=new ArrayList<>();
	private List<Edge> edges= new ArrayList<>();
	private List<Root> roots= new ArrayList<>();

	public DB connectDB()
	{
		String localHostName = play.Configuration.root().getString("mongo.local.hostname");
		Integer  localPort = play.Configuration.root().getInt("mongo.local.port");
		MongoClient mongoClient= new MongoClient(localHostName,localPort);
		DB db= mongoClient.getDB("ESG");
		return db;
	}
	//insert data to mongodb
	public Result insertDataToDB(DBCollection dbCollection)
	{
		BasicDBObject basicDBObject=new BasicDBObject();
		List<BasicDBObject> vertexList=new ArrayList<>();
		List<BasicDBObject> edgeList=new ArrayList<>();
		List<BasicDBObject> rootList=new ArrayList<>();
		
		Vertex v1=new Vertex(0,"event1");
		Vertex v2=new Vertex(1,"event2");
		Vertex v3=new Vertex(0,"event1");
		Vertex v4=new Vertex(1,"event2");
		Edge e1=new Edge(0,0,1);
		Edge e2=new Edge(1,0,1);
		Edge e3=new Edge(0,0,1);
		Edge e4=new Edge(1,0,1);
		
		vertices.add(v1);
		vertices.add(v2);
		vertices.add(v3);
		vertices.add(v4);
		
		edges.add(e1);
		edges.add(e2);
		edges.add(e3);
		edges.add(e4);
		
		Root r1=new Root(0,"root1",vertices,edges);
		roots.add(r1);
		
		for(Root r:roots)
		{
			basicDBObject.put("id", r.getId());
			basicDBObject.put("name", r.getName());
			for(Vertex v:vertices)
			{
				BasicDBObject basicDBObjectVertex=new BasicDBObject();
				basicDBObjectVertex.put("id",v.getId());
				basicDBObjectVertex.put("event",v.getEvent());
				vertexList.add(basicDBObjectVertex);
			}
			
			for(Edge e: edges)
			{
				BasicDBObject basicDBObjectEdge=new BasicDBObject();
				basicDBObjectEdge.put("id",e.getId());
				basicDBObjectEdge.put("source",e.getSource());
				basicDBObjectEdge.put("target",e.getTarget());
				edgeList.add(basicDBObjectEdge);
			}
		}
		
		basicDBObject.put("vertices",vertexList);
		basicDBObject.put("edges",edgeList);
		dbCollection.insert(basicDBObject);
		return ok("data is inserted");
	}
	
	//read data from mongodb
	public String readDataFromDB(DBCollection dbCollection)
	{
		DBCursor cursor = dbCollection.find();
		String jsonText="";
		while (cursor.hasNext()) {
			int i=1;
			jsonText+=cursor.next().toString();
		}
		return jsonText;
	}
	
	//write to json
	public Result writeDataToJSON(String jsonText)
	{
		try {
			FileWriter fw=new FileWriter("myJSON.json");
			fw.write(jsonText);
			fw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ok("Data is written");
	}
	
	//read data fromJSON
	public void readDataFromJSON()
	{
		//TODO
	}
	public Result dbConnection()
	{
		
		DBCollection dbCollection= connectDB().getCollection("root");
		insertDataToDB(dbCollection);
		String jsonText=readDataFromDB(dbCollection);
		writeDataToJSON(jsonText);
		//return ok(play.libs.Json.toJson(basicDBObject));
		return ok("dbconnections are done.");
	}
}
