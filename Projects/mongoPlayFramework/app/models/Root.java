package models;

import java.util.ArrayList;
import java.util.List;

public class Root {
	private String name;
	private Integer id;
	private List<Vertex> vertexList;
	private List<Edge> edgeList;
	public Root(int id, String name)//en az ikitane verrtices olmalı
	{
		vertexList=new ArrayList<>();
		edgeList=new ArrayList<>();
		this.id=id;
		this.name=name;
	}
	public void addVertex(Vertex vertex)
	{
		vertexList.add(vertex);
	}
	public void addEdge(Edge edge)
	{
		edgeList.add(edge);
	}
}
