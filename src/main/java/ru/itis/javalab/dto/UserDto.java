package ru.itis.javalab.dto;

import lombok.*;
import ru.itis.javalab.model.enumerated.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserDto {
    private String login;
    private String email;
    private Role role;
}
