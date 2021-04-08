package coffeeIdx.coffeeIdx.dto;

import java.util.ArrayList;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data; //자동으로 getter, setter 등 메소드를 만들어 준다

@ApiModel(value="CoffeeDetailDto : 카페 상세정보", description = "카페 상세정보")
@Data //lombok어노테이션
public class CoffeeDetailDto {
	@ApiModelProperty(value = "카페명")
	private String cafeName;
	
	@ApiModelProperty(value = "카페주소")
	private String cafeAddress;
	
	@ApiModelProperty(value = "카페 연락처")
	private String contactNumber;
	
	//배열 2개 만들어서 하나는 메뉴 하나는 가격, 가격순으로 정렬할때 같이 정렬
	@ApiModelProperty(value = "priceInof에 대응되는 메뉴명")
	private ArrayList<String> coffeeInfo;
	@ApiModelProperty(value = "내림차순 메뉴 가격들")
	private ArrayList<Integer> priceInfo;
	
	@ApiModelProperty(value = "초기 작성시간")
	private String createdDatetime;
	
	@ApiModelProperty(value = "작성자")
	private String creatorId;
	
	@ApiModelProperty(value = "수정 시간")
	private String updatedDatetime;
	
	@ApiModelProperty(value = "수정한 사람")
	private String updatorId;
	
	@ApiModelProperty(value = "삭제여부 : Y-삭제, N-삭제되지 않음")
	private String deletedYn;
}
