package coffeeIdx.coffeeIdx.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value="RequestDto : 요청목록", description = "요청목록")
@Data
public class RequestDto {
	@ApiModelProperty(value = "원하는 동명, 도로명 주소명")
	private String address;
	
	@ApiModelProperty(value = "초기 작성시각")
	private String createdDatetime;
	
	@ApiModelProperty(value = "삭제여부 : Y-삭제, N-삭제되지 않음")
	private String deletedYn;
	
	@ApiModelProperty(value = "id. 최대 10글자")
	private String creatorId;
}
