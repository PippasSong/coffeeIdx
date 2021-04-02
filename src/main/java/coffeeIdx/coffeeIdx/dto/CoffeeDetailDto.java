package coffeeIdx.coffeeIdx.dto;

import java.util.ArrayList;

import lombok.Data; //자동으로 getter, setter 등 메소드를 만들어 준다

@Data //lombok어노테이션
public class CoffeeDetailDto {
	private String cafeName;
	private String cafeAddress;
	private String contactNumber;
	private ArrayList<String> menuInfo;
	private String createdDatetime;
	private String creatorId;
	private String updatedDatetime;
	private String updatorId;
	private String deletedYn;
}
