package dataAccessLayer;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class DBConnections {
	
	final static String databaName = "ESG";
	final static String collectionName = "esg";
	protected DB database;
	private DBCollection dbCollection;
	
	
	public DBCollection collection()
	{
		database = connectMongoDB(databaName);
		dbCollection = getDBCollection(collectionName);
		return dbCollection;
	}
	
	@SuppressWarnings("resource")
	public DB connectMongoDB(String databaseName)
	{
		String localHostName = play.Configuration.root().getString("mongo.local.hostname");
		Integer  localPort = play.Configuration.root().getInt("mongo.local.port");
		return new MongoClient(localHostName,localPort).getDB(databaseName);
	}
	
	public DBCollection getDBCollection(String collectionName)
	{
		return database.getCollection(collectionName);
	}
}