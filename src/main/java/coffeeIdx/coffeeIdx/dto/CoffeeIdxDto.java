package coffeeIdx.coffeeIdx.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data; //자동으로 getter, setter 등 메소드를 만들어 준다


@ApiModel(value="CoffeeIdxDto : 카페 기본정보", description = "카페 기본정보")
@Data //lombok어노테이션
public class CoffeeIdxDto {
	@ApiModelProperty(value = "카페명")
	private String cafeName;
	
	@ApiModelProperty(value = "카페주소")
	private String cafeAddress;
	
	@ApiModelProperty(value = "카페 연락처")
	private String contactNumber;
	
	@ApiModelProperty(value = "메뉴명:가격;형태로 구성된 문자열. index창에서는 사용하지 않기 때문에 null")
	private String menuInfo;
	
	@ApiModelProperty(value = "초기 작성시간")
	private String createdDatetime;
	
	@ApiModelProperty(value = "작성자")
	private String creatorId;
	
	@ApiModelProperty(value = "수정한 시간")
	private String updatedDatetime;
	
	@ApiModelProperty(value = "수정한 사람")
	private String updatorId;
	
	@ApiModelProperty(value = "삭제여부 : Y-삭제, N-삭제되지 않음")
	private String deletedYn;

}
