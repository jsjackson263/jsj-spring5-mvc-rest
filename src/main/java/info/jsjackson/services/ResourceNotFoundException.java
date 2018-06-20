/**
 * 
 */
package info.jsjackson.services;

/**
 * @author jsjackson
 *
 */
public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {
	}

	/**
	 * @param message
	 */
	public ResourceNotFoundException(String message) {
		super(message);
	}
	
	/**
	 * @param message
	 * @param cause
	 */
	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	

	/**
	 * @param cause
	 */
	public ResourceNotFoundException(Throwable cause) {
		super(cause);
	}
	
	
	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	

	


}
