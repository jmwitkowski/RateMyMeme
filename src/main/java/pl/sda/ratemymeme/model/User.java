package pl.sda.ratemymeme.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;


@Entity
public class User {

    @Id
    private String  login;

    private String  password;
    private String  email;
    private Role role;
    private LocalDate registrationDate;




}
