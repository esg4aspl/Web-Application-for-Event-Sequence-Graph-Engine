package models;

public class Edge {
	
	public Edge(int id, int source, int target)
	{
		setId(id);
		setSource(source);
		setTarget(target);
	}

	private Integer id;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getTarget() {
		return target;
	}

	public void setTarget(Integer target) {
		this.target = target;
	}

	private Integer source;
	private Integer target;
	
}
