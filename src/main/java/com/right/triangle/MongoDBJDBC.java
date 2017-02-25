package com.right.triangle;

/**
 * Created by sinraja on 2/25/17.
 */

import com.google.gson.Gson;
import com.mongodb.*;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.right.triangle.model.Location;
import com.right.triangle.model.PositionData;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import org.bson.Document;

public class MongoDBJDBC {

    public static void main( String args[] ) {

        /**
         * db.trackerData.createIndex({"cabId":1,"driverId":1},{unique:true})
         {
         "createdCollectionAutomatically" : false,
         "numIndexesBefore" : 2,
         "numIndexesAfter" : 3,
         "ok" : 1
         }
         * db.trackerData.ensureIndex({loc:"2d",status:1})
         {
         "createdCollectionAutomatically" : false,
         "numIndexesBefore" : 1,
         "numIndexesAfter" : 2,
         "ok" : 1
         }
         */

        try{

            Location location = new Location(50,-112);
            Gson gson = new Gson();
            String jsonString = gson.toJson(location);

            // To connect to mongodb server
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

            // Now connect to your databases
            MongoDatabase db = mongoClient.getDatabase("test");
            System.out.println("Connect to database successfully");

            PositionData positionData = new PositionData();
            positionData.setCabId("123456");
            positionData.setDriverId("123");
            positionData.setStatus("IDLE");//IDLE,IN-TRIP,LOGGED-OUT
            //Location location = new Location(51.09144802136697,-114.11773681640625);
            positionData.setLoc(location);

            // Deserialize object to json string

            String json = gson.toJson(positionData);
            // Parse to bson document and insert
            Document doc = Document.parse(json);

            db.getCollection("trackerData").insertOne(doc);
            System.out.println("Collection trackerData selected successfully");

            // Retrieve to ensure object was inserted
            FindIterable<Document> iterable = db.getCollection("trackerData").find();
            iterable.forEach(new Block<Document>() {
                @Override
                public void apply(final Document document) {
                    System.out.println(document); // See below to convert document back to Employee
                }
            });

        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}