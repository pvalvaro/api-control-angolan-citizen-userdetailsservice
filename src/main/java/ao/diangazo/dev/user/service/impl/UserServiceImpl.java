package ao.diangazo.dev.user.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ao.diangazo.dev.user.dtos.PasswordConfig;
import ao.diangazo.dev.user.dtos.UserDto;
import ao.diangazo.dev.user.model.UserModel;
import ao.diangazo.dev.user.repositories.UserRepository;
import ao.diangazo.dev.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.var;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	final UserRepository repository;

	final PasswordConfig passwordConfig;
	
	@Override
	public boolean existsByEmail(String email) {
		return repository.existsByEmail(email);
	}

	@Override
	public Object saveUser(UserDto userDto) {

		var user = new UserModel();
		BeanUtils.copyProperties(userDto, user);
		user.setPassword(passwordConfig.passwordEncoder().encode(userDto.getPassword()));
		return repository.save(user);
	}

}
