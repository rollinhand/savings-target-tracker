package org.kivio.stt.user.domain.cmd;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public abstract class UserCmd {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
}
