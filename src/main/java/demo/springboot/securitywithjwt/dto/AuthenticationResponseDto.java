package demo.springboot.securitywithjwt.dto;

public class AuthenticationResponseDto {
	
	private String message;
	private String jwt;
	
	public AuthenticationResponseDto() {
		super();
	}

	public AuthenticationResponseDto(String message, String jwt) {
		super();
		this.message = message;
		this.jwt = jwt;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	@Override
	public String toString() {
		return "AuthenticationResponseDto [message=" + message + ", jwt=" + jwt + "]";
	}
	
}
