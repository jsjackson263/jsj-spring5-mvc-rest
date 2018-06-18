/**
 * 
 */
package info.jsjackson.controllers.v1;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
public class CustomerControllerTest extends AbstractRestControllerTest {

	public static Long ID1 = 1L;
	public static String FIRST_NAME1 = "Jim";
	public static String LAST_NAME1 = "Jones";
	public static String CUSTOMER_URL1 = "/api/v1/customer/1";
	
	public static Long ID2 = 2L;
	public static String FIRST_NAME2 = "Joe";
	public static String LAST_NAME2 = "Blog";
	public static String CUSTOMER_URL2 = "/api/v1/customer/2";
	
	public static Long ID3 = 3L;
	public static String FIRST_NAME3 = "Steve";
	public static String LAST_NAME3 = "Hawkings";
	public static String CUSTOMER_URL3 = "/api/v1/customer/3";
	
	
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
		.andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME1)))
		.andExpect(jsonPath("$.lastName", equalTo(LAST_NAME1)))
		.andExpect(jsonPath("$.customer_url", equalTo(CUSTOMER_URL1)))
		.andReturn();
				
	}
	
	@Test
	public void testCreateNewCustomer() throws Exception {

		//Given
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(ID3);
		customerDTO.setFirstName(FIRST_NAME3);
		customerDTO.setLastName(LAST_NAME3);
		customerDTO.setCustomerUrl(CUSTOMER_URL3);
		
		CustomerDTO returnDTO = new CustomerDTO();
		returnDTO.setId(customerDTO.getId());
		returnDTO.setFirstName(customerDTO.getFirstName());
		returnDTO.setLastName(customerDTO.getLastName());
		returnDTO.setCustomerUrl(customerDTO.getCustomerUrl());
		
		
		when(customerService.createNewCustomer(customerDTO)).thenReturn(returnDTO);
		
		//When/Then
		mockMvc.perform(post("/api/v1/customers/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(customerDTO)))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME3)))
		.andExpect(jsonPath("$.lastName", equalTo(LAST_NAME3)))
		.andExpect(jsonPath("$.customer_url", equalTo(CUSTOMER_URL3)))
		.andReturn();
				
	}
	
	@Test
	public void testCreateNewCustomerDebug() throws Exception {

		//Given
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(ID3);
		customerDTO.setFirstName(FIRST_NAME3);
		customerDTO.setLastName(LAST_NAME3);
		customerDTO.setCustomerUrl(CUSTOMER_URL3);
		
		CustomerDTO returnDTO = new CustomerDTO();
		returnDTO.setId(customerDTO.getId());
		returnDTO.setFirstName(customerDTO.getFirstName());
		returnDTO.setLastName(customerDTO.getLastName());
		returnDTO.setCustomerUrl(customerDTO.getCustomerUrl());
		
		
		when(customerService.createNewCustomer(customerDTO)).thenReturn(returnDTO);
		
		//When/Then
		String response = mockMvc.perform(post("/api/v1/customers/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(customerDTO)))
		.andReturn().getResponse().getContentAsString();
		System.out.println("Response: " + response);
		
	}
	
	@Test
	public void testUpdateCustomer() throws Exception {

		//Given
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(ID3);
		customerDTO.setFirstName(FIRST_NAME3);
		customerDTO.setLastName(LAST_NAME3);
		customerDTO.setCustomerUrl(CUSTOMER_URL3);
		
		CustomerDTO returnDTO = new CustomerDTO();
		returnDTO.setId(customerDTO.getId());
		returnDTO.setFirstName(customerDTO.getFirstName());
		returnDTO.setLastName(customerDTO.getLastName());
		returnDTO.setCustomerUrl(customerDTO.getCustomerUrl());
		
		
		when(customerService.saveCustomerByDTO(anyLong(),  any(CustomerDTO.class))).thenReturn(returnDTO);
		
		//When/Then
		mockMvc.perform(put("/api/v1/customers/3")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(customerDTO)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME3)))
		.andExpect(jsonPath("$.lastName", equalTo(LAST_NAME3)))
		.andExpect(jsonPath("$.customer_url", equalTo(CUSTOMER_URL3)))
		.andReturn();
				
	}

}
