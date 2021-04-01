package coffeeIdx.coffeeIdx.service;

import java.util.List;

import coffeeIdx.coffeeIdx.dto.CoffeeIdxDto;
import coffeeIdx.coffeeIdx.mapper.CoffeeIdxMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service //비즈니스 로직을 수행하기 위한 서비스 클래스
public class CoffeeIdxServiceImpl implements CoffeeIdxService {
	//데이터베이스에 접근하는 DAO빈을 선언
	@Autowired
	private CoffeeIdxMapper coffeeIdxMapper;
	
	@Override
	public List<CoffeeIdxDto> selectCoffeeList() throws Exception{
		//사용자 요청을 처리하기 위한 비즈니스 로직
		//일반적으로 조회, 가공을 위해 복잡한 로직 수행
		return coffeeIdxMapper.selectCoffeeIdxList();
	}
	
	//상세정보를 보여줄 때는 DB에서 coffeeIdxdto상태로 받은 뒤 다른 형식의 dto로 변환 필요(대표메뉴가 연속된 문자열 형식이기 때문)
	

}
