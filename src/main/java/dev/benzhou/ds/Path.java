package dev.benzhou.ds;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.text.WordUtils;

public class Path {
	private long cost;
	private List<Long> costArray;
	private List<Node<String>> path;

	public Path() {
		path = new ArrayList<>();
		costArray = new ArrayList<>();
		cost = 0;
	}

	public Path(Path path) {
		this.path = new ArrayList<>();
		this.costArray = new ArrayList<>();
		for (Node<String> n : path.getPath()) {
			this.path.add(n);
		}
		for (Long c : path.getCostArray()) {
			this.costArray.add(c);
		}
		this.cost = path.getCost();
	}

	public void add(Node<String> node) {
		if (path.isEmpty()) {
			path.add(node);
			return;
		} else {
			Node<String> t = path.get(path.size() - 1);
			List<Edge<Node<String>>> edges = t.getEdges();
			for (Edge<Node<String>> edge : edges) {
				if (edge.getTarget().equals(node)) {
					cost += edge.getWeight();
					costArray.add(edge.getWeight());
					path.add(node);
					return;
				}
			}
		}
	}

	public String[] toArray() {
		String[] pathArray = new String[path.size()];

		int i = 0;
		for (Node<String> n : path) {
			pathArray[i] = WordUtils.capitalizeFully(n.getLabel());
			i++;
		}
		return pathArray;
	}

	public long getCost() {
		return cost;
	}

	private List<Long> getCostArray() {
		return costArray;
	}

	public String[] getCostArray(String weightMethod) {
		String[] costStringArray = new String[costArray.size()];

		int i = 0;
		if (weightMethod.equals("DURATION")) {
			for (Long c : costArray) {
				costStringArray[i] = convertToHumanReadableTime(c);
				i++;
			}
		} else if (weightMethod.equals("DISTANCE")) {
			for (Long c : costArray) {
				costStringArray[i] = convertToHumanReadableDistance(c);
				i++;
			}
		}

		return costStringArray;
	}

	public String getHumanReadableCost(String weightMethod) {
		if (weightMethod.equals("DURATION")) {
			return convertToHumanReadableTime(cost);

		} else {
			return convertToHumanReadableDistance(cost);
		}
	}

	private String convertToHumanReadableDistance(Long c) {
		float km = Long.valueOf(c / 1000).floatValue();
		float metres = Long.valueOf((long) (c - km * 1000)).floatValue();
		km += metres / 1000;

		return String.format("%.1f kilometre(s)", km);
	}

	private String convertToHumanReadableTime(Long c) {
		int hours = Long.valueOf(c / 3600).intValue();
		int minutes = Long.valueOf((c - hours * 3600) / 60).intValue();

		StringBuilder sb = new StringBuilder();

		if (hours > 0) {
			sb.append(hours);
			if (minutes > 0) {
				sb.append(" hour(s), ");
				sb.append(minutes);
				sb.append(" minute(s)");
			} else {
				sb.append(" hour(s)");
			}
		} else {
			sb.append(minutes);
			sb.append(" minute(s)");
		}

		return sb.toString();
	}

	public List<Node<String>> getPath() {
		return path;
	}

	public boolean isEmpty() {
		return path.isEmpty();
	}

	public int size() {
		return path.size();
	}

	public void print() {
		for (Node<String> n : path) {
			System.out.println(n);
		}

	}
}
