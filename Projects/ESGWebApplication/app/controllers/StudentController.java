package controllers;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import models.Edge;
import models.Root;
import models.Vertex;
import play.mvc.Controller;
import play.mvc.Result;

public class StudentController extends Controller{
	private List<Vertex> vertices=new ArrayList<>();
	private List<Edge> edges= new ArrayList<>();
	private List<Root> roots= new ArrayList<>();

	public Result dbConnection()
	{
		String localHostName = play.Configuration.root().getString("mongo.local.hostname");
		Integer  localPort = play.Configuration.root().getInt("mongo.local.port");
		MongoClient mongoClient= new MongoClient(localHostName,localPort);
		DB db= mongoClient.getDB("ESG");
		DBCollection dbCollection= db.getCollection("root");
		BasicDBObject basicDBObject=new BasicDBObject();
		List<BasicDBObject> vertexList=new ArrayList<>();
		List<BasicDBObject> edgeList=new ArrayList<>();
		//List<BasicDBObject> rootList=new ArrayList<>();
		
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
				vertexList.add(new BasicDBObject("id",v.getId()));
				vertexList.add(new BasicDBObject("event",v.getEvent()));
			}
			
			for(Edge e: edges)
			{

				edgeList.add(new BasicDBObject("id",e.getId()));
				edgeList.add(new BasicDBObject("source",e.getSource()));
				edgeList.add(new BasicDBObject("target",e.getTarget()));
			}
		}
		
		basicDBObject.put("vertices",vertexList);
		basicDBObject.put("edges",edgeList);
		dbCollection.insert(basicDBObject);
		
		return ok(play.libs.Json.toJson(basicDBObject));
		//return ok("it is added");
	}
}
