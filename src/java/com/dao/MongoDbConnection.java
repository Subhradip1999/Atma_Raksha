/*
 *Developed by : Subhradip Biswas  
 *Date: jan 10, 2021. 
 */
package com.dao;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.mongodb.DB;
import com.mongodb.MongoClient;
/**
 *
 * @author u
 */
public class MongoDbConnection {
    private static MongoDbConnection mongocon = null;
    private static DB db = null;
    
    public static MongoDbConnection getInstance(){
       if(mongocon == null){
           System.out.println("inside instances");
          mongocon = new MongoDbConnection();
       }
       return mongocon;
    }
    
    public static DB getConnection(){
        if(db == null){
          MongoClient mongo=null;
            try {
                mongo = new MongoClient("127.0.0.1", 27017);
                //db = mongo.getDB("smsrcLabdb");
                db = mongo.getDB("projectAtmaRaksha");
            } catch (Exception ex) {
                Logger.getLogger(MongoDbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
     return db;
    }
}
