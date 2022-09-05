package ao.diangazo.dev.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.diangazo.dev.user.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
	boolean existsByEmail(String email);

}
