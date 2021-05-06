package coffeeIdx.coffeeIdx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import coffeeIdx.coffeeIdx.dto.CoffeeDetailDto;
import coffeeIdx.coffeeIdx.dto.CoffeeIdxDto;
import coffeeIdx.coffeeIdx.dto.MemberDto;
import coffeeIdx.coffeeIdx.dto.RequestDto;

//마이바티스는 데이터 접근 객체인 DAO를 만드는 것보다 SqlSessionDaoSupport 나 SqlSessionTemplate을 사용하기를 권장
//마이바티스 스프링 연동 모듈은 다른 빈에 직접 주입할 수 있는 매퍼를 생성할 수 있다.
//매퍼를 사용하면 일일이 DAO를 만들지 않고 인터페이스만을 이용해서 개발 가능

//마이바티스는 쿼리를 XML에 작성하고 아이디를 이용하여 매핑한다. XML파일은 resources에 놓인다.

@Mapper //마이바티스의 매퍼 인터페이스임을 선언
public interface CoffeeIdxMapper {
	List<CoffeeIdxDto> selectCoffeeIdxList() throws Exception; //메서드 이름은 sql쿼리의 이름과 동일해야 한다.
	CoffeeIdxDto selectCoffeeDetail(@Param("cafeName") String cafeName, @Param("cafeAddress") String cafeAddress) throws Exception;
	List<RequestDto> selectRequestList() throws Exception;
	void insertRequest(@Param("address")String address, @Param("creatorId")String creatorId) throws Exception;
	MemberDto getMemberById(MemberDto member) throws Exception;
	void insertMember(MemberDto member) throws Exception;
	int idCheck(String id) throws Exception;
	void deleteRequestList(String address) throws Exception;
}
