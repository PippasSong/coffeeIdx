package coffeeIdx.coffeeIdx.dto;

import javax.management.relation.Role;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;


@ApiModel(value="MemberDto : 로그인 정보", description = "로그인 정보")
@ToString
@Entity
@Data
public class MemberDto {
	
	@ApiModelProperty(value = "id. 최대 10글자")
	@Id
	private String id;
	
	@ApiModelProperty(value = "password. 최대 10글자")
	private String password;
	
	@ApiModelProperty(value = "이름")
	private String name;
	
	@ApiModelProperty(value = "역할")
	private String role;
	
	@ApiModelProperty(value = "계정 활성화 여부")
	private boolean enabled;

}
