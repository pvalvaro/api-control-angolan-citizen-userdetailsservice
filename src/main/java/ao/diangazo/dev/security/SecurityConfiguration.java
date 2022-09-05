package ao.diangazo.dev.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
	protected InMemoryUserDetailsManager configAuthentication() {
      
    	List<UserDetails> users = new ArrayList<UserDetails>();
    	List<GrantedAuthority> adminAuthorities = new ArrayList<GrantedAuthority>();
    	
    	adminAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
    	UserDetails  admin = new User("dev", passwordEncoder().encode("1234"), adminAuthorities);
    	users.add(admin);


    	List<GrantedAuthority> citizenAuthorities = new ArrayList<GrantedAuthority>();
    	citizenAuthorities.add(new SimpleGrantedAuthority("CITIZEN"));
    	UserDetails  citizen = new User("citizen", passwordEncoder().encode("123"), citizenAuthorities);
    	users.add(citizen);
    	
    	List<GrantedAuthority> managerAuthorities = new ArrayList<GrantedAuthority>();
    	managerAuthorities.add(new SimpleGrantedAuthority("MANAGER"));
    	UserDetails  manager = new User("manager", passwordEncoder().encode("123"), managerAuthorities);
    	users.add(manager);
    	
        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //declares which Page(URL) will have What access type
        http
                .headers()
                .and()
                .authorizeRequests()
                .antMatchers("/api/citizen/").permitAll()
                .antMatchers("/api/citizen/save").hasAnyAuthority("ADMIN", "MANAGER")
                .antMatchers("/api/citizen/delete/**").hasAuthority("ADMIN")
                .antMatchers("/api/citizen//getAll").permitAll()
                .antMatchers("/api/citizen//get/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and().csrf().disable().httpBasic()
        ;
        return http.build();
    }
}