package com.repositary;

import java.sql.Connection;    
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.model.UserBean;

public class UserRepository {

public int createUser (UserBean obj) {
		
		int i = 0;
		
		String sql = "insert into user (user_name, userId, gender) values (?, ?, ?)";
		
		try(
			Connection connection = DBConnection.getConnection2();
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
		    ){
		
			ps.setString(1, obj.getUser_name());
			ps.setString(2, obj.getUserId());
			ps.setString(3, obj.getGender());
			
			i = ps.executeUpdate();
			if (i > 0) {
				try (ResultSet rs = ps.getGeneratedKeys()) {
					if (rs.next()) {
						i= rs.getInt(1);
					}
 				}
			}
			
		
		} catch (SQLException e) {
			System.out.println("Insert error: " + e.getMessage());
		}
		
		return i;
	}


public int getUserRowCount(){
	
	int i = 0;
		
		String sql = "select count(*) as user_count from user";
				
		try(
			Connection con = DBConnection.getConnection2();
			PreparedStatement ps = con.prepareStatement(sql)
		   ){
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				
				i = rs.getInt("user_count");
				
			}
			
		}catch (SQLException e) {
			System.out.println("Get rows error: " + e.getMessage());
		}
		
		return i;
	}
	

public String getUserId(int id){
	
	    String userId = null;
		
		String sql = "select userId from user where id =?";
				
		try(
			Connection con = DBConnection.getConnection2();
			PreparedStatement ps = con.prepareStatement(sql)
		   ){
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				
				userId = rs.getString("userId");
				
			}
			
		}catch (SQLException e) {
			System.out.println("Get user Id error: " + e.getMessage());
		}
		
		return userId;
	}

public UserBean getByUserId(String userId){
	
    UserBean obj = null;
	
	String sql = " select * from user where userId = ?";
			
	try(
		Connection con = DBConnection.getConnection2();
		PreparedStatement ps = con.prepareStatement(sql)
	   ){
		
		ps.setString(1, userId);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {		
			obj = new UserBean();
			obj.setId(rs.getInt("id"));
			obj.setUser_name(rs.getString("user_name"));
			obj.setGender(rs.getString("gender"));
			obj.setUserId(rs.getString("userId"));
		}
		
	}catch (SQLException e) {
		System.out.println("Get user error: " + e.getMessage());
	}
	
	return obj;
}

public int checkInUser (int userId) {
	
	int i = 0;
	
	String sql = " insert into attendance(user_id) values (?)";
	
	
	try(
		Connection connection = DBConnection.getConnection2();
		PreparedStatement ps = connection.prepareStatement(sql)
	    ){
	
		ps.setInt(1, userId);
		
		
		i = ps.executeUpdate();
		
	} catch (SQLException e) {
		System.out.println("Check in error: " + e.getMessage());
	}
	
	return i;
}

public int checkOutUser (int userId) {
	
	int i = 0;
	
	String sql = " update attendance set check_out = ? where user_id = ? ";
	
	
	try(
		Connection connection = DBConnection.getConnection2();
		PreparedStatement ps = connection.prepareStatement(sql)
	    ){
	
		ps.setTime(1, Time.valueOf(LocalTime.now()));
		ps.setInt(2, userId);
		
		
		i = ps.executeUpdate();
		
	} catch (SQLException e) {
		System.out.println("Check out user error: " + e.getMessage());
	}
	
	return i;
}


	
}
