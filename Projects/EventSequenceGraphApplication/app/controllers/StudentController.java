/*package controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
//import org.codehaus.jackson.JsonProcessingException;
//import org.codehaus.jackson.map.ObjectMapper;
import org.w3c.dom.Document;

//import com.google.gson.Gson;
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
	
	//read XML file
	public String readXmlFile() throws Exception
	{
		File xmlFile= new File("myXML.xml");
		DocumentBuilderFactory documentBuilderFactory= DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder= documentBuilderFactory.newDocumentBuilder();
		Document document= documentBuilder.parse(xmlFile);
		return convertXMLFileToString(document);
		//TODO according to Turkan's xml file.
		//NodeList list=document.getElementsByTagName("");
	}
	private String convertXMLFileToString(Document xmlDocument)
	{
	    TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer;
	    String xmlString="";
	    try {
	        transformer = tf.newTransformer();
	         
	        // Uncomment if you do not require XML declaration
	        // transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	         
	        //A character stream that collects its output in a string buffer,
	        //which can then be used to construct a string.
	        StringWriter writer = new StringWriter();
	 
	        //transform document to string
	        transformer.transform(new DOMSource(xmlDocument), new StreamResult(writer));
	 
	        xmlString = writer.getBuffer().toString();  
	        //System.out.println(xmlString);                      //Print to console or logs
	    }
	    catch (TransformerException e)
	    {
	        e.printStackTrace();
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	    return xmlString;
	}
	
	//read data JSON file
	@SuppressWarnings("deprecation")
	public Result readDataFromJSON(JsonNode rootJson) throws Exception, IOException
	{
		//ObjectMapper mapper=new ObjectMapper();
		//JsonNode rootNode = mapper.readTree(new File("TurkansJSON.json"));
		JsonNode rootNode=rootJson;
		JsonNode idNode=rootNode.path("ID");
		JsonNode nameNode=rootNode.path("name");
		JsonNode xmlFile=rootNode.path("xmlVersion");
		
		
		JsonNode verticesNode=rootNode.path("vertices");
		Iterator<JsonNode> itr=verticesNode.elements();
		System.out.println("\nVertices");
		
		while (itr.hasNext()) {
			JsonNode vertexNode=itr.next();
			
			JsonNode vertexIdNode=vertexNode.path("ID");
			JsonNode vertexEventNode=vertexNode.path("event");
			
			Vertex vertex= new Vertex(vertexIdNode.intValue(),vertexEventNode.textValue());
			vertices.add(vertex);
		}
		
		JsonNode edgesNode=rootNode.path("edges");
		Iterator<JsonNode> iter=edgesNode.elements();
		System.out.println("\nEdges");
		
		while (iter.hasNext()) {
			JsonNode edgeNode=iter.next();
			
			JsonNode edgeIdNode=edgeNode.path("ID");
			JsonNode edgeSourceNode=edgeNode.path("source");
			JsonNode edgeTargetNode=edgeNode.path("target");
			
			Edge edge=new Edge(edgeIdNode.intValue(),edgeSourceNode.intValue(),edgeTargetNode.intValue());
			edges.add(edge);
		}
		

		Root root=new Root(idNode.intValue(),nameNode.textValue(),vertices,edges,xmlFile.textValue());
		roots.add(root);
		return ok("it is ok");
	        
	}
	
	//insert data to mongodb
	public Result insertDataToDB(DBCollection dbCollection) throws Exception
	{
		BasicDBObject basicDBObject=new BasicDBObject();
		List<BasicDBObject> vertexList=new ArrayList<>();
		List<BasicDBObject> edgeList=new ArrayList<>();
		List<BasicDBObject> rootList=new ArrayList<>();
		
		for(Root r:roots)
		{
			basicDBObject.put("id", r.getId());
			basicDBObject.put("name", r.getName());
			basicDBObject.put("xmlVersion", r.getXmlFileString());
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
			FileWriter fw=new FileWriter("TurkansJSON.json");
			fw.write(jsonText);
			fw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ok("Data is written");
	}
	

	//connect DB
	public Result dbConnection(JsonNode rootJson) throws Exception
	{
		readDataFromJSON(rootJson);
		DBCollection dbCollection= connectDB().getCollection("root");
		insertDataToDB(dbCollection);
		//String jsonText=readDataFromDB(dbCollection);
		//writeDataToJSON(jsonText);
		//return ok(play.libs.Json.toJson(basicDBObject));
		return ok("dbconnections are done.");
	}
}
*/