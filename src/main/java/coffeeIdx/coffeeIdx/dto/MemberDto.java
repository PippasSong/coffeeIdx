package coffeeIdx.coffeeIdx.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value="MemberDto : 로그인 정보", description = "로그인 정보")
@Data
public class MemberDto {
	
	@ApiModelProperty(value = "id. 최대 10글자")
	private String id;
	
	@ApiModelProperty(value = "password. 최대 10글자")
	private String password;
	
	@ApiModelProperty(value = "이름")
	private String name;
	
	@ApiModelProperty(value = "역할")
	private String role;

}
