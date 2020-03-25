import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import java.io.BufferedReader;
import java.io.FileReader;
import net.sf.json.JSONObject;
import org.bson.Document;

public class dbConnection {
    public void importData() {
        try {
            //Connect MongoDB, port number is 27017
            MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);

            //Connect specific database
            MongoDatabase mDatabase = mongoClient.getDatabase("test"); //"test" is optional
            //System.out.println("Connect to database successfully!");
            //System.out.println("Current MongoDatabase is : "+mDatabase.getName());

            //Switch to specific collection
            MongoCollection collection = mDatabase.getCollection("DXYArea");

            //Read the data file from previous crawler running result
            BufferedReader br = new BufferedReader(new FileReader("D:/3rdYear/DXYArea.json")); //Need to switch to your own file path
            String s = null;
            String text = "";
            while((s = br.readLine()) != null){
                //System.out.println(s);
                text = text + s + "\n";
            }
            //System.out.println(text);

            //Change the content into the type that suits MongoDB
            Document document = new Document(JSONObject.fromObject(text));

            //Import the latest data
            collection.insertOne(document);
            //System.out.println("Successfully insert!");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}