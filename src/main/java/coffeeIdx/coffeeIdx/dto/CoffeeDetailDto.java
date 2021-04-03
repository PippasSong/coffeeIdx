package coffeeIdx.coffeeIdx.dto;

import java.util.ArrayList;

import lombok.Data; //자동으로 getter, setter 등 메소드를 만들어 준다

@Data //lombok어노테이션
public class CoffeeDetailDto {
	private String cafeName;
	private String cafeAddress;
	private String contactNumber;
	//배열 2개 만들어서 하나는 메뉴 하나는 가격, 가격순으로 정렬할때 같이 정렬
	private ArrayList<String> coffeeInfo;
	private ArrayList<Integer> priceInfo;
	private String createdDatetime;
	private String creatorId;
	private String updatedDatetime;
	private String updatorId;
	private String deletedYn;
}
