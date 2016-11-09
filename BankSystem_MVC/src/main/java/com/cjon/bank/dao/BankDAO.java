package com.cjon.bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cjon.bank.dto.BankDTO;
import com.cjon.bank.util.DBTemplate;

public class BankDAO {

	private DBTemplate template;
	
	
	public BankDAO(DBTemplate template) {
		super();
		this.template = template;
	}

	public BankDTO update(BankDTO dto){
		
		Connection con=template.getCon();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
				
			String sql="update bank set balance = balance+? where userid=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, dto.getBalance());
			pstmt.setString(2, dto.getUserid());
			
			int count=pstmt.executeUpdate();
			
			if(count==1){
				String sql1="select userid, balance from bank where userid=?";
				PreparedStatement pstmt1=con.prepareStatement(sql1);
				
				pstmt1.setString(1, dto.getUserid());
				rs=pstmt1.executeQuery();
				
				if(rs.next()){
					dto.setBalance(rs.getInt("balance"));
				}
				
				dto.setResult(true);
				
				try{
					rs.close();
					pstmt1.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				dto.setResult(false);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try{
				pstmt.close();
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return dto;
	}

	public BankDTO updateWithdraw(BankDTO dto) {
		Connection con=template.getCon();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			String sql="update bank set balance = balance-? where userid=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, dto.getBalance());
			pstmt.setString(2, dto.getUserid());
			
			int count=pstmt.executeUpdate();
			
			if(count==1){
				String sql1="select userid, balance from bank where userid=?";
				PreparedStatement pstmt1=con.prepareStatement(sql1);
				
				pstmt1.setString(1, dto.getUserid());
				rs=pstmt1.executeQuery();
				
				if(rs.next()){
					dto.setBalance(rs.getInt("balance"));
				}
				
				if(dto.getBalance() <0){
					System.out.println("예금 금액이 작아서 출금할수없어요");
					dto.setResult(false);
					
				}else{
					dto.setResult(true);
				}
				try{
					rs.close();
					pstmt1.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		
		}finally {
			try{
				pstmt.close();
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return dto;
	}
	
}
