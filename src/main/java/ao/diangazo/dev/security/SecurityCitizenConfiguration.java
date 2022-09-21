package ao.diangazo.dev.security;

import ao.diangazo.dev.dtos.PasswordEncoderUtil;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityCitizenConfiguration {

    final PasswordEncoderUtil password;
   /* @Bean
    protected InMemoryUserDetailsManager configAuthentication() {

        List<UserDetails> users = new ArrayList<UserDetails>();
        List<GrantedAuthority> adminAuthorities = new ArrayList<GrantedAuthority>();

        adminAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
        UserDetails  admin = new User("dev", password.passwordEncoder().encode("1234"), adminAuthorities);
        users.add(admin);


        List<GrantedAuthority> citizenAuthorities = new ArrayList<GrantedAuthority>();
        citizenAuthorities.add(new SimpleGrantedAuthority("CITIZEN"));
        UserDetails  citizen = new User("citizen", password.passwordEncoder().encode("123"), citizenAuthorities);
        users.add(citizen);

        List<GrantedAuthority> managerAuthorities = new ArrayList<GrantedAuthority>();
        managerAuthorities.add(new SimpleGrantedAuthority("MANAGER"));
        UserDetails  manager = new User("manager", password.passwordEncoder().encode("123"), managerAuthorities);
        users.add(manager);

        return new InMemoryUserDetailsManager(users);
    }*/


    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //declares which Page(URL) will have What access type
        http
                .headers()
                .and()
                .authorizeRequests()
                .antMatchers("/api/citizen").permitAll()
                .antMatchers("/api/citizen/save").hasAnyAuthority("ADMIN", "MANAGER")
                .antMatchers("/api/citizen/delete/**").hasAnyAuthority("ADMIN", "MANAGER")
                .antMatchers("/api/citizen/getAll").permitAll()
                .antMatchers("/api/citizen/get/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and().csrf().disable().httpBasic()
        ;
        return http.build();
    }
}
