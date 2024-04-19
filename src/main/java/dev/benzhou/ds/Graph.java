package dev.benzhou.ds;

import java.util.ArrayList;
import java.util.List;

import com.google.maps.model.DistanceMatrixRow;

public class Graph {
	protected List<Node<String>> nodes;

	public Graph(List<String> places, DistanceMatrixRow[] distances, String weightMethod) {
		nodes = new ArrayList<>();

		for (String place : places) {
			nodes.add(new Node<>(place));
		}

		if (weightMethod.equals("DISTANCE")) {
			for (int i = 0; i < nodes.size(); i++) {
				for (int j = 0; j < distances[i].elements.length; j++) {
					if (i != j) {
						Edge<Node<String>> edge = new Edge<>(nodes.get(i), nodes.get(j),
								distances[i].elements[j].distance.inMeters);
						nodes.get(i).addEdge(edge);
					}
				}
			}
		} else if (weightMethod.equals("DURATION")) {
			for (int i = 0; i < nodes.size(); i++) {
				for (int j = 0; j < distances[i].elements.length; j++) {
					if (i != j) {
						Edge<Node<String>> edge = new Edge<>(nodes.get(i), nodes.get(j),
								distances[i].elements[j].duration.inSeconds);
						nodes.get(i).addEdge(edge);
					}
				}
			}
		}
	}

	public Path getShortestPath() {
		List<Path> paths = new ArrayList<>();

		for (Node<String> n : nodes) {
			paths.add(getShortestPath(n, new Path(), nodes));
		}

		return getLowestCost(paths);
	}

	public Path getShortestPath(String startNode) {
		Node<String> node = null;
		for (Node<String> t : nodes) {
			if (t.getLabel().equals(startNode)) {
				node = t;
			}
		}

		return (getShortestPath(node, new Path(), nodes));
	}

	private Path getShortestPath(Node<String> startNode, Path path, List<Node<String>> remainingNodes) {
		if (remainingNodes.size() == 1) {
			Path currentPath = new Path(path);
			currentPath.add(remainingNodes.get(0));
			return currentPath;
		} else if (remainingNodes.size() == nodes.size()) {
			Path currentPath = new Path(path);
			currentPath.add(startNode);

			return getShortestPath(startNode, currentPath, getRemainingNodes(remainingNodes, startNode));
		} else {
			List<Path> paths = new ArrayList<>();

			for (Node<String> n : remainingNodes) {
				Path currentPath = new Path(path);
				currentPath.add(n);
				paths.add(getShortestPath(startNode, currentPath, getRemainingNodes(remainingNodes, n)));
			}
			return getLowestCost(paths);
		}
	}

	private Path getLowestCost(List<Path> paths) {
		Path shortestPath = new Path();
		shortestPath = paths.get(0);
		for (int i = 1; i < paths.size(); i++) {
			if (paths.get(i).getCost() < shortestPath.getCost()) {
				shortestPath = paths.get(i);
			}
		}
		return shortestPath;
	}

	private List<Node<String>> getRemainingNodes(List<Node<String>> nodes, Node<String> excludeNode) {
		List<Node<String>> nodesArray = new ArrayList<>();

		for (Node<String> node : nodes) {
			if (!excludeNode.equals(node)) {
				nodesArray.add(node);
			}
		}

		return nodesArray;
	}
}
