package com.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection 
{
    public final static String DRIVER = "com.mysql.jdbc.Driver";
    
    public final static String URL = "jdbc:mysql://localhost:3306/test";
	
	public static Connection getConnection()
    {
    	Connection conn = null;//创建用于连接数据库的Connection对象
    	try
    	{
    		//加载Mysql数据驱动
    		Class.forName(DRIVER);
    		//创建数据库连接
    		conn = DriverManager.getConnection(URL, "root", "");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return conn;
    }
	
	public static void getTables()
	{
		try 
		{
			Connection conn = getConnection();
			DatabaseMetaData md = conn.getMetaData();
			ResultSet rs = md.getTables(null, null, null, null);
			while(rs.next())
			{
				System.out.println("table " + rs.getString("TABLE_NAME"));
			}
			rs.close();
			conn.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void getAttrOfTable()
	{
		try 
		{
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			String sql = "describe User";
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			//获取字段名
			String sName = rsmd.getColumnName(1);
			System.out.println(sName);
			while(rs.next())
			{
				System.out.println(rs.getString(1));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		Connection conn = DBConnection.getConnection();
		try
		{
			//测试连接，看是否成功，打印false表示已经连接上数据库了
			System.out.println(conn.isClosed());
			getTables();
			getAttrOfTable();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}
