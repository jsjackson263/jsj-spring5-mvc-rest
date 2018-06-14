/**
 * 
 */
package info.jsjackson.services;

import java.util.List;

import info.jsjackson.api.v1.model.CategoryDTO;

/**
 * @author jsjackson
 *
 */
public interface CategoryService {

	List<CategoryDTO> getAllCategories();
	
	CategoryDTO getCategoryByName(String name);
}
