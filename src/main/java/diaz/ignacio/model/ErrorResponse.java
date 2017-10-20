package diaz.ignacio.model;

/**
 * Class to return the error
 * @author ignaciodiazmaurino
 *
 */
public class ErrorResponse {

	private String errorMessage;
	private String httpStatusCode;

	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getHttpStatusCode() {
		return httpStatusCode;
	}
	public void setHttpStatusCode(String httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}
	
}
