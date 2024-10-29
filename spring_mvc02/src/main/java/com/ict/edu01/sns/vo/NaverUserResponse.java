package com.ict.edu01.sns.vo;

public class NaverUserResponse {
	private String resultcode, message;
	private Response response;
	
	
	public String getResultcode() {
		return resultcode;
	}


	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Response getResponse() {
		return response;
	}


	public void setResponse(Response response) {
		this.response = response;
	}


	public static class Response{
		private String id, nickname, profile_image, age, gender, email, name, birthday, birthyear;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public String getProfile_image() {
			return profile_image;
		}

		public void setProfile_image(String profile_image) {
			this.profile_image = profile_image;
		}

		public String getAge() {
			return age;
		}

		public void setAge(String age) {
			this.age = age;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getBirthday() {
			return birthday;
		}

		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}

		public String getBirthyear() {
			return birthyear;
		}

		public void setBirthyear(String birthyear) {
			this.birthyear = birthyear;
		}
		
	}
}
