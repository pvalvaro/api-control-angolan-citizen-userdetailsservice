package ao.diangazo.dev.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {

	@NotBlank(message = "User name is mandatory")
	private String name;
	@NotBlank(message = "Password is mandatory")
	private String password;
	
	@NotBlank(message = "E-mail is mandatory")
	@Email(message = "Enter valid e-mail")
	private String email;
	
	private boolean role;
}
