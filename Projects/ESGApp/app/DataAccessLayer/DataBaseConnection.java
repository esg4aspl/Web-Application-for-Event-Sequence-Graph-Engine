package DataAccessLayer;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.BsonBinaryWriter;
import org.bson.Document;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;
import org.bson.io.BasicOutputBuffer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import de.undercouch.bson4jackson.BsonFactory;
import models.Edge;
import models.Root;
import models.Vertex;

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
		//esg.replace(),"");
		
		return esg;
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
	public List<Root> getItems1() throws IOException, Exception
	{
		String localHostName = play.Configuration.root().getString("mongo.local.hostname");
		Integer  localPort = play.Configuration.root().getInt("mongo.local.port");
		MongoDatabase db = new MongoClient(localHostName,localPort).getDatabase("ESG");
		MongoCollection<Document> coll = db.getCollection("root");


		List<Root> jsonList=new ArrayList<>();
		FindIterable<Document> fi=coll.find();
		MongoCursor<Document> cursor = fi.iterator();
		while (cursor.hasNext()) {
			jsonList.add( parseRootFromJSON(documentToJsonNode(cursor.next())));
		}
		return jsonList;
	}
	public static InputStream documentToInputStream(final Document document) {
	    BasicOutputBuffer outputBuffer = new BasicOutputBuffer();
	    BsonBinaryWriter writer = new BsonBinaryWriter(outputBuffer);
	    new DocumentCodec().encode(writer, document, EncoderContext.builder().isEncodingCollectibleDocument(true).build());
	    return new ByteArrayInputStream(outputBuffer.toByteArray());
	}

	public static JsonNode documentToJsonNode(final Document document) throws IOException {
	    ObjectMapper mapper = new ObjectMapper();
	    InputStream is = documentToInputStream(document);
	    return mapper.readTree(is);
	}

	public Root parseRootFromJSON(JsonNode rootJson) throws Exception, IOException
	{
		Root root= new Root();

		JsonNode rootNode=rootJson;
		//JsonNode _idNode=rootNode.path("_id");
		JsonNode idNode=rootNode.path("id");
		JsonNode nameNode=rootNode.path("name");
		JsonNode xmlFile=rootNode.path("xmlVersion");
		//JsonNode subESGNode=rootNode.path("subESG");

		//root.set_id(_idNode.textValue());
		root.setId(idNode.textValue());
		root.setName(nameNode.textValue());
		root.setXmlFileString(xmlFile.textValue());
		//root.addSubESG("sub");

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
		List<BasicDBObject> subESG=new ArrayList<>();

		Root root= parseRootFromJSON(rootJson);
		//basicDBObject.put("_id", root.get_id());
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
		basicDBObject.put("subESG", subESG);
		connectDB().insert(basicDBObject);
	}

	public void updateDataInDB(JsonNode jsonNode)
	{
		List<BasicDBObject> vertexList=new ArrayList<>();
		List<BasicDBObject> edgeList=new ArrayList<>();
		JsonNode rootNode=jsonNode;
		//JsonNode _idNode=rootNode.path("_id");
		JsonNode idNode=rootNode.path("id");
		JsonNode nameNode=rootNode.path("name");
		JsonNode xmlFile=rootNode.path("xmlVersion");

		BasicDBObject searchQuery= new BasicDBObject().append("xmlVersion",xmlFile.asText());//.append("name",nameNode.asText());//.append("id", idNode.asText()
		BasicDBObject updateQuery = new BasicDBObject();
		

		JsonNode verticesNode=rootNode.path("vertices");
		Iterator<JsonNode> itr=verticesNode.elements();

		while (itr.hasNext()) {
			JsonNode vertexNode=itr.next();
			JsonNode vertexIdNode=vertexNode.path("id");
			JsonNode vertexEventNode=vertexNode.path("event");
			BasicDBObject vertexUpdateQuery = new BasicDBObject();
			//vertexUpdateQuery.notify();
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
		updateQuery.put("id",idNode.textValue());
		updateQuery.put("name",nameNode.textValue());
		updateQuery.put("xmlVersion",xmlFile.textValue());
		//updateQuery.put(key, v)
		updateQuery.put("vertices",vertexList);
		updateQuery.put("edges", edgeList);
		connectDB().update(searchQuery, updateQuery);

	}

}
