/**
 * 
 */
package info.jsjackson.api.v1.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author jsjackson
 *
 */
@Data
@AllArgsConstructor
public class CategoryListDTO {
	
	List<CategoryDTO> categories;
	
	

}
