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
import com.simplilearn.model.Flight;


public class FlightDao {

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

	public static List<Flight> getAllFlights(Flight flight) {
		
		List<Flight> flightList = new ArrayList<>();

		try {
			
			Connection con = FlightDao.getConnection();
			
			System.out.println("Connection Established");
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Flights WHERE source = ? AND destination = ?");

			ps.setString(1, flight.getSource());
			ps.setString(2, flight.getDestination());
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Flight f = new Flight();
				f.setId(rs.getInt(1));
				f.setSource(rs.getString(2));
				f.setDestination(rs.getString(3));
				f.setAirlines(rs.getString(4));
				f.setFare(rs.getDouble(5));

				flightList.add(f);
			}

			con.close();

		} catch (SQLException e3) {

			e3.printStackTrace();
			
		}
		
		return flightList;
	}
}

	
	
	