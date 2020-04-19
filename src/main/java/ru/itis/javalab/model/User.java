package ru.itis.javalab.model;

import lombok.*;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.model.enumerated.Role;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "itis_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "login")
    private String login;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String hashPassword;

    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "isConfirmed")
    private boolean isConfirmed;
    @Column(name = "code")
    private String code;

    public UserDto getDto() {
        return UserDto.builder()
                .email(email)
                .login(login)
                .role(role)
                .build();
    }
}
