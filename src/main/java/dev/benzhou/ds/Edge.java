package dev.benzhou.ds;

import java.util.Objects;

public class Edge<T> {
	private T source;
	private T target;
	private long weight;

	public Edge(T source, T target, long weight) {
		this.source = source;
		this.target = target;
		this.weight = weight;
	}

	public T getSource() {
		return source;
	}

	public T getTarget() {
		return target;
	}

	public long getWeight() {
		return weight;
	}

	@Override
	public int hashCode() {
		return Objects.hash(source, target);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge<T> other = (Edge<T>) obj;
		return Objects.equals(source, other.source) && Objects.equals(target, other.target)
				&& Objects.equals(weight, other.weight);
	}

	@Override
	public String toString() {
		return "Edge {source=" + source + ", target=" + target + ", weight=" + weight + "}";
	}

}
