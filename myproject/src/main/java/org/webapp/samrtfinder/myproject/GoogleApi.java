package org.webapp.samrtfinder.myproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import com.google.maps.model.RankBy;

public class GoogleApi {
	static String placeKey = "AIzaSyDwZfMV-6OB5kUk_BPOzGTjp5_IFh3OL-w";
	static String geoKey = "AIzaSyDwZfMV-6OB5kUk_BPOzGTjp5_IFh3OL-w";
	
	//get Address from location
	public String getAddressFromlocation(String location) throws ApiException, InterruptedException, IOException {
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey(geoKey)
			    .build();
		GeocodingResult[] results = GeocodingApi.geocode(context,location).await();
		String address;
		if (results[0].formattedAddress!=null) {
			address = (results[0].formattedAddress);
		}
		else {
			address = "No address Found. Can you try another address.";
		}
		return address;
	}
	
	//get placeId from location
	public String getPlaceId(String location) throws ApiException, InterruptedException, IOException {
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey(geoKey)
			    .build();
		GeocodingResult[] results = GeocodingApi.geocode(context,location).await();
		String placeId  = (results[0].placeId);
		return placeId;
	}
	
	//get coordinations from location
	public LatLng getCoord(String location) throws ApiException, InterruptedException, IOException {
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey(geoKey)
			    .build();
		GeocodingResult[] results = GeocodingApi.geocode(context,location).await();
		LatLng coords  = (results[0].geometry.location);
		return coords;
	}
	
	//get openingHours from placeId
	public List<String> getOpeningHours(String placeId) throws ApiException, InterruptedException, IOException {
		List<String> hours = new ArrayList<String>();
		if(placeId == null) {
			hours.add("No Information found");
			return hours;
		}
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey(placeKey)
			    .build();
		PlaceDetails placeDetails = PlacesApi.placeDetails(context, placeId).await();
		if(placeDetails.openingHours!=null) {
			for(int i=0; i<6;i++) {
				hours.add(placeDetails.openingHours.weekdayText[i]);
			}
		}else {
			hours.add("Opening Hours not avaliable.");
		}
		return hours;
	}
	
	//get address from placeId
	public String getAddressFromId(String placeId) throws ApiException, InterruptedException, IOException {
		String address = new String();
		if(placeId == null) {
			return "No Information found";
		}
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey(placeKey)
			    .build();
		PlaceDetails placeDetails = PlacesApi.placeDetails(context, placeId).await();
		if(placeDetails.formattedAddress!=null) {
			address = placeDetails.formattedAddress;
		}else {
			address= "Address is not found.";
		}
		return address;
	}
	//get name from placeId
	public String getNameFromId(String placeId) throws ApiException, InterruptedException, IOException {
		String name = new String();
		if(placeId == null) {
			return "No Information found";
		}
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey(placeKey)
			    .build();
		PlaceDetails placeDetails = PlacesApi.placeDetails(context, placeId).await();
		if(placeDetails.name!=null) {
			name = placeDetails.name;
		}else {
			name= "Name is not found.";
		}
		return name;
	}
	
	//get Ratings from placeId
	public String getRating(String placeId) throws ApiException, InterruptedException, IOException {
		String rating = new String();
		if(placeId == null) {
			return ("No opening hours found");
		}
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey(placeKey)
			    .build();
		PlaceDetails placeDetails = PlacesApi.placeDetails(context, placeId).await();
		if(placeDetails.reviews!=null) {
			PlaceDetails.Review review = placeDetails.reviews[0];
			rating = Integer.toString(review.rating);
		}else {
			return("No ratings found");
		}
		return rating;
	}
	//get locations from User Input
	public List<String> nearbySearchRequest(LatLng lat, PlaceType type) throws ApiException, InterruptedException, IOException{
		List<String> nearby = new ArrayList<String>();
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey(placeKey)
			    .build();
		PlacesSearchResponse results = PlacesApi.nearbySearchQuery(context, lat)
				.rankby(RankBy.DISTANCE)
				.type(type)
				.await();
		if(results.results!=null) {
			if(results.results.length>2) {
			PlacesSearchResult result1 = results.results[0];
			PlacesSearchResult result2 = results.results[1];
			PlacesSearchResult result3 = results.results[2];
			nearby.add(result1.placeId);
			nearby.add(result2.placeId);
			nearby.add(result3.placeId);
			}else if(results.results.length==2) {
				PlacesSearchResult result1 = results.results[0];
				PlacesSearchResult result2 = results.results[1];
				nearby.add(result1.placeId);
				nearby.add(result2.placeId);
			}else if(results.results.length==1) {
				PlacesSearchResult result1 = results.results[0];
				nearby.add(result1.placeId);
			}
		}
		return nearby;
	}
}
