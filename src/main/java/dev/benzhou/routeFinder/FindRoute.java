package dev.benzhou.routeFinder;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.google.maps.model.DistanceMatrixRow;

import dev.benzhou.ds.Graph;
import dev.benzhou.ds.Path;

public class FindRoute {
	public static Scanner scanner;
	private DistanceMatrixRow[] distances;
	private Graph graph;
	private List<String> places;
	private boolean hasStart;
	private String startPlace;
	private String transportMethod;
	private String weightMethod;

	public FindRoute(List<String> places, String startPlace, String transportMethod, String weightMethod)
			throws IOException {

		this.weightMethod = weightMethod;
		this.transportMethod = transportMethod;
		this.places = places;

		ApiCom.initialiseApiCom();

		for (String place : this.places) {
			if (!ApiCom.checkPlaceExistance(place)) {
				throw new IOException("Invalid Location");
			}
		}

		if (startPlace.isBlank()) {
			hasStart = false;
		} else if (this.places.contains(startPlace)) {
			hasStart = true;
			this.startPlace = startPlace;
		} else {
			throw new IOException("Invalid Start Location");
		}
	}

	public Path start() throws Exception {
		distances = ApiCom.getDistances(getOrigins(), getDestinations(), transportMethod);

		try {
			graph = new Graph(places, distances, weightMethod);
		} catch (Exception e) {
			throw new Exception("No Valid Routes");
		}

		Path shortestPath;
		if (hasStart) {
			shortestPath = graph.getShortestPath(startPlace);
		} else {
			shortestPath = graph.getShortestPath();
		}

		return shortestPath;
	}

	public String[] getOrigins() {
		String[] origins = new String[places.size()];
		origins = places.toArray(origins);

		return origins;
	}

	public String[] getDestinations() {
		String[] destinations = new String[places.size()];
		destinations = places.toArray(destinations);

		return destinations;
	}

	public DistanceMatrixRow[] getDistances() {
		return distances;
	}
}
