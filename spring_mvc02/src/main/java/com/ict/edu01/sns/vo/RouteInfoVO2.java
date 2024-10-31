package com.ict.edu01.sns.vo;

import java.util.List;

public class RouteInfoVO2 {
	private List<RouteVO2> routes;

	public class RouteVO2 {
		private List<SectionVO2> sections;

		public class SectionVO2 {
			private int distance;
			private int duration;
			private List<RoadVO2> roads;

			public class RoadVO2 {
				private String name;
				private int distance;
				private int duration;
				private double[] vertexes;

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

			public List<RoadVO2> getRoads() {
				return roads;
			}

			public void setRoads(List<RoadVO2> roads) {
				this.roads = roads;
			}
		}

		public List<SectionVO2> getSections() {
			return sections;
		}

		public void setSections(List<SectionVO2> sections) {
			this.sections = sections;
		}

	}

	public List<RouteVO2> getRoutes() {
		return routes;
	}

	public void setRoutes(List<RouteVO2> routes) {
		this.routes = routes;
	}

}
