package com.bl.common.utils.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.expression.ParseException;

  
public class DateConverter implements Converter<String, Date> {
    private static Logger log = LoggerFactory.getLogger(DateConverter.class);
    private String[] patterns = new String[4];

    public DateConverter() {
//    	patterns[0] = "yyyy-MM-dd";
    	patterns[0] = "yyyy-MM-dd HH:mm";
    	patterns[1] = "yyyy-MM-dd";
    	patterns[2] = "yyyy/MM/dd HH:mm";
    	patterns[3] = "yyyy/MM/dd";
    }

    @Override
    public Date convert(String text) {
        if (text == null) {
            return null;
        }

        Date date = null;
        try {
        	if(text.indexOf("-")>1){
        		if(text.indexOf(":")>1){
        			 date = tryConvert(text, patterns[0]);
        		}else{
        			 date = tryConvert(text, patterns[1]);
        		}
        	}else if(text.indexOf("/")>1){
        		if(text.indexOf(":")>1){
        			 date = tryConvert(text, patterns[2]);
        		}else{
        			 date = tryConvert(text, patterns[3]);
        		}
        	}
        	
        	
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
       

        return date;
    }

    public static Date tryConvert(String text, String pattern) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);

        try {
            return dateFormat.parse(text);
        } catch (ParseException ex) {
            log.error(ex.getMessage(), ex);
        }

        return null;
    }

    public void setPatterns(String [] patterns) {
        this.patterns = patterns;
    }
    public static void main(String [] args) throws Exception{
    	String p = "yyyy-MM-dd HH:mm:ss";
    	String p2 = "yyyy-MM-dd";
    	String p3 = "yyyy/MM/dd HH:mm:ss";
    	String p4 = "yyyy/MM/dd";
    	String t = "2014/01/01 19:11:11";
    	if(t.indexOf("-")>1){
    		if(t.indexOf(":")>1){
    			System.out.println(tryConvert(t ,p));
    		}else{
    			System.out.println(tryConvert(t ,p2));
    		}
    	}else if(t.indexOf("/")>1){
    		if(t.indexOf(":")>1){
    			System.out.println(tryConvert(t ,p3));
    		}else{
    			System.out.println(tryConvert(t ,p4));
    		}
    	}
    	//System.out.println(tryConvert("2014-01-01" ,p));
    }
}
