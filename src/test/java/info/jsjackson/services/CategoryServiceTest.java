/**
 * 
 */
package info.jsjackson.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import info.jsjackson.api.v1.mapper.CategoryMapper;
import info.jsjackson.api.v1.model.CategoryDTO;
import info.jsjackson.domain.Category;
import info.jsjackson.repositories.CategoryRepository;

/**
 * @author jsjackson
 *
 */
public class CategoryServiceTest {

	public static final Long ID1 = 2L;
	public static final String NAME1 = "Jimmy";
	
	public static final Long ID2 = 2L;
	public static final String NAME2 = "Jones";
	
	CategoryService categoryService;
	
	@Mock
	CategoryRepository categoryRepository;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
		
	}

	@Test
	public void testgetAllCategories() throws Exception {

		//Given
		List<Category> categoryList = new ArrayList<>();
		Category cat1 = new Category();
		cat1.setId(ID1);
		cat1.setName(NAME1);
		categoryList.add(cat1);
		
		Category cat2 = new Category();
		cat2.setId(ID2);
		cat2.setName(NAME2);
		categoryList.add(cat2);
		
		when(categoryRepository.findAll()).thenReturn(categoryList);
		
		
		//When
		List<CategoryDTO> categoryDTOs = categoryService.getAllCategories();
		
		
		//Then
		assertEquals(2, categoryDTOs.size());
		
	}
	
	
	@Test
	public void testgetCategoryByName() throws Exception {
		
		//Given
		Category cat = new Category();
		cat.setId(ID1);
		cat.setName(NAME1);

		when(categoryRepository.findByName(anyString())).thenReturn(cat);
		
		//When
		CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME1);
		
		
		//Then
		assertEquals(NAME1, categoryDTO.getName());
		
	
	}

}
