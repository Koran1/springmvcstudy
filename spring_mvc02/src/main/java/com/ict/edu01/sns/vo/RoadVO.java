package com.ict.edu01.sns.vo;

import java.util.List;

public class RoadVO {
	private String name;
	private int distance;
	private int duration;
	private double[] vertexes;
	private List<CoordinateVO> coordinates; // List of coordinate pairs

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double[] getVertexes() {
		return vertexes;
	}

	public void setVertexes(double[] vertexes) {
		this.vertexes = vertexes;
	}

	public List<CoordinateVO> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<CoordinateVO> coordinates) {
		this.coordinates = coordinates;
	}

}
