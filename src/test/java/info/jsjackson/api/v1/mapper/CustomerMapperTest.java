/**
 * 
 */
package info.jsjackson.api.v1.mapper;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import info.jsjackson.api.v1.model.CustomerDTO;
import info.jsjackson.domain.Customer;

/**
 * @author jsjackson
 *
 */
public class CustomerMapperTest {

	public static final long ID = 1L;
	public static final String FIRST_NAME = "Joe";
	public static final String LAST_NAME = "Blog";
	
	
	CustomerMapper mapper = CustomerMapper.INSTANCE;
	

	@Test
	public void testMapCustomerToCustomerDTO() {

		//Given
		Customer customer = new Customer();
		customer.setId(ID);
		customer.setFirstName(FIRST_NAME);
		customer.setLastName(LAST_NAME);
		
		//When
		CustomerDTO customerDTO = mapper.customerToCustomerDTO(customer);
		
		
		//Then
		assertEquals(Long.valueOf(1L), customerDTO.getId());
		assertEquals(FIRST_NAME, customerDTO.getFirstName());
		assertEquals(LAST_NAME, customerDTO.getLastName());
		
		
	
	
	}

}
