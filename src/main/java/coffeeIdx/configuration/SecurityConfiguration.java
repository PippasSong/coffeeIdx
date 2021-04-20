package coffeeIdx.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import javax.sql.DataSource;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import coffeeIdx.coffeeIdx.service.CoffeeIdxUserDetailsService;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CoffeeIdxUserDetailsService coffeeIdxUserDetailsService;  //사용자 정의 userdetailsservice를 이용할 수 있도록 의존성 주입
	

	
	@Override
	protected void configure(HttpSecurity security) throws Exception{
		security.csrf().disable(); //RESTful사용을 위해 비활성화
		security.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/index/request").hasAnyRole("GENERAL",  "ADMIN")
			//post는 로그인한 사람만 사용. 여려 역할을 지정할 때는 한 줄로 표현해야 한다. 여러 줄에 따로 지정하면 메소드를 찾을 수 없다는 예외 발생
			.antMatchers(HttpMethod.POST, "/index/request").hasAnyRole("GENERAL", "ADMIN") 
			.and()
			.formLogin().loginPage("/index/login").defaultSuccessUrl("/index/request", true)
			.and()
			.exceptionHandling().accessDeniedPage("/index/accessDenied") //접근권한 없음 처리
			.and()
			.logout().invalidateHttpSession(true).logoutSuccessUrl("/index") //로그아웃 처리
			.and()
			.userDetailsService(coffeeIdxUserDetailsService);  //사용자 정의 userdetailsservice를 이용 
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
}
