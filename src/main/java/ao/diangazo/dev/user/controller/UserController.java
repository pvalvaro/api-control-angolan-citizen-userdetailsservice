package ao.diangazo.dev.user.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ao.diangazo.dev.user.dtos.UserDto;
import ao.diangazo.dev.user.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/user")
public class UserController {

	final UserServiceImpl userServiceImpl;
		
	 @RequestMapping("/")
	    public String home(){
	        return "User homepage";
	    }
	
	@PostMapping("/save")
	public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDto userDto){
		if(userServiceImpl.existsByEmail(userDto.getEmail())) {
			 return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: An User is registered with this e-mail: " + userDto.getEmail());
		}
		return ResponseEntity.status(HttpStatus.OK).body(userServiceImpl.saveUser(userDto));	
	}
	
}
