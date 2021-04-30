package coffeeIdx.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import coffeeIdx.coffeeIdx.dto.MemberDto;
import coffeeIdx.coffeeIdx.mapper.CoffeeIdxMapper;

public class SecurityUser extends User {
	//User클래스의 생성자를 호출할 때 검색 결과로 얻은 MemberDto객체의 값을 전달한다
	private static final long serilVersionUID = 1L;
	
	public String name;
	
	public SecurityUser(MemberDto member) {
		//부모클래스의 생성자를 불러온다
		super(member.getId(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRole().toString()));
		name = member.getName();
	}

}
