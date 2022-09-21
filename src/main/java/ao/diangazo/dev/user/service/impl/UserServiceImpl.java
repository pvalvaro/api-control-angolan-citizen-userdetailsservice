package ao.diangazo.dev.user.service.impl;

import ao.diangazo.dev.dtos.UserDto;
import ao.diangazo.dev.user.model.UserModel;
import ao.diangazo.dev.user.repositories.UserRepository;
import ao.diangazo.dev.user.service.UserService;
import ao.diangazo.dev.dtos.PasswordEncoderUtil;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	final UserRepository repository;
	final PasswordEncoderUtil passwordEncoder;

	@Override
	public boolean existsByEmail(String email) {
		return repository.existsByEmail(email);
	}

	@Override
	@Transactional
	public Object saveUser(UserDto userDto) {

		var user = new UserModel();
		BeanUtils.copyProperties(userDto, user);
		user.setPassword(passwordEncoder.passwordEncoder().encode(userDto.getPassword()));
		return repository.save(user);
	}
}
