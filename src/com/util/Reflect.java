package com.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Reflect 
{
    public static final String BEAN_PATH = "src/com/model";
	    
    public static final String XML_PATH = "src/com/xml";
    
    public static final List<String> modelList = new ArrayList<String>();
    
    public void getModels()
    {
    	File f = new File(BEAN_PATH);
    	File[] files = f.listFiles();
    	for(File file:files)
    	{
    		String fileName = file.getPath();
    		fileName = fileName.substring(4, fileName.length()-5);
    		String className = fileName.replaceAll("/", ".");
    		System.out.println(className);
    		modelList.add(className);
    	}
    }
    
    public void generateXML()
    {
    	for(int i=0;i<modelList.size();i++)
    	{
    		reflect(modelList.get(i));
    	}
    }
	
	public void reflect(String model)
    {
    	try 
    	{
    		Class<?> classType = Class.forName(model);
			Field[] fields = classType.getDeclaredFields();
			
			//generate a Hibernate.xml
			String xmlName = classType.getSimpleName()+".hbm.xml";
			File f = new File(XML_PATH);
			if(!f.exists())
			{
				f.mkdirs();
			}
						
			FileWriter fw = new FileWriter(XML_PATH+"/"+xmlName, false);
			BufferedWriter bw = new BufferedWriter(fw);
			
			//write header
			bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			bw.newLine();
			bw.write("<!DOCTYPE hibernate-mapping PUBLIC");
			bw.newLine();
			bw.write("    \"-//Hibernate/Hibernate Mapping DTD 3.0//EN\"");
			bw.newLine();
			bw.write("    \"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\">");
			bw.newLine();
			bw.write("<hibernate-mapping package=\""+getExplicitType(classType.getPackage().toString())+"\">");
			bw.newLine();
			bw.write("    <class name=\""+classType.getSimpleName()+"\" table=\""+classType.getSimpleName()+"\">");
			bw.newLine();
			
			//write the primary key
			bw.write("        <id name=\""+fields[0].getName()+"\" type=\""+getExplicitType(fields[0].getType().toString())+"\">");
			bw.newLine();
			bw.write("            <generator class=\"native\" />");
			bw.newLine();
			bw.write("        </id>");
			bw.newLine();
			
			//write body
			for(int i=1;i<fields.length;i++)
			{
				bw.write("        <property name=\""+fields[i].getName()+"\" type=\""+getExplicitType(fields[i].getType().toString())+"\">");
				bw.newLine();
				bw.write("            <column name=\""+fields[i].getName()+"\" />");
				bw.newLine();
				bw.write("        </property>");
				bw.newLine();
			}
			
			//write tail
			bw.write("    </class>");
			bw.newLine();
			bw.write("</hibernate-mapping>");
			bw.close();
		} 
    	catch (ClassNotFoundException e) 
    	{
			e.printStackTrace();
		} 
    	catch (IOException e) 
		{
			e.printStackTrace();
		}
    }
	
	public String getExplicitType(String type)
	{
		String exType = null;
		if(type.equals("int"))
		{
			exType = "java.lang.Integer";
		}
		else if(type.equals("double"))
		{
			exType = "java.lang.Double";
		}
		else if(type.contains("class"))
		{
			exType = type.substring(6);//class java.lang.Integer
		}
		else if(type.contains("package"))
		{
			exType = type.substring(8);//package com.model
		}
		return exType;
	}
	
	public static void main(String[] args)
	{
		Reflect r = new Reflect();
		r.getModels();
		r.generateXML();
	}
}
