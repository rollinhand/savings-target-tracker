package org.kivio.stt.user.domain.cmd;

import org.junit.jupiter.api.Test;
import org.kivio.stt.user.domain.User;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UpdateUserCmdTest {
    private static final String FIRST_NAME = "Marge";
    private static final String LAST_NAME = "Simpson";
    private static final LocalDate BIRTHDATE = LocalDate.of(1956, 10, 1);
    private static final String MAIL = "simpson@gmail.com";

    @Test
    void testUpdateUser() {
        final String userId = UUID.randomUUID().toString();
        final User user = new User("Homer", LAST_NAME, LocalDate.of(1956, 5, 10), MAIL);
        user.setId(userId);
        final UpdateUserCmd underTest = new UpdateUserCmd(FIRST_NAME, LAST_NAME, BIRTHDATE, MAIL);
        underTest.update(user);

        assertEquals(userId, user.getId());
        assertEquals(FIRST_NAME, user.getFirstName());
        assertEquals(LAST_NAME, user.getLastName());
        assertEquals(BIRTHDATE, user.getBirthDate());
        assertEquals(MAIL, user.getEmail());
    }
}