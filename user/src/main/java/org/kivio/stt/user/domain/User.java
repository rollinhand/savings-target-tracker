package org.kivio.stt.user.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "stt_users")
@JsonbPropertyOrder({
        "id", "firstname", "lastname", "birthdate", "email"
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid-hex")
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;

    public User(String firstName, String lastName, LocalDate birthDate, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
