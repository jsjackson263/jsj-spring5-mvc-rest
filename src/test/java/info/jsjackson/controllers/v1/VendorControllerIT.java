/**
 * 
 */
package info.jsjackson.controllers.v1;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import info.jsjackson.api.v1.model.VendorDTO;
import info.jsjackson.api.v1.model.VendorListDTO;
import info.jsjackson.services.VendorService;

/**
 * @author jsjackson
 *
 */
@RunWith(SpringRunner.class)
/*  -brings up a small segment of SpringContext just for the web front-end
 *  - wire up the VendorController class  */
@WebMvcTest(controllers = {VendorController.class})   
public class VendorControllerIT extends AbstractRestControllerTest {

	public static final Long ID1 = 1L;
	public static final String NAME1 = "Tutti Frutti";
	public static final String URL1 = VendorController.BASE_URL + "/" + ID1;

	public static final Long ID2 = 2L;
	public static final String NAME2 = "Fruits R Us";
	public static final String URL2 = VendorController.BASE_URL + "/" + ID2;
	
	public static final Long ID3 = 3L;
	public static final String NAME3 = "New Vendor";
	public static final String URL3 = VendorController.BASE_URL + "/" + ID3;
	
	
	@InjectMocks
	VendorController vendorController;

	/* -provided by Spring Context
	 * -tells Spring to create a Mockito mock, and inject it into the test 
	 * - no need for initMocks*/
	@MockBean 
	VendorService vendorService;
	
	@Autowired
	MockMvc mockMvc;
	
	
	@Before
	public void setUp() throws Exception {
		//MockitoAnnotations.initMocks(this);  
		
		//mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build(); //because @Autowired
	}

	@Test
	public void testGetAllVendors() throws Exception  {

		//Given
		List<VendorDTO> vendorList = new ArrayList<>();
		
		VendorDTO vendorDTO1 = new VendorDTO();
		vendorDTO1.setId(ID1);
		vendorDTO1.setName(NAME1);
		vendorDTO1.setVendorUrl(URL1);
		vendorList.add(vendorDTO1);
		
		VendorDTO vendorDTO2 = new VendorDTO();
		vendorDTO2.setId(ID2);
		vendorDTO2.setName(NAME2);
		vendorDTO2.setVendorUrl(URL2);
		vendorList.add(vendorDTO2);
		
		VendorListDTO vendorListDTO = new VendorListDTO(vendorList);
				
		when(vendorService.getAllVendors()).thenReturn(vendorListDTO);

		
		//When/Then
		mockMvc.perform(get(vendorController.BASE_URL)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.vendors", hasSize(2)))
		.andReturn();
		
	
	}
	
	@Test
	public void testGetVendorById() throws Exception  {

		//Given
		
		VendorDTO vendorDTO1 = new VendorDTO();
		vendorDTO1.setId(ID1);
		vendorDTO1.setName(NAME1);
		vendorDTO1.setVendorUrl(URL1);
		
		when(vendorService.getVendorById(anyLong())).thenReturn(vendorDTO1);

		
		//When/Then
		mockMvc.perform(get(VendorController.BASE_URL + "/" + ID1)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name", equalTo(NAME1)))
		.andReturn();
		
	
	}
	
	@Test
	public void testGetVendorByName() throws Exception  {

		//Given
		
		VendorDTO vendorDTO1 = new VendorDTO();
		vendorDTO1.setId(ID1);
		vendorDTO1.setName(NAME1);
		vendorDTO1.setVendorUrl(URL1);
		
		when(vendorService.getVendorByName(anyString())).thenReturn(vendorDTO1);

		
		//When/Then
		mockMvc.perform(get(VendorController.BASE_URL + "/vendor/" + NAME1)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name", equalTo(NAME1)))
		.andReturn();
		
	
	}
	
	@Test
	public void testGetVendorByNameDEBUG() throws Exception  {

		//Given
		
		VendorDTO vendorDTO1 = new VendorDTO();
		vendorDTO1.setId(ID1);
		vendorDTO1.setName(NAME1);
		vendorDTO1.setVendorUrl(URL1);
		
		when(vendorService.getVendorByName(anyString())).thenReturn(vendorDTO1);

		
		//When/Then
		String response = mockMvc.perform(get(VendorController.BASE_URL + "/vendor/" + NAME1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(vendorDTO1)))
		.andReturn().getResponse().getContentAsString();
			
		System.out.println("Response: " + response);
	
	}
	
	
	@Test
	public void testCreateNewVendor() throws Exception  {

		//Given
		VendorDTO vendorDTO3 = new VendorDTO();
		vendorDTO3.setId(ID3);
		vendorDTO3.setName(NAME3);
		vendorDTO3.setVendorUrl(URL3);
		
		VendorDTO returnedDTO = new VendorDTO();
		returnedDTO.setId(vendorDTO3.getId());
		returnedDTO.setName(vendorDTO3.getName());
		returnedDTO.setVendorUrl(vendorDTO3.getVendorUrl());
		
		when(vendorService.createNewVendor(vendorDTO3)).thenReturn(returnedDTO);

		
		//When/Then
		mockMvc.perform(post(VendorController.BASE_URL)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(vendorDTO3)))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.name", equalTo("New Vendor")))
		.andExpect(jsonPath("$.vendorUrl", equalTo("/api/v1/vendors/3")))
		.andReturn();
		
	}
	
	@Test
	public void testUpdateVendor() throws Exception  {

		//Given
		VendorDTO vendorDTO3 = new VendorDTO();
		vendorDTO3.setId(ID3);
		vendorDTO3.setName(NAME3);
		vendorDTO3.setVendorUrl(URL3);
		
		VendorDTO returnedDTO = new VendorDTO();
		returnedDTO.setId(vendorDTO3.getId());
		returnedDTO.setName(vendorDTO3.getName());
		returnedDTO.setVendorUrl(vendorDTO3.getVendorUrl());
		
		when(vendorService.saveVendorByDTO(ID3, vendorDTO3)).thenReturn(returnedDTO);

		
		//When/Then
		mockMvc.perform(put(VendorController.BASE_URL + "/" + ID3)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(vendorDTO3)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name", equalTo("New Vendor")))
		.andExpect(jsonPath("$.vendorUrl", equalTo("/api/v1/vendors/3")))
		.andReturn();
		
	}
	

	@Test
	public void testPatchVendor() throws Exception  {

		//Given
		VendorDTO vendorDTO3 = new VendorDTO();
		vendorDTO3.setId(ID3);
		vendorDTO3.setName(NAME3);
		vendorDTO3.setVendorUrl(URL3);
		
		VendorDTO returnedDTO = new VendorDTO();
		returnedDTO.setId(vendorDTO3.getId());
		returnedDTO.setName("New Patched Vendor Name");
		returnedDTO.setVendorUrl(vendorDTO3.getVendorUrl());
		
		when(vendorService.patchVendor(ID3, vendorDTO3)).thenReturn(returnedDTO);

		
		//When/Then
		mockMvc.perform(patch(VendorController.BASE_URL + "/" + ID3)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(vendorDTO3)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name", equalTo("New Patched Vendor Name")))
		.andExpect(jsonPath("$.vendorUrl", equalTo("/api/v1/vendors/3")))
		.andReturn();
		
	}
	
	@Test
	public void testDeleteVendorById() throws Exception  {

		//Given
		Long idToDelete = ID3;
		
		//When/Then
		mockMvc.perform(delete(VendorController.BASE_URL + "/" + idToDelete)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andReturn();
		
		verify(vendorService, times(1)).deleteVendor(anyLong());
		
	}
	
	
}
