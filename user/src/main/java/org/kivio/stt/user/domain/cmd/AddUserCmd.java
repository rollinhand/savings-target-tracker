package org.kivio.stt.user.domain.cmd;

import org.kivio.stt.user.domain.User;

import java.beans.ConstructorProperties;
import java.time.LocalDate;

public class AddUserCmd extends UserCmd {
    @ConstructorProperties({"firstname", "lastname", "birthdate", "email"})
    public AddUserCmd(String firstName, String lastName, LocalDate birthDate, String email) {
        super(firstName, lastName, birthDate, email);
    }

    public User asUser() {
        return new User(getFirstName(), getLastName(), getBirthDate(), getEmail());
    }
}
