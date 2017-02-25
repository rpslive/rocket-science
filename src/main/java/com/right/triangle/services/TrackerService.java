package com.right.triangle.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import com.right.triangle.model.Location;
import com.right.triangle.model.PositionData;
import javafx.geometry.Pos;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sinraja on 2/25/17.
 */
public class TrackerService {
    private static MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
    private static MongoDatabase db = mongoClient.getDatabase("test");
    private static MongoCollection  collection = db.getCollection("trackerData");

    public String getStatus(String cabId, String driverId) {
        FindIterable<Document> iterable = collection.find(Filters.and(Filters.eq("cabId",cabId),Filters.eq("driverId",driverId)));
        List<PositionData> dataList = getPositionDataList(iterable);
        if(dataList.size()>0){
            return dataList.get(0).getStatus();
        }
        return null;
    }

    public boolean updateStatus(String cabId, String driverId, String status) {
        Bson filter = Filters.and(Filters.eq("cabId", cabId),Filters.eq("driverId",driverId));
        Bson updates = Updates.set("status", status);
        collection.findOneAndUpdate(filter, updates);
        return true;
    }

    public List<PositionData> getNearby(Location location) {

        FindIterable<Document> iterable = collection.find(
                Filters.and(
                        Filters.eq("status", "IDLE"),
                        Filters.near("loc", location.getLat(), location.getLon(), 50000.0,0.0)));
        return getPositionDataList(iterable);
    }

    private List<PositionData> getPositionDataList(FindIterable<Document> iterable) {
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.findAndRegisterModules();
        List<PositionData> positionDataList = new ArrayList<>();

        for (Document document : iterable) {
            try {
                PositionData positionData = mapper.readValue(document.toJson(), PositionData.class);
                positionDataList.add(positionData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return positionDataList;
    }

    public boolean setPosition(PositionData positionData) {
        Document doc = createDocument(positionData);
        Bson filter = Filters.and(Filters.eq("carId", positionData.getCabId()),Filters.eq("driverId",positionData.getDriverId()));
        Bson update =  new Document("$set",doc);
        UpdateOptions options = new UpdateOptions().upsert(true);
        collection.updateOne(filter, update, options);
        return true;
    }

    private Document createDocument(Object data) {
        // Deserialize object to json string
        Gson gson = new Gson();
        String json = gson.toJson(data);
        // Parse to bson document and insert
        return Document.parse(json);
    }
}
