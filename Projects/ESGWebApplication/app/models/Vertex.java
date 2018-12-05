package models;

public class Vertex {
	private Integer id;
	private String event;
	public Vertex(int id, String event)
	{
		setId(id);
		setEvent(event);
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
}
