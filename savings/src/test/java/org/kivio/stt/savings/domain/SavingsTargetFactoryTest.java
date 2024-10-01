package org.kivio.stt.savings.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kivio.stt.savings.domain.cmd.AddSavingsTargetCmd;
import org.kivio.stt.savings.domain.factory.SavingsTargetFactory;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SavingsTargetFactoryTest {
    public static final String USER_1 = "user1";
    public static final String SAVE_FOR_VACATION = "Save for vacation";

    private SavingsTargetFactory savingsTargetFactory;

    @BeforeEach
    void setUp() {
        savingsTargetFactory = new SavingsTargetFactory();
    }


    @Test
    @DisplayName("Valid input: No exception should be thrown for correct savings target.")
    void testCreate_validInput() {
        AddSavingsTargetCmd cmd = new AddSavingsTargetCmd(USER_1, SAVE_FOR_VACATION,
                BigDecimal.valueOf(10000), LocalDate.now().plusMonths(20), BigDecimal.valueOf(500));

        assertDoesNotThrow(() -> savingsTargetFactory.create(cmd));
    }

    @Test
    @DisplayName("Invalid target amount: Zero or negative target amount should throw an exception.")
    void testTargetAmount_zeroOrNegative_throwsException() {
        AddSavingsTargetCmd cmdZero = new AddSavingsTargetCmd(USER_1, SAVE_FOR_VACATION,
                BigDecimal.ZERO, LocalDate.now().plusMonths(20), BigDecimal.valueOf(500));
        assertThrows(SavingsTargetException.class, () -> savingsTargetFactory.create(cmdZero));

        AddSavingsTargetCmd cmdNegative = new AddSavingsTargetCmd(USER_1, SAVE_FOR_VACATION,
                BigDecimal.valueOf(-1000), LocalDate.now().plusMonths(20), BigDecimal.valueOf(500));
        assertThrows(SavingsTargetException.class, () -> savingsTargetFactory.create(cmdNegative));
    }

    @Test
    @DisplayName("Invalid target amount: Target amount less than or equal to monthly savings should throw an exception.")
    void testTargetAmount_lessThanOrEqualToMonthlySavings_throwsException() {
        AddSavingsTargetCmd cmdEqual = new AddSavingsTargetCmd(USER_1, SAVE_FOR_VACATION,
                BigDecimal.valueOf(500), LocalDate.now().plusMonths(20), BigDecimal.valueOf(500));
        assertThrows(SavingsTargetException.class, () -> savingsTargetFactory.create(cmdEqual));

        AddSavingsTargetCmd cmdLess = new AddSavingsTargetCmd(USER_1, SAVE_FOR_VACATION,
                BigDecimal.valueOf(400), LocalDate.now().plusMonths(20), BigDecimal.valueOf(500));
        assertThrows(SavingsTargetException.class, () -> savingsTargetFactory.create(cmdLess));
    }

    @Test
    @DisplayName("Invalid target date: Target date in the past should throw an exception.")
    void testTargetDate_inThePast_throwsException() {
        AddSavingsTargetCmd cmd = new AddSavingsTargetCmd(USER_1, SAVE_FOR_VACATION,
                BigDecimal.valueOf(10000), LocalDate.now().minusDays(1), BigDecimal.valueOf(500));
        assertThrows(SavingsTargetException.class, () -> savingsTargetFactory.create(cmd));
    }

    @Test
    @DisplayName("Invalid monthly savings: Zero or negative monthly savings should throw an exception.")
    void testMonthlySavings_zeroOrNegative_throwsException() {
        AddSavingsTargetCmd cmdZero = new AddSavingsTargetCmd(USER_1, SAVE_FOR_VACATION,
                BigDecimal.valueOf(10000), LocalDate.now().plusMonths(20), BigDecimal.ZERO);
        assertThrows(SavingsTargetException.class, () -> savingsTargetFactory.create(cmdZero));

        AddSavingsTargetCmd cmdNegative = new AddSavingsTargetCmd(USER_1, SAVE_FOR_VACATION,
                BigDecimal.valueOf(10000), LocalDate.now().plusMonths(20), BigDecimal.valueOf(-100));
        assertThrows(SavingsTargetException.class, () -> savingsTargetFactory.create(cmdNegative));
    }

    @Test
    @DisplayName("Unreachable target: If the target cannot be reached in the given timeframe, an exception should be thrown.")
    void testTargetNotReachable_throwsException() {
        AddSavingsTargetCmd cmd = new AddSavingsTargetCmd(USER_1, SAVE_FOR_VACATION,
                BigDecimal.valueOf(10000), LocalDate.now().plusMonths(5), BigDecimal.valueOf(500));
        assertThrows(SavingsTargetException.class, () -> savingsTargetFactory.create(cmd));
    }

    @Test
    @DisplayName("Reachable target: If the target can be reached in the given timeframe, no exception should be thrown.")
    void testTargetReachable_noExceptionThrown() {
        AddSavingsTargetCmd cmd = new AddSavingsTargetCmd(USER_1, SAVE_FOR_VACATION,
                BigDecimal.valueOf(6000), LocalDate.now().plusMonths(12), BigDecimal.valueOf(500));
        assertDoesNotThrow(() -> savingsTargetFactory.create(cmd));
    }
}