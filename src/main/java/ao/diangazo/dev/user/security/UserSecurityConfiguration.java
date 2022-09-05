package ao.diangazo.dev.user.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class UserSecurityConfiguration {

	  @Bean
	    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

	        //declares which Page(URL) will have What access type
	        http
	                .headers()
	                .and()
	                .authorizeRequests()
	                .antMatchers("/api/user/").permitAll()
	                .antMatchers("/api/user/save").permitAll()
	                .antMatchers("/api/user/delete/**").hasAnyAuthority("ADMIN", "MANAGER")
	                .antMatchers("/api/user/getAll").permitAll()
	                .antMatchers("/api/user/get/**").permitAll()
	                .and().csrf().disable().httpBasic()
	        ;
	        return http.build();
	    }
}
