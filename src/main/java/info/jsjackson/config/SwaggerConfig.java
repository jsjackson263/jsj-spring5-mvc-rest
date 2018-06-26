/**
 * 
 */
package info.jsjackson.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author jsjackson
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig  { //extends WebMvcConfigurationSupport {

	//TODO: Fix the version configuration - use version in pom.xml
	//@Value("${info.build.version}")
	//private String versionNumber;
	
	
	@Bean
	public Docket api() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.pathMapping("/")
				.apiInfo(apiInfo());
		
		
	}
	
	
	private ApiInfo apiInfo() {
	
		Contact contact = new Contact("Jov Sample Apps", "https://www.example.com", "owner@gmail.com");
		
		ApiInfo apiMeta = new ApiInfoBuilder()
	            .title("Spring Boot Swagger Example API")
	            .description( "An example Spring Boot Integration with Swagger")
	           // .version(versionNumber)
	            .version("1.0")
	            .license("Terms of Service")
	            .licenseUrl("https://www.apache.org/licenses/")
	            .contact(contact)
	            .build();
	        
	        return apiMeta;
	        
	}
	
	        
	
	/*
	 * Manual Configuration:  - if you weren't using SpringBoot:
	 *  - extend WebMvcConfigurationSupport
	 *  - override addResourceHandlers
	 *  - add your own resource handers)
	 */
	//@Override
	/*protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry
		.addResourceHandler("swagger-ui.html")
		.addResourceLocations("classpath:/META-INF/resources/");

		registry
		.addResourceHandler("/webjars/**")
		.addResourceLocations("classpath:/META-INF/resources/wabjars/");
		
		
	}*/
	
	
	
}
