package com.simplilearn.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.mysql.cj.protocol.Resultset;
import com.simplilearn.model.User;


public class UserDao {

	public static Connection getConnection() {
		Connection con = null;

		try {
			
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyAway", "root", "admin123");
			
		} catch (ClassNotFoundException e1) {

			e1.printStackTrace();
			
		} catch (SQLException e2) {

			e2.printStackTrace();
		}

		return con;

	}

	public static int save(User user) {
		
		int status = 0;

		try {
			
			Connection con = UserDao.getConnection();
			
			System.out.println("Connection Established");
			
			PreparedStatement ps = con.prepareStatement("insert into Users values(?,?,?,?,?)");

			ps.setInt(1, new Random().nextInt(50));
			ps.setString(2, user.getName());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getCountry());

			status = ps.executeUpdate();

			con.close();

		} catch (SQLException e3) {

			e3.printStackTrace();
			
		}
		return status;
	}

	
	
	public static boolean authenticate(User user) {
		
		boolean validUser = false;
		
			
		try {
			
			// Obtain a connection to the database;
			Connection con = UserDao.getConnection();
			
			System.out.println("Connection Estabished and checking user");
			
			// preparing a SQL statement
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Users WHERE EMAIL = ? AND PASSWORD = ?");
			
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				validUser = true;
			}
			
			con.close();
		} catch (SQLException e4) {

			e4.printStackTrace();
			
		}
		
		return validUser;
	}

	
	public static int update(User user) {
		int status = 0;

		try {
			Connection con = UserDao.getConnection();
			PreparedStatement ps = con.prepareStatement("update Users set NAME=?,PASSWORD=?,EMAIL=?,COUNTRY=? where ID=?");

			
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getCountry());
			ps.setInt(5, user.getId());

			status = ps.executeUpdate();

			con.close();

		} catch (SQLException e6) {
			
			e6.printStackTrace();
		}
		return status;
	}
	
}
