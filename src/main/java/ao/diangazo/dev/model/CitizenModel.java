package ao.diangazo.dev.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_CITIZEN")
public class CitizenModel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "birthDay", nullable = false)
    private Date birthDay;

    @Column(name = "mobile", nullable = false)
    private String mobile;

    @Column(name = "university")
    private String university;

    @Column(name = "course")
    private String course;

}
