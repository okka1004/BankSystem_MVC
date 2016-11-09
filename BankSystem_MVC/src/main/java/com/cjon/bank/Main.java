package com.cjon.bank;

import java.util.ArrayList;
import java.util.Scanner;

import com.cjon.bank.dto.BankDTO;
import com.cjon.bank.service.BankService;

public class Main {

	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		int menu=0;
		
		do{
			System.out.println("-----은행시스템------");
			System.out.println("1. 입금");
			System.out.println("2. 출금");
			System.out.println("3. 이체");
			System.out.println("4. 종료");
			System.out.println("사용할 메뉴를 입력하세요 -->");
			String menuString=s.nextLine();

			menu=Integer.parseInt(menuString);
			
			if(menu==1){
				System.out.println("입금업무입니다");
				System.out.println("입금할 사람을 입력하세요 -->");
				String id=s.nextLine();
				System.out.println("입금할 금액을 입력하세요 -->");
				
				String moneyString=s.nextLine();
				int money=Integer.parseInt(moneyString);
				
				BankDTO dto=new BankDTO();				
				dto.setUserid(id);
				dto.setBalance(money);
				
				
				BankService service=new BankService();
				dto=service.deposit(dto);
				System.out.println("처리결과===");
				System.out.println("id : "+dto.getUserid()+" / 잔액 : "+dto.getBalance());
				
			}else if(menu==2){
				System.out.println("출금업무입니다");
				System.out.println("출금할 사람을 입력하세요 -->");
				String id=s.nextLine();
				System.out.println("출금할 금액을 입력하세요 -->");
				
				String moneyString=s.nextLine();
				int money=Integer.parseInt(moneyString);
				
				BankDTO dto=new BankDTO();				
				dto.setUserid(id);
				dto.setBalance(money);
				
				
				BankService service=new BankService();
				dto=service.withdraw(dto);
				System.out.println("처리결과===");
				System.out.println("id : "+dto.getUserid()+" / 잔액 : "+dto.getBalance());
			}else if(menu==3){
				System.out.println("이체업무입니다");
				System.out.println("출금할 사람을 입력하세요 -->");
				String id1=s.nextLine();
				System.out.println("입금 받을 사람을 입력하세요 -->");
				String id2=s.nextLine();
				System.out.println("이체할 금액을 입력하세요 -->");
				
				String moneyString=s.nextLine();
				int money=Integer.parseInt(moneyString);
				
				BankDTO dto1=new BankDTO();				
				dto1.setUserid(id1);
				dto1.setBalance(money);
				
				BankDTO dto2=new BankDTO();	
				dto2.setUserid(id2);
				dto2.setBalance(money);
				
				BankService service=new BankService();
				ArrayList<BankDTO> list=service.transfer(dto1, dto2);
				
				dto1=list.get(0);
				dto2=list.get(1);
				
				System.out.println("처리결과===");
				System.out.println("출금id : "+dto1.getUserid()+" / 잔액 : "+dto1.getBalance()+"이체받은 id : "+dto2.getUserid()+" / 잔액 : "+dto2.getBalance());
			}else if(menu==4){
				System.out.println("시스템을 종료합니다");
			}
		}while(menu != 4);
	}

}
