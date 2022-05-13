 package com.automation.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class ReadPropertyFile {
	
	static String value;
	static InputStream inputstream;
	static Properties prop;
	
	public static String getPropertyvalue(String key){
		try {
			prop = new Properties();
			String  propFileName ="config.properties";
			String propFilePath=System.getProperty("user.dir")+"\\"+propFileName;
			
			inputstream = new FileInputStream(propFilePath);
			
			if(inputstream!=null){
				prop.load(inputstream);
			}
			else{
				throw new FileNotFoundException(propFileName + " is not found ");
			}
			
			Date time = new Date(System.currentTimeMillis());
			value = prop.getProperty(key);
			System.out.println(key +":: "+ value);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try{
				inputstream.close();
			} catch(IOException e){
				e.printStackTrace();
			}
		}
		return value;
	}

	public static void main(String args[]){
		ReadPropertyFile.getPropertyvalue("user");
	}
}
