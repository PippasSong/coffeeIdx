package coffeeIdx.coffeeIdx.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import coffeeIdx.coffeeIdx.dto.MemberDto;
import coffeeIdx.coffeeIdx.mapper.CoffeeIdxMapper;
import coffeeIdx.configuration.SecurityUser;

@Service //사용자 인증 위해 UserDetails객체를 얻기 위해 UserDetailsService 구현
public class CoffeeIdxUserDetailsService implements UserDetailsService {
	
	@Autowired
	private CoffeeIdxMapper coffeeIdxMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		//매퍼로 정보 조회하여
		//UserDetails 타입의 객체로 리턴한다
		MemberDto temp = new MemberDto();
		temp.setId(username);
		
//		Optional<MemberDto> optional = coffeeIdxMapper.getMemberById(temp);
//		if(!optional.isPresent()) {
//			throw new UsernameNotFoundException(username + " 사용자 없음");
//		} else {
//			MemberDto member = optional.get();
//			return new SecurityUser(member);
//		}
		
		try {
			MemberDto member = coffeeIdxMapper.getMemberById(temp);
			return new SecurityUser(member);
		} catch (Exception e) {
			throw new UsernameNotFoundException(username + " 사용자 없음");
		}
		
	}
}
