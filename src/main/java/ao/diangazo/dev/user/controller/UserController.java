package ao.diangazo.dev.user.controller;

import ao.diangazo.dev.dtos.UserDto;
import ao.diangazo.dev.user.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    final UserServiceImpl userService;

    @GetMapping("/home")
    public String home(){
        return "Welcome to user homepage";
    }
    @PostMapping("/save")
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDto userDto){
        if(userService.existsByEmail(userDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: An User is registered with this e-mail: " + userDto.getEmail());
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.saveUser(userDto));
    }
}
