package org.webapp.samrtfinder.myproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;

@WebServlet(
		name = "servlet",
		urlPatterns = "/FindPlace"
		)
public class Servlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		String inputText = req.getParameter("location");
		String type = req.getParameter("Type");
		PlaceType typeDummy = PlaceType.valueOf(type.toUpperCase());
		
		String inputAddress = new String();
		List<String> placeId = new ArrayList<String>();
		List<String> address = new ArrayList<String>();
		List<String> name = new ArrayList<String>();
		List<String> ratings = new ArrayList<String>();
		List<List<String>> openingHours = new ArrayList<List<String>>();
		LatLng latLng = new LatLng();
		GoogleApi googleApi = new GoogleApi();
		
		if(inputText!=null && !inputText.isEmpty()) {
			try {
				inputAddress = googleApi.getAddressFromlocation(inputText);
			} catch (ApiException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				latLng = googleApi.getCoord(inputAddress);
			} catch (ApiException e1) {
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			try {
				placeId = googleApi.nearbySearchRequest(latLng, typeDummy);
			} catch (ApiException e1) {
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			for(int i=0; i<placeId.size();i++) {
				try {
					address.add(googleApi.getAddressFromId(placeId.get(i)));
				} catch (ApiException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				try {
					openingHours.add(googleApi.getOpeningHours(placeId.get(i)));
				} catch (ApiException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				try {
					ratings.add(googleApi.getRating(placeId.get(i)));
				} catch (ApiException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				try {
					name.add(googleApi.getNameFromId(placeId.get(i)));
				} catch (ApiException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		req.setAttribute("openHours", openingHours);
		req.setAttribute("address", address);
		req.setAttribute("ratings", ratings);
		req.setAttribute("name", name);
		RequestDispatcher view = req.getRequestDispatcher("result.jsp");
		view.forward(req, resp);
	}
}
