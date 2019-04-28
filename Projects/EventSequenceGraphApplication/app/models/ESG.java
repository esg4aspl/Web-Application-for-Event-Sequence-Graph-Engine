package models;

import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;

public class ESG {
	private Object _id;
	private String id;
	private String name;
	private String xmlVersion;
	private ArrayList < Object > vertices = new ArrayList < Object > ();
	private ArrayList < Object > edges = new ArrayList < Object >();
	private ArrayList < Vertex > vertexList = new ArrayList < Vertex > ();
	private ArrayList < Edge > edgeList = new ArrayList < Edge >();
	private int userId;
	private int generatedTestCasesId;
	private String generatedTestCases;
	// Getter Methods 

	public Object get_id() {
		return _id;
	}
	
	public String getId() {
		return id;
	}
	/*public void setUserId(int userId) {
	this.userId = userId;
}

public void setGeneratedTestCasesId(int generatedTestCasesId) {
	this.generatedTestCasesId = generatedTestCasesId;
}

public void setGeneratedTestCases(String generatedTestCases) {
	this.generatedTestCases = generatedTestCases;
}*/
	public String getName() {
		return name;
	}

	public String getXmlVersion() {
		return xmlVersion;
	}
	public ArrayList<Object> getObjectVertices() 
	{
		return this.vertices;
	}
	public ArrayList<Object> getObjectEdges() 
	{
		return this.edges;
	}

	public ArrayList<Vertex> getVertexList() {
		for(Object ver:vertices)
		{
			Vertex vertex= new ObjectMapper().convertValue(ver, Vertex.class);
			vertexList.add(vertex);
		}
		return vertexList;
	}

	public ArrayList<Edge> getEdgeList() {
		for(Object ed:edges)
		{
			Edge edge= new ObjectMapper().convertValue(ed, Edge.class);
			this.edgeList.add(edge);
		}
		return edgeList;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public int getGeneratedTestCasesId() {
		return generatedTestCasesId;
	}
	
	public String getGeneratedTestCases() {
		return generatedTestCases;
	}

	// Setter Methods 

	public void set_id(Object _id) {
		this._id = _id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setXmlVersion(String xmlVersion) {
		this.xmlVersion = xmlVersion;
	}

	public void setVertices(ArrayList<Object> vertices) {
		this.vertices=vertices;		
	}

	public void setEdges(ArrayList<Object> edges) {
		this.edges = edges;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public void setGeneratedTestCasesId(int generatedTestCasesId) {
		this.generatedTestCasesId = generatedTestCasesId;
	}
	
	public void setGeneratedTestCases(String generatedTestCases) {
		this.generatedTestCases = generatedTestCases;
	}
	
	@Override
	public String toString() {
		return "RootObject [id=" + id + ", name=" + name + ", xmlVersion=" + xmlVersion + ", vertices="
				+ vertices + ", edges=" + edges + "]";
	}
	
}



