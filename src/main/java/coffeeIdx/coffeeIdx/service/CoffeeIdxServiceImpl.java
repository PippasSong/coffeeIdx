package coffeeIdx.coffeeIdx.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import coffeeIdx.coffeeIdx.dto.CoffeeDetailDto;
import coffeeIdx.coffeeIdx.dto.CoffeeIdxDto;
import coffeeIdx.coffeeIdx.dto.MemberDto;
import coffeeIdx.coffeeIdx.dto.RequestDto;
import coffeeIdx.coffeeIdx.mapper.CoffeeIdxMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service //비즈니스 로직을 수행하기 위한 서비스 클래스
public class CoffeeIdxServiceImpl implements CoffeeIdxService {
	//커피 상세정보 정렬용 전역변수
	ArrayList<String> coffeeInfo = new ArrayList<>();
    ArrayList<Integer> priceInfo = new ArrayList<>();
	
    
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
	
	@Override
	public CoffeeDetailDto selectCoffeeDetail(String cafeName, String cafeAddress) throws Exception{
		CoffeeIdxDto coffeeIdxDto = coffeeIdxMapper.selectCoffeeDetail(cafeName, cafeAddress);
		CoffeeDetailDto coffeeDetailDto= new CoffeeDetailDto();
		
		//메뉴정보를 가진 문자열
		String menuStr = coffeeIdxDto.getMenuInfo();
		
		//가격정보 정렬
		sortMenus(menuStr);
		
		
		coffeeDetailDto.setCafeName(coffeeIdxDto.getCafeName());
		coffeeDetailDto.setCafeAddress(coffeeIdxDto.getCafeAddress());
		coffeeDetailDto.setContactNumber(coffeeIdxDto.getContactNumber());
		coffeeDetailDto.setCoffeeInfo(coffeeInfo);
		coffeeDetailDto.setPriceInfo(priceInfo);
		coffeeDetailDto.setCreatedDatetime(coffeeIdxDto.getCreatedDatetime());
		coffeeDetailDto.setCreatorId(coffeeIdxDto.getCreatorId());
		coffeeDetailDto.setUpdatedDatetime(coffeeIdxDto.getUpdatedDatetime());
		coffeeDetailDto.setUpdatorId(coffeeIdxDto.getUpdatorId());
		coffeeDetailDto.setDeletedYn(coffeeIdxDto.getDeletedYn());
		
		return coffeeDetailDto;
	}
	
	//메뉴정보 받아 전역변수 갱신
	public void sortMenus(String menuStr) throws Exception {
		coffeeInfo = new ArrayList<>();
		priceInfo = new ArrayList<>();
        String[] menuEach  = menuStr.split(";");
        
        for(int i = 0; i < menuEach.length; i++){
            String[] temp = menuEach[i].split(":");
            coffeeInfo.add(i,temp[0]);

            try{
                //콤마, 원 제거
                String strTemp = temp[1];
                strTemp = strTemp.replace(",", "");
                strTemp = strTemp.replace("원", "");
                priceInfo.add(Integer.parseInt(strTemp));
            } catch (Exception e){
                System.out.println("가격정보 없음");
                priceInfo.add(i,0);
            }

        }
        
        for(int i = 0; i < coffeeInfo.size(); i++){
            int minVal = Integer.MAX_VALUE;
            int indexOfMin = -1;
            for(int j = i; j < coffeeInfo.size(); j++){
                if(priceInfo.get(j) < minVal){
                    minVal = priceInfo.get(j);
                    indexOfMin = j;
                }
            }
            Collections.swap(coffeeInfo, i, indexOfMin);
            Collections.swap(priceInfo, i, indexOfMin);
        }
    }
	
	@Override
	public List<RequestDto> selectRequestList() throws Exception{
		return coffeeIdxMapper.selectRequestList();
	}
	
	@Override
	public void insertRequest(String address, String creatorId) throws Exception{
		coffeeIdxMapper.insertRequest(address, creatorId);
	}
	
	@Override
	public MemberDto getMemberById(MemberDto member) throws Exception{
		
		return coffeeIdxMapper.getMemberById(member);
	}
	

}
