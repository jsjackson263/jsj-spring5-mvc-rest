/**
 * 
 */
package info.jsjackson.controllers.v1;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
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

import info.jsjackson.api.v1.model.CategoryDTO;
import info.jsjackson.controllers.RestResponseEntityExceptionHandler;
import info.jsjackson.services.CategoryService;
import info.jsjackson.services.ResourceNotFoundException;

/**
 * @author jsjackson
 *
 */
public class CategoryControllerTest {

	public static Long ID1 = 1L;
	public static String NAME1 = "Jim";
	
	public static Long ID2 = 2L;
	public static String NAME2 = "Jones";
	
	
	@Mock
	CategoryService categoryService;
	
	@InjectMocks
	CategoryController categoryController;
	
	MockMvc mockMvc;
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		//categoryController = new CategoryController(categoryService); //replaced by @InjectMocks
		
		mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
				.setControllerAdvice(new RestResponseEntityExceptionHandler())  //add the exception handler
				.build();
	}

	@Test
	public void testGetAllCategories() throws Exception {

		//Given
		List<CategoryDTO> categories = new ArrayList<>();
		
		CategoryDTO dto1 = new CategoryDTO();
		dto1.setId(ID1);
		dto1.setName(NAME1);
		categories.add(dto1);
		
		CategoryDTO dto2 = new CategoryDTO();
		dto1.setId(ID2);
		dto1.setName(NAME2);
		categories.add(dto2);
		
		when(categoryService.getAllCategories()).thenReturn(categories);

		
		//When//Then
		mockMvc.perform(get(CategoryController.BASE_URL)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.categories", hasSize(2)))
		.andReturn();
				
	}
	
	@Test
	public void testGetCategoryByName() throws Exception {

		//Given
		CategoryDTO dto1 = new CategoryDTO();
		dto1.setId(ID1);
		dto1.setName(NAME1);
		
		when(categoryService.getCategoryByName(anyString())).thenReturn(dto1);

		
		//When//Then
		mockMvc.perform(get(CategoryController.BASE_URL+ "/Jim")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name", equalTo(NAME1)))
		.andReturn();
				
	}
	
	@Test
	public void testGetByNameNotFound() throws Exception {

		when(categoryService.getCategoryByName(anyString())).thenThrow(ResourceNotFoundException.class);

		
		//When//Then
		mockMvc.perform(get(CategoryController.BASE_URL+ "/foo")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound())
		.andReturn();
				
	}
	
	

}
