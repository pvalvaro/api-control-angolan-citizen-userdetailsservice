package ao.diangazo.dev.user.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
