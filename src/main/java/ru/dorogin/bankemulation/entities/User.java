package ru.dorogin.bankemulation.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;


// todo реализовать проверку данных

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;    // используется email пользователя

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public User(Integer id) {
        this.id = id;
    }
}
