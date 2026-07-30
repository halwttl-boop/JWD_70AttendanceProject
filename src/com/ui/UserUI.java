package com.ui;

import java.util.Scanner;

import com.model.UserBean;
import com.repositary.*;

public class UserUI {
	
	UserRepository userRepo;
	
	Scanner scan = new Scanner (System.in);
	
	public void showMessage() {
		
		System.out.println("------Welcome!------");
		System.out.println("1. Create Account\n 2. Login");
		System.out.print("Please choose option : ");
		int option = scan.nextInt();
		
		switch (option) {
		
		case 1 : 
			createUser();
		    break;
		
		case 2 : 
			loginUser();
		    break;
		
		default :
			System.out.println("Exit System");
		}
 	}
	
	public void loginUser() {
		
		System.out.print("Enter ID (USR_) : ");
		String id = scan.next();
		userRepo = new UserRepository();
		UserBean obj = userRepo.getByUserId(id);
		
		if (obj == null) {
			System.out.println("No User Found!");
		}else {
			int i = userRepo.checkInUser(obj.getId());
			if (i > 0) {
				System.out.println("Welcome " + obj.getUser_name());
			}else {
				System.out.println("Already Login\n Logout First (3)");
				int option =  scan.nextInt();
				
				switch (option) {
				case 3: 
					userRepo.checkOutUser(obj.getId());
					showMessage();
					break;
				default:
					System.out.println("");
				}
			}
		}
 	}

	public void createUser() { 
		
		System.out.print("Enter Name:");
		String name = scan.nextLine();
		
		System.out.print("Enter gender:");
		String gender = scan.next();
		
		UserBean user = new UserBean(name, getUserId(), gender);
		userRepo = new UserRepository();
		int userId = userRepo.createUser(user);
		String id = userRepo.getUserId(userId);
		System.out.println("Create Successfull");
		System.out.println("Your Id: " + id);
	}
	
	public String getUserId() { 
		
		int rows = 0;
		userRepo = new UserRepository();
		rows = userRepo.getUserRowCount();
		if (rows != 0) {
			return "USR" + (rows + 1);
		}else {
			return "USR1";
		}
		
	}

}
