package coffeeIdx.coffeeIdx.service;

import coffeeIdx.coffeeIdx.dto.CoffeeDetailDto;
import coffeeIdx.coffeeIdx.dto.CoffeeIdxDto;

import java.util.List;

public interface CoffeeIdxService {
	List<CoffeeIdxDto> selectCoffeeList() throws Exception;
	CoffeeDetailDto selectCoffeeDetail(String cafeName, String cafeAddress) throws Exception;

}
