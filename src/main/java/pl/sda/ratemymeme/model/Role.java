package pl.sda.ratemymeme.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Role {

    @Id
    @GeneratedValue(generator = "roleSeq")
    @SequenceGenerator(name = "roleSeq", sequenceName = "role_seq", allocationSize = 1)
    private int idRole;

    private String RoleName;
}
