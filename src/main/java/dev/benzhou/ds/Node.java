package dev.benzhou.ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node<T> {
	private T label;
	private List<Edge<Node<String>>> edges;

	public Node(T v) {
		label = v;
		edges = new ArrayList<>();
	}

	public T getLabel() {
		return label;
	}

	public List<Edge<Node<String>>> getEdges() {
		return edges;
	}

	public Edge<Node<String>> getEdge(Node<String> target) {
		for (Edge<Node<String>> edge : edges) {
			if (edge.getTarget().equals(target)) {
				return edge;
			}
		}

		return null;
	}

	public void addEdge(Edge<Node<String>> edge) {
		edges.add(edge);
	}

	@Override
	public int hashCode() {
		return Objects.hash(label);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node<T> other = (Node<T>) obj;
		return Objects.equals(label, other.label);
	}

	@Override
	public String toString() {
		return (String) label;
	}

}
