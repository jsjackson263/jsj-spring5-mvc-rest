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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import info.jsjackson.api.v1.model.CategoryDTO;
import info.jsjackson.api.v1.model.CategoryListDTO;
import info.jsjackson.services.CategoryService;

/**
 * @author jsjackson
 *
 * alternatively externalize the API url value this way: @RequestMapping("${some.url.value}") 
 * - but would need to bring up the SpringContext to get this value
 * - test would have to change from Unit tests to Integration tests
 * 
 * @RestController annotation -> @Controller + @ResponseBody
 */
@RestController

@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

	public static final String BASE_URL = "/api/v1/categories";
	
	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CategoryListDTO getAllCategories() {
		
		List<CategoryDTO> categoryList = categoryService.getAllCategories();
		
		return new CategoryListDTO(categoryList);
		
	}
	
	@GetMapping("{name}")
	@ResponseStatus(HttpStatus.OK)
	public CategoryDTO getCategoryByName(@PathVariable String name) {
		
		CategoryDTO categoryDTO = categoryService.getCategoryByName(name);
		
		return categoryDTO;
		
	}
	
	
	
}
