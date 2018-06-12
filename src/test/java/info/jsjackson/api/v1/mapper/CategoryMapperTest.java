/**
 * 
 */
package info.jsjackson.api.v1.mapper;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import info.jsjackson.api.v1.model.CategoryDTO;
import info.jsjackson.domain.Category;

/**
 * @author jsjackson
 *
 */
@Ignore //XXX set-up needs fixing 
public class CategoryMapperTest {

	CategoryMapper categoryMapper = CategoryMapper.INSTANCE;
	
	public static final long ID = 1L;
	public static final String NAME = "Joe";
	
	
	@Test
	public void testCategoryToCategoryDTO() {

		//Given
		Category category = new Category();
		category.setId(ID);
		category.setName(NAME);
		
		
		
		//When
		CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);
		
		
		//Then
		assertEquals(Long.valueOf(ID), categoryDTO.getId());
		assertEquals(NAME, categoryDTO.getName());
		
	}

}
