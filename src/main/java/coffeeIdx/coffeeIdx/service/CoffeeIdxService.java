package coffeeIdx.coffeeIdx.service;

import coffeeIdx.coffeeIdx.dto.CoffeeIdxDto;

import java.util.List;

public interface CoffeeIdxService {
	List<CoffeeIdxDto> selectCoffeeList() throws Exception;

}
