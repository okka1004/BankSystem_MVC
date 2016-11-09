package com.cjon.bank.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBTemplate {
	
	private Connection con;
	
	
	public DBTemplate() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/library";
			String id="jQuery";
			String pw="jQuery";
			
			con=DriverManager.getConnection(url, id, pw);
			
			con.setAutoCommit(false);//트랜젝션 시작
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	public void commit(){
		try {
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void rollback(){
		try {
			con.rollback();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public Connection getCon(){
		return con;
	}
	
	public void setCon(Connection con){
		this.con=con;
	}
	
	
	
	
}
