package com.company.myorders.mobile.model.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import oracle.adfmf.bindings.dbf.AmxIteratorBinding;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class OrdersUtilityBean {
    public OrdersUtilityBean() {
        super();
    }
    public String toDateTime(String value){
        SimpleDateFormat intialFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        SimpleDateFormat newFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String newDate = null;
        try {
            if(value!=null){
            Date date = intialFormat.parse(value);
            newDate = newFormat.format(date);   
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }
    
    public String toDateTimeTZ(String value){
        SimpleDateFormat intialFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        SimpleDateFormat newFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String newDate = null;
        try {
            if(value!=null){
            Date date = intialFormat.parse(value);
            newDate = newFormat.format(date);   
            }
        } catch (ParseException e) {
            e.printStackTrace();
            newDate=value;
        }
        return newDate;
    }


}
