package project.EE.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Size(min = 2, max = 30, message = "Никнейм должнен быть от 5 до 30 символов")
    private String username;

    @Column(nullable = false)
    @Size(min = 2, max = 45, message = "Имя должно быть от 2 до 45 символов")
    private String firstname;

    @Column(nullable = false)
    @Size(min = 2, max = 45, message = "Фамилия должна быть от 2 до 45 символов")
    private String surname;

    @Column(nullable = false)
    @Size(min = 5, message = "Пароль должен быть от 5 символов")
    private String password;

    @Column(name ="passport_number", nullable = false)
    @Size(min = 9, max =13, message = "Личный номер паспорта должен быть 13 символов")
    private String passportNumber;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private List<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

}