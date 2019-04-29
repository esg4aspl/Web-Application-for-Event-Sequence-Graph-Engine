package engine;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class EngineConnection{

public String parseJSONObjectForESGSimpleCreation(JSONObject esg){

		
		String testCases = "";
		try {
			String id = (String) esg.get("id");
			String name = esg.getString("name");
			System.out.println("id " + id + " name " + name);

			JSONArray JSONVertices = esg.getJSONArray("vertices");
			System.out.println("JSONVertices.length() " + JSONVertices.length());
			JSONArray JSONEdges = esg.getJSONArray("edges");
			System.out.println("JSONEdges.length() " + JSONEdges.length());
			testCases += id + name + JSONVertices + JSONEdges ;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return testCases;
	}
}
