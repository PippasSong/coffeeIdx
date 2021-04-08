package coffeeIdx.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("coffeeIdx"))  //coffeeIdx패키지내에 RequestMapping 으로 할당된 모든 URL을 선택
				.paths(PathSelectors.any())  //PathSelectors.any("/api/**")와 같이 사용하면 특정 URI를 가진 주소만 선택할 수 있다.
				.build();
	}

}
