package DataAccessLayer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;

import com.fasterxml.jackson.databind.JsonNode;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

import models.Edge;
import models.Root;
import models.Vertex;
import play.libs.Json;

public class DataBaseConnection {

	//TODO readESGfromDBden 
	public String readDataFromDB()
	{
		String esg = "[";
		DBCollection collection= connectDB();
		DBCursor cursor= collection.find();
		while(cursor.hasNext())
		{
			esg+=cursor.next();
			esg+=",";
		}
		return esg+"]";
	}
	//TODO DBden bisi getirirken 3 ayri kosulla getirecek
		//TODO 1. tum listeyi getir.
		//TODO 2. ismi su olan graphi getir
		//TODO 3. isminde su gecen graphlari getir.

	
	@SuppressWarnings({ "deprecation", "resource" })
	public DBCollection connectDB()
	{
		String localHostName = play.Configuration.root().getString("mongo.local.hostname");
		Integer  localPort = play.Configuration.root().getInt("mongo.local.port");
		DB db= new MongoClient(localHostName,localPort).getDB("ESG");
		DBCollection dbCollection= db.getCollection("root");
		return dbCollection;
	}
	public void saveJPEGToDB () throws IOException
	{
		String localHostName = play.Configuration.root().getString("mongo.local.hostname");
		Integer  localPort = play.Configuration.root().getInt("mongo.local.port");
		DB db= new MongoClient(localHostName,localPort).getDB("ESG");
		DBCollection dbCollection= db.getCollection("root");
		String newFileName ="savedJPEG";
		File imageFile = new File("photoName.jpg");
		
		GridFS gfsPhoto = new GridFS(db,"photo");
		GridFSInputFile gfsFile = gfsPhoto.createFile(imageFile);
		gfsFile.setFilename(newFileName);
		gfsFile.save();
	}
	
	public GridFSDBFile showPhotos()
	{
		String localHostName = play.Configuration.root().getString("mongo.local.hostname");
		Integer  localPort = play.Configuration.root().getInt("mongo.local.port");
		DB db= new MongoClient(localHostName,localPort).getDB("ESG");
		DBCollection dbCollection= db.getCollection("root");
		String newFileName = "savedJPEG";
		GridFS gfsPhoto = new GridFS(db, "photo");
		GridFSDBFile imageForOutput = gfsPhoto.findOne(newFileName);
		return imageForOutput;

	}
	//
	@SuppressWarnings({ "deprecation", "resource" })
	public List<String> getItems()
	{
		String localHostName = play.Configuration.root().getString("mongo.local.hostname");
		Integer  localPort = play.Configuration.root().getInt("mongo.local.port");
		MongoDatabase db = new MongoClient(localHostName,localPort).getDatabase("ESG");
		MongoCollection<Document> coll = db.getCollection("root");


		List<String> jsonList=new ArrayList<>();
		FindIterable<Document> fi=coll.find();
		MongoCursor<Document> cursor = fi.iterator();
		while (cursor.hasNext()) {
			jsonList.add( cursor.next().toJson());
		}
		return jsonList;
	}

	public Root parseRootFromJSON(JsonNode rootJson) throws Exception, IOException
	{
		Root root= new Root();

		JsonNode rootNode=rootJson;
		JsonNode idNode=rootNode.path("id");
		JsonNode nameNode=rootNode.path("name");
		JsonNode xmlFile=rootNode.path("xmlVersion");
		root.setId(idNode.intValue());
		root.setName(nameNode.textValue());
		root.setXmlFileString(xmlFile.textValue());

		JsonNode verticesNode=rootNode.path("vertices");
		Iterator<JsonNode> itr=verticesNode.elements();

		while (itr.hasNext()) {
			JsonNode vertexNode=itr.next();
			JsonNode vertexIdNode=vertexNode.path("id");
			JsonNode vertexEventNode=vertexNode.path("event");

			Vertex vertex= new Vertex(vertexIdNode.intValue(),vertexEventNode.textValue());
			root.addVertex(vertex);
		}

		JsonNode edgesNode=rootNode.path("edges");
		Iterator<JsonNode> iter=edgesNode.elements();

		while (iter.hasNext()) {
			JsonNode edgeNode=iter.next();
			JsonNode edgeIdNode=edgeNode.path("id");
			JsonNode edgeSourceNode=edgeNode.path("source");
			JsonNode edgeTargetNode=edgeNode.path("target");

			Edge edge=new Edge(edgeIdNode.intValue(),edgeSourceNode.intValue(),edgeTargetNode.intValue());
			root.addEdge(edge);
		}

		return root;
	}


	//insert data to mongodb
	public void insertDataToDB(JsonNode rootJson) throws Exception
	{
		BasicDBObject basicDBObject=new BasicDBObject();
		List<BasicDBObject> vertexList=new ArrayList<>();
		List<BasicDBObject> edgeList=new ArrayList<>();
		Root root= parseRootFromJSON(rootJson);

		basicDBObject.put("id", root.getId());
		basicDBObject.put("name", root.getName());
		basicDBObject.put("xmlVersion", root.getXmlFileString());
		for(Vertex v:root.getVertexList())
		{
			BasicDBObject basicDBObjectVertex=new BasicDBObject();
			basicDBObjectVertex.put("id",v.getId());
			basicDBObjectVertex.put("event",v.getEvent());
			vertexList.add(basicDBObjectVertex);
		}

		for(Edge e: root.getEdgeList())
		{
			BasicDBObject basicDBObjectEdge=new BasicDBObject();
			basicDBObjectEdge.put("id",e.getId());
			basicDBObjectEdge.put("source",e.getSource());
			basicDBObjectEdge.put("target",e.getTarget());
			edgeList.add(basicDBObjectEdge);
		}


		basicDBObject.put("vertices",vertexList);
		basicDBObject.put("edges",edgeList);
		connectDB().insert(basicDBObject);
	}

	public void updateDataInDB(JsonNode jsonNode, Root root)
	{
		List<BasicDBObject> vertexList=new ArrayList<>();
		List<BasicDBObject> edgeList=new ArrayList<>();
		JsonNode rootNode=jsonNode;
		JsonNode idNode=rootNode.path("id");
		JsonNode nameNode=rootNode.path("name");
		JsonNode xmlFile=rootNode.path("xmlVersion");

		BasicDBObject searchQuery= new BasicDBObject().append("id",idNode.intValue());
		BasicDBObject updateQuery = new BasicDBObject();
		updateQuery.put("id",idNode.intValue());
		updateQuery.put("name",nameNode.textValue());
		updateQuery.put("xmlVersion",xmlFile.textValue());

		JsonNode verticesNode=rootNode.path("vertices");
		Iterator<JsonNode> itr=verticesNode.elements();

		while (itr.hasNext()) {
			JsonNode vertexNode=itr.next();
			JsonNode vertexIdNode=vertexNode.path("id");
			JsonNode vertexEventNode=vertexNode.path("event");
			BasicDBObject vertexUpdateQuery = new BasicDBObject();
			vertexUpdateQuery.put("id",vertexIdNode.intValue());
			vertexUpdateQuery.put("event",vertexEventNode.textValue());
			vertexList.add(vertexUpdateQuery);
		}

		JsonNode edgesNode=rootNode.path("edges");
		Iterator<JsonNode> iter=edgesNode.elements();

		while (iter.hasNext()) {
			JsonNode edgeNode=iter.next();
			JsonNode edgeIdNode=edgeNode.path("id");
			JsonNode edgeSourceNode=edgeNode.path("source");
			JsonNode edgeTargetNode=edgeNode.path("target");
			BasicDBObject edgeUpdateQuery = new BasicDBObject();
			edgeUpdateQuery.put("id",edgeIdNode.intValue());
			edgeUpdateQuery.put("source",edgeSourceNode.intValue());
			edgeUpdateQuery.put("target",edgeTargetNode.intValue());
			edgeList.add(edgeUpdateQuery);

		}
		updateQuery.put("vertices",vertexList);
		updateQuery.put("edges", edgeList);
		connectDB().update(searchQuery, updateQuery);

	}

}
