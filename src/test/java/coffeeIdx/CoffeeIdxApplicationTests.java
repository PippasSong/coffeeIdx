package coffeeIdx;

import javax.management.relation.Role;

//새로 추가
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import coffeeIdx.coffeeIdx.dto.MemberDto;
import coffeeIdx.coffeeIdx.mapper.CoffeeIdxMapper;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CoffeeIdxApplicationTests {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private CoffeeIdxMapper coffeeIdxMapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Test
	public void testInsert() throws Exception {
		MemberDto member = new MemberDto();
		member.setId("도우너");
		member.setPassword(encoder.encode("1234"));
		member.setName("도우너");
		member.setRole("ROLE_GENERAL");
		member.setEnabled(true);
		coffeeIdxMapper.insertMember(member);
	}

//	@Test
//	public void contextLoads() {
//	}
//	
//	@Test
//	public void testSqlSession() throws Exception{
//		System.out.println(sqlSession.toString());
//	}

}
