/*
 * Developed by : Subhradip Biswas  
 * Date: jan 11, 2021. 
 */

package com.dao;

import com.bean.NewUserRegistrationBean;
import com.bean.loginBean;
import com.bean.userCurrentLocation;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import static javafx.scene.Cursor.cursor;

public class daoImpl {
    public ArrayList<userCurrentLocation> getCurrentLocation(){
        ArrayList<userCurrentLocation> userLocation = new ArrayList<userCurrentLocation>();
        userCurrentLocation info = new userCurrentLocation();
        info.setUid("0001");
        info.setName("ram");
        info.setLat("22.36");
        info.setLng("73.46");
        userCurrentLocation info1 = new userCurrentLocation();
        info1.setUid("0002");
        info1.setName("ramesh");
        info1.setLat("12.22");
        info1.setLng("67.56");
        
        userLocation.add(info);
        userLocation.add(info1);
        return userLocation;
    }
    
    private static MongoDbConnection mongocon = null;
    private static DB db = null;
    
    public ArrayList<userCurrentLocation> getData(String profileId) throws UnknownHostException{
            
            DB  db =  mongocon.getConnection();
            DBCollection col = db.getCollection("userDetails");
            DBCursor cursor = col.find();
            BasicDBObject whereQuery = new BasicDBObject();
                
            ArrayList<userCurrentLocation> userLocation = new ArrayList<userCurrentLocation>();
          
            whereQuery.append("profileId", profileId);
            cursor = col.find(whereQuery);
            
             {
               while (cursor.hasNext()){
                userCurrentLocation u=new userCurrentLocation();
                DBObject theObj = cursor.next(); 
                String name=(String)theObj.get("userName");
                
                u.setName(name);
                u.setUid((String)theObj.get("userID"));
                u.setLat((String)theObj.get("latitude"));
                u.setLng((String)theObj.get("longitude"));
                userLocation.add(u);
                //System.out.println("name : "+name);
                }
            }
             return userLocation;
            }
   
    
    public String  getFlag(String profileId) throws UnknownHostException{
            
                DB  db =  mongocon.getConnection();
                DBCollection col = db.getCollection("nearUserFlag");
                DBCursor cursor = col.find();
                BasicDBObject whereQuery = new BasicDBObject();
                String flag="";
            whereQuery.append("profileId", profileId);
            cursor = col.find(whereQuery);
            
             {
               while (cursor.hasNext()){
                userCurrentLocation u=new userCurrentLocation();
                DBObject theObj = cursor.next(); 
                flag=(String)theObj.get("flag");
            }
            }
             return flag;
            }
     
    public ArrayList<userCurrentLocation> getNearUserAlarmData(String profileId){
         
                    DB  db =  mongocon.getConnection();
                    DBCollection col = db.getCollection("nearUserAlarm");
                    DBCursor cursor = col.find();
                    BasicDBObject whereQuery = new BasicDBObject();
                    
                    ArrayList<userCurrentLocation> userAlarmLocation = new ArrayList<userCurrentLocation>();
                    
                    whereQuery.append("profileId", profileId);
                    cursor = col.find(whereQuery);
           // System.out.println("we are here");
                    {
                        while (cursor.hasNext()){
                        userCurrentLocation u1=new userCurrentLocation();
                        DBObject theObj = cursor.next(); 
                        String name=(String)theObj.get("name");
                
                        u1.setName(name);
                        u1.setUid((String)theObj.get("userId"));
                        u1.setLat((String)theObj.get("lat"));
                        u1.setLng((String)theObj.get("lang"));
                        u1.setPhoneNo((String)theObj.get("phoneNo"));
                        userAlarmLocation.add(u1);        
                        }
                         return userAlarmLocation;
           }
        }
         
    public boolean insertDataInNearUserAlarm(userCurrentLocation u){
        System.out.println("we are in insertDataInNearUserAlarm");
                DB  db =  mongocon.getConnection();
                DBCollection col = db.getCollection("nearUserAlarm");
                
                BasicDBObject doc = new BasicDBObject();
                doc.put("userId", u.getUid());
                doc.put("profileId", u.getProfileId());
                doc.put("name", u.getName());
                doc.put("lat", u.getLat());
                doc.put("lang", u.getLng());
                doc.put("phoneNo1", u.getPhoneNo());
                
                boolean status=false;
               if((col.save(doc).getN())==0){
                   status=true;
               }
                return status;
            }
            
    public boolean insertDataInNearUserFlag(userCurrentLocation u){
        
        System.out.println("we are in b");
                DB  db =  mongocon.getConnection();
                DBCollection col = db.getCollection("nearUserAlarm");
                
                BasicDBObject doc = new BasicDBObject();
                doc.put("userId", u.getUid());
                doc.put("profileId", u.getProfileId());
                doc.put("name", u.getName());
                doc.put("lat", u.getLat());
                doc.put("lang", u.getLng());
                doc.put("phoneNo1", u.getPhoneNo());
                
                boolean status=false;
               if((col.save(doc).getN())==0){
                   status=true;
               }
                return status;
            }
                       
    public void updateFlag(String profileId,String oldFlag,String newFlag){
                   
                    DB  db =  mongocon.getConnection();
                    DBCollection col = db.getCollection("nearUserFlag");
                    DBCursor cursor = col.find();
                    
                    BasicDBObject dbo2 = new BasicDBObject();
                    BasicDBObject dbo = new BasicDBObject();
                    dbo.put("flag", oldFlag);
                    BasicDBObject dbo1 = new BasicDBObject();
                    dbo2.put("flag", newFlag);
                    dbo2.put("profileId", profileId);
                    
                    cursor = col.find(dbo2);
                    while (cursor.hasNext()){
                        DBObject theObj = cursor.next();
                        if((theObj.get("profileId")).equals(profileId)){
                           col.update(dbo, dbo2);
                           System.out.println("Data update done !");
                        }
                    }
              }
  
    public String  getuserRegistrationInteger() throws UnknownHostException{
            
                DB  db =  mongocon.getConnection();
                DBCollection col = db.getCollection("registrationFlag");
                DBCursor cursor = col.find();
                BasicDBObject whereQuery = new BasicDBObject();
                String flag="";
             {
               while (cursor.hasNext()){                
                DBObject theObj = cursor.next(); 
                flag=(String)theObj.get("lastRegisteredInteger");
                }
            }
                 //System.out.println("=========//===========>"+flag);
             return flag;
            }
     
    public boolean saveNewRegistrationDetails(NewUserRegistrationBean nr){
                
            boolean status=false;
                DB  db =  mongocon.getConnection();
                DBCollection col = db.getCollection("registeredUsers");
                String name=nr.getFname()+" "+nr.getLname();
                BasicDBObject bdo = new BasicDBObject();
                
                bdo.put("name", name);
                bdo.put("emailID", nr.getEmail());
                bdo.put("phoneNo", nr.getPhno());
                bdo.put("password", nr.getPassword());
                bdo.put("profileId", nr.getProfileId());
                
               if((col.save(bdo).getN())==0){
                   status=true;
               }
                return status;
        }
    
    public boolean saveNewRegistrationFlag(String profileId){
                
            boolean status=false;
                DB  db =  mongocon.getConnection();
                DBCollection col = db.getCollection("registeredUsers");
                BasicDBObject bdo = new BasicDBObject();
                
                bdo.put("profileId", profileId);
                
               if((col.save(bdo).getN())==0){
                   status=true;
               }
                return status;
        }
    
    public void updateRegistrationFlag(String oldFlag,String newFlag){
                   
                    DB  db =  mongocon.getConnection();
                    DBCollection col = db.getCollection("registrationFlag");
                    DBCursor cursor = col.find();
                    BasicDBObject dbo2 = new BasicDBObject();
                    BasicDBObject dbo = new BasicDBObject();
                    dbo.put("lastRegisteredInteger", oldFlag);
                    BasicDBObject dbo1 = new BasicDBObject();
                    dbo2.put("lastRegisteredInteger", newFlag);
                    col.update(dbo, dbo2);
                    
                    }
    
    public boolean setFlag(String profileId){
        System.out.println("we are in setFlag");
                DB  db =  mongocon.getConnection();
                DBCollection col = db.getCollection("nearUserFlag");
                
                BasicDBObject doc = new BasicDBObject();
                doc.put("flag", "1");
                doc.put("profileId", profileId);
                
                boolean status=false;
               if((col.save(doc).getN())==0){
                   status=true;
               }
                return status;
            }
    
    public boolean saveNewLoginDetails(NewUserRegistrationBean nr){
                
            boolean status=false;
                DB  db =  mongocon.getConnection();
                DBCollection col = db.getCollection("login");
                String name=nr.getFname()+" "+nr.getLname();
                BasicDBObject bdo = new BasicDBObject();
                
                bdo.put("name", name);
                bdo.put("phoneNo", nr.getPhno());
                bdo.put("password", nr.getPassword());
                bdo.put("profileId", nr.getProfileId());
                
               if((col.save(bdo).getN())==0){
                   status=true;
               }
                return status;
        }
    
    // ************************** test codes ************************************//
    
    //***************************************************************************//
    public loginBean verifyLogin(String userId,String password){
                   
                    DB  db =  mongocon.getConnection();
                    DBCollection col = db.getCollection("login");
                    DBCursor cursor = col.find();
                    BasicDBObject whereQuery = new BasicDBObject();
                    whereQuery.append("profileId", userId);
                    whereQuery.append("password", password);
                    loginBean loginBean=new loginBean();
                    cursor = col.find(whereQuery);
                    {
                    
                        while (cursor.hasNext()){
                       
                        DBObject theObj = cursor.next(); 
                        String name=(String)theObj.get("name");
                        loginBean.setName(name);
                        loginBean.setProfileId((String)theObj.get("profileId"));
                        loginBean.setPassword((String)theObj.get("password"));
                        loginBean.setStatus("success");
                           System.out.println("name : "+name);
                        }
                    }
                    return loginBean;
           
    }
    
    public void deleteNearUserFlag(String profileId) throws UnknownHostException{
        DB  db =  mongocon.getConnection();
        DBCollection col = db.getCollection("nearUserFlag");
                
            
            DBCursor cursor = col.find();
                    BasicDBObject whereQuery = new BasicDBObject();
                    whereQuery.append("profileId", profileId);
                    
                    loginBean loginBean=new loginBean();
                    cursor = col.find(whereQuery);
                    col.remove(whereQuery);
                   
            
            
    }
}
