package coffeeIdx.configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity security) throws Exception{
		
		security.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/index/request").permitAll()
			.antMatchers(HttpMethod.POST, "/index/request").hasAnyRole("general") //post는 로그인한 사람만 사용
			.antMatchers(HttpMethod.POST, "/index/request").hasAnyRole("admin")
			.and()
			.csrf().disable() //RESTful사용을 위해 비활성화
			.formLogin().loginPage("/index/login");
		
	}

}
