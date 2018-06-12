/**
 * 
 */
package info.jsjackson.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import info.jsjackson.api.v1.model.CategoryDTO;
import info.jsjackson.domain.Category;

/**
 * @author jsjackson
 *
 */
@Mapper
public interface CategoryMapper {

	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
	
	//@Mapping(source = "getId", target = "id") 
	CategoryDTO categoryToCategoryDTO(Category category);
	
}
