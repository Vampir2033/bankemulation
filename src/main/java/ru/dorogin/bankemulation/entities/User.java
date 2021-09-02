package ru.dorogin.bankemulation.entities;

import javax.persistence.Entity;

@Entity
public class User {
    private long id;
    private String email;
    private String password;
    private String name;
    private String lastName;
    private String patronymic;

}
