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
    	Connection conn = null;//���������������ݿ��Connection����
    	try
    	{
    		//����Mysql��������
    		Class.forName(DRIVER);
    		//�������ݿ�����
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
			//��ȡ�ֶ���
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
			//�������ӣ����Ƿ�ɹ�����ӡfalse��ʾ�Ѿ����������ݿ���
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
