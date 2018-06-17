/**
 * 
 */
package info.jsjackson.api.v1.model;

import java.util.List;

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
public class CustomerListDTO {
	
	List<CustomerDTO> customers;

}
