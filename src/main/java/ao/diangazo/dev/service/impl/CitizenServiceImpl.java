package ao.diangazo.dev.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ao.diangazo.dev.model.CitizenModel;
import ao.diangazo.dev.repositories.CitizenRepository;
import ao.diangazo.dev.service.CitizenService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CitizenServiceImpl implements CitizenService, UserDetailsService{

	final CitizenRepository citizenRepository;

    public List<CitizenModel> findAllCitizen(){
       return citizenRepository.findAll();
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return citizenRepository.existsByCpf(cpf);
    }

    @Transactional
    @Override
    public Object saveCitizen(CitizenModel citizen) {
        return citizenRepository.save(citizen);
    }

    @Override
    public Optional<CitizenModel> findCitizen(Long id) {
        Optional<CitizenModel> citizen = citizenRepository.findById(id);
        return citizen;
    }

    @Transactional
    public void deleteCitizen(CitizenModel citizenModel) {
        citizenRepository.delete(citizenModel);
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
