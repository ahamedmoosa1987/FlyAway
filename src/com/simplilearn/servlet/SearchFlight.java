package com.simplilearn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.model.Flight;
import com.simplilearn.dao.FlightDao;

public class SearchFlight extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		res.setContentType("text/html");

		PrintWriter pw = res.getWriter();

		String source = req.getParameter("source");
		String destination = req.getParameter("destination");
		
		
		Flight flight = null;
		
		if (source != null && destination != null && !source.equals("") && !destination.equals("")) {
			
			flight = new Flight();
			
			flight.setSource(source);
			flight.setDestination(destination);
			
			
		}
		
		
		System.out.println(flight.getSource());
		System.out.println(flight.getDestination());
		
		
		List<Flight> flightList = FlightDao.getAllFlights(flight);
		
		
		pw.print("<b>Below are the flights for you </b><br><br>");
		pw.print("<table border='1' width='100%'>");
		pw.print("<tr><th>Departure</th><th>Destination</th><th>Airlines</th><th>Fare</th><th>Action</th></tr>");

		for (Flight f : flightList) {

			pw.print("<tr><td>" + f.getSource() + "</td><td>" + f.getDestination() + "</td><td>" + f.getAirlines()
					+ "</td><td>" + f.getFare() + "</td><td><a href='book.html'>Book Now</a></td></tr>");
		}
		
		pw.print("</table>");

		pw.close();

	}

}

