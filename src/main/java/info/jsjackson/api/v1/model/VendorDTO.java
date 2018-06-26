/**
 * 
 */
package info.jsjackson.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author jsjackson
 *
 */
@Data
public class VendorDTO {

	private Long id;
	
	@ApiModelProperty(value = "This is vendor name", required = true)
	private String name;
	
	@ApiModelProperty(value = "This is vendor url", required = false)
	private String vendorUrl;
	
}
