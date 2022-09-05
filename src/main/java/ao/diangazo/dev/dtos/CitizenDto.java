package ao.diangazo.dev.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;


import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CitizenDto {
	@NotBlank(message = "Citizen´s name is mandatory")
    private String name;

    @NotBlank(message = "Citizen´s CPF is mandatory")
    @CPF(message = "Enter valid CPF")
    private String cpf;

    @NotBlank(message = "Citizen´s birthday is mandatory")
    private String birthDay;

    @NotBlank(message = "Citizen´s mobile is mandatory")
    private String mobile;
    private String university;
    private String course;
}
