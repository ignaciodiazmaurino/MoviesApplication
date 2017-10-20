package exceptions;

import org.springframework.http.HttpStatus;

/**
 * Class to handle the API errors  
 * @author ignaciodiazmaurino
 *
 */
public class ApiException extends Exception {

	private static final long serialVersionUID = -2203067690911508794L;
	private HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;

	/**
	 * Constructs a new Api exception
	 * 
	 * @param message		Message returned
	 * @param statusCode	The status code returned
	 */
	public ApiException(String message, HttpStatus statusCode) {
		super(message);
		this.statusCode = statusCode;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

}
