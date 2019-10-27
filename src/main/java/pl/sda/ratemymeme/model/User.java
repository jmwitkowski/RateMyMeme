package pl.sda.ratemymeme.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;


@Entity
public class User {

    @Id
    private String  login;

    private String  password;
    private String  email;

    @ManyToOne(targetEntity = Role.class)
    private Role role;

    private LocalDate registrationDate;




}
