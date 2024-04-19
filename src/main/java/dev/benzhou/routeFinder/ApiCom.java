package dev.benzhou.routeFinder;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.FindPlaceFromTextRequest.InputType;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixRow;
import com.google.maps.model.FindPlaceFromText;
import com.google.maps.model.TravelMode;

public class ApiCom {
	private static GeoApiContext context;
	private static DistanceMatrix matrix;

	private static String API_KEY = "AIzaSyAZr6pUnQToZIHR8lqH9Mn5QnRFb4b8rNc";

	public static void initialiseApiCom() {
		context = new GeoApiContext.Builder().apiKey(System.getenv(API_KEY)).build();
	}

	public static GeoApiContext getContext() {
		return context;
	}

	public static DistanceMatrixRow[] getDistances(String[] origins, String[] destinations, String transportMethod) {
		try {
			switch (transportMethod) {
				case "DRIVING":
					matrix = DistanceMatrixApi.newRequest(getContext()).origins(origins).destinations(destinations)
							.mode(TravelMode.DRIVING).await();
					break;
				case "TRANSIT":
					matrix = DistanceMatrixApi.newRequest(getContext()).origins(origins).destinations(destinations)
							.mode(TravelMode.TRANSIT).await();
					break;
				case "WALKING":
					matrix = DistanceMatrixApi.newRequest(getContext()).origins(origins).destinations(destinations)
							.mode(TravelMode.WALKING).await();
					break;
			}
		} catch (Exception e) {
			System.out.println("Failed to get distances");
			return null;
		}

		return matrix.rows;
	}

	public static boolean checkPlaceExistance(String place) {
		try {
			FindPlaceFromText placesResult = PlacesApi.findPlaceFromText(getContext(), place, InputType.TEXT_QUERY)
					.await();
			if (placesResult.candidates.length > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Failed to check place existance");
			return false;
		}
	}
}
