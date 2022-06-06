package sat.recruitment.api.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size( max = 50)
	public String name;

	@NotBlank
	@Size( max = 50)
	public String email;

	@NotBlank
	@Size( max = 50)
	public String address;

	@NotBlank
	@Size( max = 50)
	public String phone;

	@Column(name = "type")
	public String userType;

	public Double money;

}
