/**
 * 
 */
package info.jsjackson.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import info.jsjackson.api.v1.mapper.CategoryMapper;
import info.jsjackson.api.v1.model.CategoryDTO;
import info.jsjackson.domain.Category;
import info.jsjackson.repositories.CategoryRepository;

/**
 * @author jsjackson
 *
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryMapper categoryMapper;
	private final CategoryRepository categoryRepository;
	
	public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
		this.categoryMapper = categoryMapper;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		return categoryRepository.findAll()
				.stream()
				.map(categoryMapper::categoryToCategoryDTO)
				.collect(Collectors.toList());
	}

	@Override
	public CategoryDTO getCategoryByName(String name) {
		Category category = categoryRepository.findByName(name);
		CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);
		
		return categoryDTO;
		
	}

}
