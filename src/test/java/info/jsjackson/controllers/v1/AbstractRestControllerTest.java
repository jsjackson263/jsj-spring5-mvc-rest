/**
 * 
 */
package info.jsjackson.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author jsjackson
 *
 */
public class AbstractRestControllerTest {

	public static String asJsonString(final Object object) {
		
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
