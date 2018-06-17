/**
 * 
 */
package info.jsjackson.api.v1.model;

import lombok.Data;

/**
 * @author jsjackson
 *
 */
@Data
public class CustomerDTO {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String customerUrl;

}
