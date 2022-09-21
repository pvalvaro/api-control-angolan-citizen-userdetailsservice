package ao.diangazo.dev.controller;

import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ao.diangazo.dev.dtos.CitizenDto;
import ao.diangazo.dev.model.CitizenModel;
import ao.diangazo.dev.services.impl.CitizenServiceImpl;
import ao.diangazo.dev.util.DateUtil;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/citizen")
public class CitizenController {
	final CitizenServiceImpl citizenService;

    @RequestMapping("/")
    public String home(){
        return "Welcome to homepage Angolan Citizen in Bahia, authenticated with jwt";
    }

    @PostMapping("/save")
    public ResponseEntity<Object> newCitizen(@RequestBody @Valid CitizenDto citizenDto) throws Exception {
        //verify if exists an citizen with current cpf
        if(citizenService.existsByCpf(citizenDto.getCpf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: This CPF is registered");
        }
        var citizen = new CitizenModel();
        var dateFormat = new DateUtil();
        BeanUtils.copyProperties(citizenDto, citizen);
        citizen.setBirthDay(dateFormat.formatInDate(citizenDto.getBirthDay()));
        return ResponseEntity.status(HttpStatus.CREATED).body(citizenService.saveCitizen(citizen));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> findCitizen(@PathVariable(value = "id") Long id){
        Optional<CitizenModel> citizen =  citizenService.findCitizen(id);
        if(!citizen.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Citizen not found");
        }
        return ResponseEntity.ok().body(citizen.get());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCitezen(@PathVariable(value = "id") Long id){
        Optional<CitizenModel> citizen =  citizenService.findCitizen(id);
        if(!citizen.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Citizen not found");
        }
        citizenService.deleteCitizen(citizen.get());
        return ResponseEntity.status(HttpStatus.OK).body("Citizen deleted successful!");
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CitizenModel>> getAllCitizen(){
        return ResponseEntity.status(HttpStatus.OK).body(citizenService.findAllCitizen());
    }
}