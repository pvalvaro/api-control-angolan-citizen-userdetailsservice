package ao.diangazo.dev.user.service;

import ao.diangazo.dev.dtos.UserDto;

public interface UserService {
	
	boolean existsByEmail(String email);
	Object saveUser(UserDto userDto);

}
