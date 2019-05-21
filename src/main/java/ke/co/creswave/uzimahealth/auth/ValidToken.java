package ke.co.creswave.uzimahealth.auth;

public class ValidToken {
	private String token;
	private String expiresAfter;
	private String status;
	private String rights;
	
	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getExpiresAfter() {
		return expiresAfter;
	}

	public void setExpiresAfter(String expiresAfter) {
		this.expiresAfter = expiresAfter;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
