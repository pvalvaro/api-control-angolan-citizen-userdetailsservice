package ao.diangazo.dev.service;

import java.util.Optional;

import ao.diangazo.dev.model.CitizenModel;

public interface CitizenService {
	boolean existsByCpf(String cpf);
    Object saveCitizen(CitizenModel citizen);
    Optional<CitizenModel> findCitizen(Long id);
}
