package models;

import java.util.ArrayList;
import java.util.List;

public class Root {
	//TODO bir ESG nin subESGsi olabilir.
	private String name;
	private Object _id;
	private String id;//
	private String xmlFileString;
	private List<Vertex> vertexList;
	private List<Edge> edgeList;
	//private List<String> subESG;
	public Root(String id,String _id, String name, List<Vertex> vertexList, List<Edge> edgeList, String xmlFileString)
	{
		//this.subESG=new ArrayList<>();//daha sonra turkandan gelicek 
		this.vertexList=vertexList;
		this.edgeList=edgeList;
		//this.set_id(_id);
		this.setId(id);
		this.setName(name);
		setXmlFileString(xmlFileString);
	}
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String get_id() {
		return (String) _id;
	}
	public void set_id(String _id) {
		this._id=_id;
	}
	/*public List<String> getSubESG() {
		return subESG;
	}
	public void addSubESG(String subESG) {
		this.subESG.add(subESG);
	}*/

	
	
}
