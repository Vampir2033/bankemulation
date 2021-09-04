package ru.dorogin.bankemulation.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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
    @NotEmpty(message = "Поле обязательно к заполнению")
    @Email(message = "Некорректный email")
    @Size(max = 30, message = "email не может быть больше 30 символов")
    private String username;    // используется email пользователя

    @Column(name = "password")
    @NotEmpty(message = "Поле обязательно к заполнению")
    private String password;

    @Column(name = "name")
    @NotEmpty(message = "Поле обязательно к заполнению")
    @Size(max = 30, message = "Имя не может быть больше 30 символов")
    private String name;

    @Column(name = "lastname")
    @NotEmpty(message = "Фамилия обязательна к заполнению")
    @Size(max = 30, message = "Фамилия не может быть больше 30 символов")
    private String lastName;

    @Column(name = "patronymic")
    @Size(max = 30, message = "Отчество не может быть больше 30 символов")
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
