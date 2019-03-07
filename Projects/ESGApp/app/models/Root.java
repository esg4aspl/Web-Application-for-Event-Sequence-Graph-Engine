package models;

import java.util.ArrayList;
import java.util.List;

public class Root {
	//TODO bir ESG nin subESGsi olabilir.
	private String name;
	private Integer id;
	private String xmlFileString;
	private List<Vertex> vertexList;
	private List<Edge> edgeList;
	/*public Root(int id, String name, List<Vertex> vertexList, List<Edge> edgeList, String xmlFileString)
	{
		this.vertexList=vertexList;
		this.edgeList=edgeList;
		this.setId(id);
		this.setName(name);
		setXmlFileString(xmlFileString);
	}*/
	public Root()
	{
		vertexList = new ArrayList<>();
		edgeList = new ArrayList<>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getXmlFileString() {
		return xmlFileString;
	}
	public void setXmlFileString(String xmlFileString) {
		this.xmlFileString = xmlFileString;
	}
	
	public List<Vertex> getVertexList() {
		return vertexList;
	}
	public void addVertex(Vertex vertex) {
		this.vertexList.add(vertex);
	}
	public List<Edge> getEdgeList() {
		return edgeList;
	}
	public void addEdge(Edge edge) {
		this.edgeList.add(edge);
	}

	
}
