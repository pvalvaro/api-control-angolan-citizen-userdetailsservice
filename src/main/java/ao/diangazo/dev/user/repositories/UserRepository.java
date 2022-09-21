package ao.diangazo.dev.user.repositories;

import ao.diangazo.dev.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
	boolean existsByEmail(String email);
	UserModel findByEmail(String email);

}
