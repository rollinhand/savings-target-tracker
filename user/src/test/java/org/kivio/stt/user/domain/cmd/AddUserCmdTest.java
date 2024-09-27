package org.kivio.stt.user.domain.cmd;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AddUserCmdTest {
    private static final String FIRST_NAME = "Marge";
    private static final String LAST_NAME = "Simpson";
    private static final LocalDate BIRTHDATE = LocalDate.of(1956, 10, 1);
    private static final String MAIL = "simpson@gmail.com";

    @Test
    void testFullMapping() {
        AddUserCmd underTest = new AddUserCmd(FIRST_NAME, LAST_NAME, BIRTHDATE, MAIL);
        assertEquals(FIRST_NAME, underTest.getFirstName());
        assertEquals(LAST_NAME, underTest.getLastName());
        assertEquals(BIRTHDATE, underTest.getBirthDate());
        assertEquals(MAIL, underTest.getEmail());

        assertNotNull(underTest.asUser());
        assertEquals(FIRST_NAME, underTest.asUser().getFirstName());
        assertEquals(LAST_NAME, underTest.asUser().getLastName());
        assertEquals(BIRTHDATE, underTest.asUser().getBirthDate());
        assertEquals(MAIL, underTest.asUser().getEmail());
        assertNull(underTest.asUser().getId());
    }
}