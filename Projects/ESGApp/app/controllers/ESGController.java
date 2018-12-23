package controllers;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import DataAccessLayer.DataBaseConnection;
import DataAccessLayer.DataFileOperation;
import models.Root;
import play.mvc.Controller;
import play.mvc.Result;

public class ESGController extends Controller{

	private DataBaseConnection dbConnection=new DataBaseConnection();
	private DataFileOperation fileOperation=new DataFileOperation();
	private List<Root> rootList=new ArrayList<>();
	private List<String> graphList;

	public Result saveESGToDB() throws Exception
	{
		graphList=dbConnection.getItems();
		for(String s:graphList)
		{
			rootList.add(dbConnection.parseRootFromJSON(fileOperation.convertStringToJsonNode(s)));		
		}
		JsonNode rootJson=request().body().asJson();
		if(isTheSameRoot(rootList, rootJson)!=null)
		{

			dbConnection.updateDataInDB(rootJson,isTheSameRoot(rootList, rootJson));
			return ok("Graph "+fileOperation.getRequestRootID(rootJson)+" updated");
		}
		else
			dbConnection.insertDataToDB(rootJson);			

		return ok(fileOperation.getRequestRootID(rootJson)+"th graph saved to DB");
	}

	public Root isTheSameRoot(List<Root> rootList,JsonNode rootJson)
	{
		Root root=null;
		for(Root r:rootList)
		{
			if(r.getId().equals(fileOperation.getRequestRootID(rootJson)))
				root=r;
			else
				root=null;
		}
		return root;
	}
}
