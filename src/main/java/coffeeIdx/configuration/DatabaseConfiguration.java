package coffeeIdx.configuration;

import java.util.Properties;

import javax.sql.DataSource;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/application.properties") //application.properties를 사용할 수 있도록 설정파일 위치 지정
@EnableTransactionManagement
public class DatabaseConfiguration {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	
	@Bean
	@ConfigurationProperties(prefix="spring.datasource.hikari")  // application.properties에 설정했던 데이터베이스 관련 정보를 사용하도록 지정
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	
	
	//히카리CP의 설정 파일을 이용해서 데이터베이스와 연결하는 데이터 소스를 생성
	@Bean
	public DataSource dataSource() throws Exception{
		return new HikariDataSource(hikariConfig());
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean(); //SqlSessionFactory를 생성하기 위해 SqlSessionFactoryBean을 사용. 스프링이 아닌 마이바티스 단독으로 사용할 겨우 SqlSessionFactoryBuilder를 사용
		sqlSessionFactoryBean.setDataSource(dataSource);  //앞에서 만든 데이터 소스 사용
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/sql-*.xml")); //마이바티스 매퍼 파일의 위치 설정
		sqlSessionFactoryBean.setConfiguration(mybatisConfig());
		
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	
	//자바와 데이터베에스 컬럼 간의 변수 매핑
	@Bean
	@ConfigurationProperties(prefix="mybatis.configuration")
	public org.apache.ibatis.session.Configuration mybatisConfig(){
		return new org.apache.ibatis.session.Configuration();
	}
	
	//스프링이 제공하는 트랜잭션 매니저를 등록
	@Bean
	public PlatformTransactionManager transactionManager() throws Exception{
		return new DataSourceTransactionManager(dataSource());
	}
	
	//jpa설정 등록
	@Bean
	@ConfigurationProperties(prefix="spring.jpa")
	public Properties hibernateConfig(){
		return new Properties();
	}

}
