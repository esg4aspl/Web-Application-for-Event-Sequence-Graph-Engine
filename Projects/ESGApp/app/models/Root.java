package models;

import java.util.List;

public class Root {
	private String name;
	private Integer id;
	private List<Vertex> vertexList;
	private List<Edge> edgeList;
	
	private String xmlFileString;
	public Root(int id, String name, List<Vertex> vertexList, List<Edge> edgeList, String xmlFileString)
	{
		this.vertexList=vertexList;
		this.edgeList=edgeList;
		this.setId(id);
		this.setName(name);
		setXmlFileString(xmlFileString);
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
	
}
