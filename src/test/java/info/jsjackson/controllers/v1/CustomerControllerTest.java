/**
 * 
 */
package info.jsjackson.controllers.v1;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import info.jsjackson.api.v1.model.CustomerDTO;
import info.jsjackson.services.CustomerService;

/**
 * @author jsjackson
 *
 */
public class CustomerControllerTest {

	public static Long ID1 = 1L;
	public static String FIRST_NAME1 = "Jim";
	public static String LAST_NAME1 = "Jones";
	public static String CUSTOMER_URL1 = "/api/v1/customer/1";
	
	public static Long ID2 = 2L;
	public static String FIRST_NAME2 = "Joe";
	public static String LAST_NAME2 = "Blog";
	public static String CUSTOMER_URL2 = "/api/v1/customer/2";
	
	
	@InjectMocks
	CustomerController customerController;
	
	@Mock
	CustomerService customerService;
	
	MockMvc mockMvc;
	
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		//customerController = new CustomerController(customerService); //replaced by @InjectMocks
		
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Test
	public void testGetAllCustomers() throws Exception {

		//Given
		List<CustomerDTO> customers = new ArrayList<>();
		CustomerDTO cutomerDTO1 = new CustomerDTO();
		cutomerDTO1.setId(ID1);
		cutomerDTO1.setFirstName(FIRST_NAME1);
		cutomerDTO1.setLastName(LAST_NAME1);
		cutomerDTO1.setCustomerUrl(CUSTOMER_URL1);
		customers.add(cutomerDTO1);
		
		CustomerDTO cutomerDTO2 = new CustomerDTO();
		cutomerDTO2.setId(ID2);
		cutomerDTO2.setFirstName(FIRST_NAME2);
		cutomerDTO2.setLastName(LAST_NAME2);
		cutomerDTO1.setCustomerUrl(CUSTOMER_URL2);
		customers.add(cutomerDTO2);
		
		when(customerService.getAllCustomers()).thenReturn(customers);
		
		//When/Then
		mockMvc.perform(get("/api/v1/customers/")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.customers", hasSize(2)))
		.andReturn();
		
	}

	
	@Test
	public void testGetCustomerById() throws Exception {

		//Given
		CustomerDTO cutomerDTO1 = new CustomerDTO();
		cutomerDTO1.setId(ID1);
		cutomerDTO1.setFirstName(FIRST_NAME1);
		cutomerDTO1.setLastName(LAST_NAME1);
		cutomerDTO1.setCustomerUrl(CUSTOMER_URL1);
		
		when(customerService.getCustomerById(anyLong())).thenReturn(cutomerDTO1);
		
		//When/Then
		mockMvc.perform(get("/api/v1/customers/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id", equalTo(ID1.intValue())))
		.andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME1)))
		.andReturn();
				
	}
	
	@Test
	public void testGetCustomerByLastName() throws Exception {

		//Given
		CustomerDTO cutomerDTO1 = new CustomerDTO();
		cutomerDTO1.setId(ID1);
		cutomerDTO1.setFirstName(FIRST_NAME1);
		cutomerDTO1.setLastName(LAST_NAME1);
		cutomerDTO1.setCustomerUrl(CUSTOMER_URL1);
		
		when(customerService.getCustomerByLastName(anyString())).thenReturn(cutomerDTO1);
		
		//When/Then
		mockMvc.perform(get("/api/v1/customers/customer/Jones")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.lastName", equalTo(LAST_NAME1)))
		.andReturn();
				
	}

}
