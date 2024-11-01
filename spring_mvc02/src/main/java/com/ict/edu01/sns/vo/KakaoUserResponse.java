package com.ict.edu01.sns.vo;

public class KakaoUserResponse {
	private long id;
	private String connected_at;
	private Properties properties;
	private KakaoAccount kakao_acount;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getConnected_at() {
		return connected_at;
	}

	public void setConnected_at(String connected_at) {
		this.connected_at = connected_at;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public KakaoAccount getKakao_acount() {
		return kakao_acount;
	}

	public void setKakao_acount(KakaoAccount kakao_acount) {
		this.kakao_acount = kakao_acount;
	}
	
	public static class Properties{
		private String nickname, profile_image, thumbnail_image;

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

		public String getThumbnail_image() {
			return thumbnail_image;
		}

		public void setThumbnail_image(String thumbnail_image) {
			this.thumbnail_image = thumbnail_image;
		}
		
	}
	
	public static class KakaoAccount{
		private Profile profile;
		private String email;
		
		
		
		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public Profile getProfile() {
			return profile;
		}


		public void setProfile(Profile profile) {
			this.profile = profile;
		}


		public static class Profile{
			private String nickname, thumbnail_image_url, profile_image_url;

			public String getNickname() {
				return nickname;
			}

			public void setNickname(String nickname) {
				this.nickname = nickname;
			}

			public String getThumbnail_image_url() {
				return thumbnail_image_url;
			}

			public void setThumbnail_image_url(String thumbnail_image_url) {
				this.thumbnail_image_url = thumbnail_image_url;
			}

			public String getProfile_image_url() {
				return profile_image_url;
			}

			public void setProfile_image_url(String profile_image_url) {
				this.profile_image_url = profile_image_url;
			}
			
			
		}
	}

	
	
	
}
