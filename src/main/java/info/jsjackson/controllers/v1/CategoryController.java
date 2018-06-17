/**
 * 
 */
package info.jsjackson.controllers.v1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import info.jsjackson.api.v1.model.CategoryDTO;
import info.jsjackson.api.v1.model.CategoryListDTO;
import info.jsjackson.services.CategoryService;

/**
 * @author jsjackson
 *
 */
@Controller
@RequestMapping("/api/v1/categories/")
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	
	@GetMapping
	public ResponseEntity<CategoryListDTO> getAllCategories() {
		
		List<CategoryDTO> categoryList = categoryService.getAllCategories();
		CategoryListDTO categoryListDTO = new CategoryListDTO(categoryList);
		
		return new ResponseEntity<CategoryListDTO>(categoryListDTO, HttpStatus.OK);
		
	}
	
	@GetMapping("{name}")
	public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name) {
		
		CategoryDTO categoryDTO = categoryService.getCategoryByName(name);
		
		return new ResponseEntity<CategoryDTO>(categoryDTO, HttpStatus.OK);
		
	}
	
	
	
}
