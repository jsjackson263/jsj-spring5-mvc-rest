/**
 * 
 */
package info.jsjackson.api.v1.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jsjackson
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
	
	private Long id;
	private String firstName;
	private String lastName;
	
	@JsonProperty("customer_url")
	private String customerUrl;

}
