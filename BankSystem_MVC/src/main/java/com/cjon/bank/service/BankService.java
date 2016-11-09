package com.cjon.bank.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.cjon.bank.dao.BankDAO;
import com.cjon.bank.dto.BankDTO;
import com.cjon.bank.util.DBTemplate;

public class BankService {

	public BankDTO deposit(BankDTO dto) {
		
		DBTemplate template=new DBTemplate();
		
		BankDAO dao=new BankDAO(template);
		dto=dao.update(dto);
		
		if(dto.isResult()){
			template.commit();
		}else{
			template.rollback();
		}

		try {
			template.getCon().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dto;
		
	}

	public BankDTO withdraw(BankDTO dto) {
		DBTemplate template=new DBTemplate();
		
		BankDAO dao=new BankDAO(template);
		dto=dao.updateWithdraw(dto);
		
		if(dto.isResult()){
			template.commit();
		}else{
			template.rollback();
		}
		
		
		try {
			template.getCon().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dto;
	}

	public ArrayList<BankDTO> transfer(BankDTO dto1, BankDTO dto2) {
		
		
		DBTemplate template=new DBTemplate();
		BankDAO dao=new BankDAO(template);
		
		
		dto1=dao.updateWithdraw(dto1);
		dto2=dao.update(dto2);
		
		if(dto1.isResult() && dto2.isResult()){
			template.commit();
		}else{
			template.rollback();
		}
		
		
		ArrayList<BankDTO> list=new ArrayList<BankDTO>();
		list.add(dto1);
		list.add(dto2);
		
		return list;
		
	}

}
