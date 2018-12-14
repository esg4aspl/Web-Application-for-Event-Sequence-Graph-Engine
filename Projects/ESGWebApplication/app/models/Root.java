package models;

import java.util.List;

public class Root {
	private String name;
	private Integer id;
	private List<Vertex> vertexList;
	private List<Edge> edgeList;
	public Root(int id, String name, List<Vertex> vertexList, List<Edge> edgeList)//en az ikitane verrtices olmalı
	{
		this.vertexList=vertexList;
		this.edgeList=edgeList;
		this.setId(id);
		this.setName(name);
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
	
}
