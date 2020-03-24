import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.util.JSON;
import java.io.BufferedReader;
import java.io.FileReader;

import com.google.gson.Gson;
import net.sf.json.JSONObject;
import org.bson.Document;

public class dbConnection {
    public static void main(String[] args) {
        try {
            //连接MongoDB服务器，端口号为27017
            MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
            //连接数据库
            MongoDatabase mDatabase = mongoClient.getDatabase("test");  //test可选
            System.out.println("Connect to database successfully!");
            System.out.println("MongoDatabase inof is : "+mDatabase.getName());

            MongoCollection collection = mDatabase.getCollection("myTestCollection");
            BufferedReader br = new BufferedReader(new FileReader("D:/3rd Year/DXYOverall.json"));
            String s = null;
            String text = "";
            while((s = br.readLine()) != null){
                //System.out.println(s);
                text = text + s + "\n";
            }
            System.out.println(text);
            Gson gson=new Gson();
            //DBObject dbObject = (DBObject) JSON.parse(text);
            Document document = new Document(JSONObject.fromObject(text));
            collection.insertOne(document);
            System.out.println("Successfully insert!");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }


    }
}