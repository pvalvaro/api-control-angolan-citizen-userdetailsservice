package ao.diangazo.dev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ao.diangazo.dev.model.CitizenModel;

@Repository
public interface CitizenRepository extends JpaRepository<CitizenModel, Long>{	
	boolean existsByCpf(String cpf);
}