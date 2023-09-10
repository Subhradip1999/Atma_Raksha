/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.commons;

import com.dao.daoImpl;
import java.net.UnknownHostException;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;

public class commons {
    
    public String getSystemYear(){
        String year="0000";
        Date d=new Date();
        int ye= YearMonth.now().getYear();
        year=String.valueOf(ye); 
        return year;
    }
    
    public String getUserRegistrationInteger() throws UnknownHostException{
        String lastNumber="0000";
        daoImpl dao=new daoImpl();
        lastNumber=dao.getuserRegistrationInteger();
        return lastNumber;
    }
    
    
    public String getNewProfileId() throws UnknownHostException{
        String profileId="9999";
        commons cm=new commons();
        daoImpl dao=new daoImpl();
        
        if(cm.getSystemYear().equalsIgnoreCase(cm.getUserRegistrationInteger().substring(0, 4))){
            int data=Integer.valueOf(cm.getUserRegistrationInteger())+1;
            profileId=String.valueOf(data);
            dao.updateRegistrationFlag(cm.getUserRegistrationInteger(),profileId);
            
        }else{
            profileId=cm.getSystemYear()+"0000";
            dao.updateRegistrationFlag(cm.getUserRegistrationInteger(),profileId);
        }
        return profileId;
    }
    
    public void getNoOfDigitInInteger(){
        int a=0000;
        //20210001
        
        String data="20210001";
        System.out.println("==>"+data.substring(0, 4));
    }
}