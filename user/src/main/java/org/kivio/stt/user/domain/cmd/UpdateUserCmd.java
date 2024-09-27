package org.kivio.stt.user.domain.cmd;

import org.kivio.stt.user.domain.User;

import java.beans.ConstructorProperties;
import java.time.LocalDate;
import java.util.Objects;

public class UpdateUserCmd extends UserCmd {
    @ConstructorProperties({"firstname", "lastname", "birthdate", "email"})
    public UpdateUserCmd(String firstName, String lastName, LocalDate birthDate, String email) {
        super(firstName, lastName, birthDate, email);
    }

    public void update(final User user) {
        Objects.requireNonNull(user);
        user.setFirstName(this.getFirstName());
        user.setLastName(this.getLastName());
        user.setBirthDate(this.getBirthDate());
        user.setEmail(this.getEmail());
    }
}
