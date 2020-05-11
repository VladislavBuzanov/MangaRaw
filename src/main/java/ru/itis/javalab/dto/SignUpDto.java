package ru.itis.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {
    private String login;
//    @Email(message = "{errors.incorrect-email}")
    private String email;
    @Size(min = 8, max = 12, message = "{errors.incorrect.password}")
    private String password;
}
