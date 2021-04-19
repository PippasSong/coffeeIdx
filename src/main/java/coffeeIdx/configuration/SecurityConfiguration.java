package coffeeIdx.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import javax.sql.DataSource;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import coffeeIdx.coffeeIdx.mapper.CoffeeIdxMapper;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private CoffeeIdxMapper coffeeIdxMapper;
	
	@Override
	protected void configure(HttpSecurity security) throws Exception{
		
		security.authorizeRequests()
//			.antMatchers(HttpMethod.GET, "/index/request").permitAll()
			.antMatchers(HttpMethod.GET, "/index/request").hasAnyRole("general")
			.antMatchers(HttpMethod.POST, "/index/request").hasAnyRole("general") //post는 로그인한 사람만 사용
			.antMatchers(HttpMethod.POST, "/index/request").hasAnyRole("admin")
			.and()
			.csrf().disable() //RESTful사용을 위해 비활성화
			.formLogin().loginPage("/index/login")
			.and()
			.exceptionHandling().accessDeniedPage("/index/accessDenied") //접근권한 없음 처리
			.and()
			.logout().invalidateHttpSession(true).logoutSuccessUrl("/index/request"); //로그아웃 처리
		
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		//요청정보 조회
		String query1 = "select id username, password from t_member where id=?";
		//역할 조회
		String query2 = "select id, role from t_member where id=?";
		
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery(query1) //조회 결과의 칼럼 이름이 USERNAME과 PASSWORLD로 일치해야 자동으로 매핑
		.authoritiesByUsernameQuery(query2);
//		 auth.inMemoryAuthentication()
//         .withUser("manager")
//         .password("{noop}1111")
//         .roles("general");
	}

}
