package ao.diangazo.dev.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import ao.diangazo.dev.services.CitizenService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ao.diangazo.dev.model.CitizenModel;
import ao.diangazo.dev.repositories.CitizenRepository;
import ao.diangazo.dev.user.model.UserModel;
import ao.diangazo.dev.user.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CitizenServiceImpl implements CitizenService, UserDetailsService{

	final CitizenRepository citizenRepository;
	final UserRepository userRepository;

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
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		UserModel userModel = userRepository.findByEmail(email);
	
		if(userModel == null) {
			throw new UsernameNotFoundException("user not found with this: " + email);
		}
		
		
		if(userModel.isRole()) {
			roles.add(new SimpleGrantedAuthority("ADMIN"));
		}
		return new User(userModel.getEmail(), userModel.getPassword(), roles);
	}

}
