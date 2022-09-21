package ao.diangazo.dev.user.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "TB_USERS")
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "role")
	private boolean role;
}
