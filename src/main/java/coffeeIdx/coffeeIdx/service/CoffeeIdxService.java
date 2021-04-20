package coffeeIdx.coffeeIdx.service;

import coffeeIdx.coffeeIdx.dto.CoffeeDetailDto;
import coffeeIdx.coffeeIdx.dto.CoffeeIdxDto;
import coffeeIdx.coffeeIdx.dto.MemberDto;
import coffeeIdx.coffeeIdx.dto.RequestDto;

import java.util.List;

public interface CoffeeIdxService {
	List<CoffeeIdxDto> selectCoffeeList() throws Exception;
	CoffeeDetailDto selectCoffeeDetail(String cafeName, String cafeAddress) throws Exception;
	List<RequestDto> selectRequestList() throws Exception;
	void insertRequest(String address, String creatorId) throws Exception;
	MemberDto getMemberById(MemberDto member) throws Exception;

}
