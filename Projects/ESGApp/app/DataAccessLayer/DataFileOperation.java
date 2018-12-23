package DataAccessLayer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.Edge;
import models.Root;
import models.Vertex;

public class DataFileOperation {

	//write to json
	public void writeToFile(String jsonText,String fileName)
	{
		try {
			FileWriter fw=new FileWriter(fileName);
			fw.write(jsonText);
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public JsonNode convertStringToJsonNode(String jsonText) throws JsonProcessingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(jsonText);
		return jsonNode;
	}
	public int getRequestRootID(JsonNode rootJson)
	{
		JsonNode rootNode=rootJson;
		JsonNode idNode=rootNode.path("id");
		return idNode.intValue();
	}

	//read data JSON file
	//@SuppressWarnings("deprecation")
	public void addJsonFileToList(JsonNode rootJson, Root root, List<Root> roots) throws Exception, IOException
	{
		JsonNode rootNode=rootJson;
		JsonNode idNode=rootNode.path("id");
		JsonNode nameNode=rootNode.path("name");
		JsonNode xmlFile=rootNode.path("xmlVersion");
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

		root.setId(idNode.intValue());
		root.setName(nameNode.textValue());
		root.setXmlFileString(xmlFile.textValue());

		roots.add(root);
	}

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
	public String convertXMLFileToString(Document xmlDocument)
	{
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		String xmlString="";
		try {
			transformer = tf.newTransformer();
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(xmlDocument), new StreamResult(writer));
			xmlString = writer.getBuffer().toString();  
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

}