package sat.recruitment.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sat.recruitment.api.enums.UserTypeEnum;


import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
	@NotNull(message = "enterpriseId can't be NULL")
	public String name;

	@NotNull(message = "The email is required")
	public String email;

	@NotNull(message = "The address is required")
	public String address;

	@NotNull(message = "The phone is required")
	public String phone;

	public UserTypeEnum userType;

	public Double money;

}
