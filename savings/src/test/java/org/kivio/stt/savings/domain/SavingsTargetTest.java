package org.kivio.stt.savings.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SavingsTargetTest {
    private SavingsTarget savingsTarget;

    @BeforeEach
    void setUp() {
        savingsTarget = new SavingsTarget("user1", "Vacation Fund", BigDecimal.valueOf(10000),
                LocalDate.now().plusMonths(12), BigDecimal.valueOf(500));
    }

    @Test
    @DisplayName("Initial savings: Should be zero when no transactions are added.")
    void testInitialSavings() {
        assertEquals(BigDecimal.ZERO, savingsTarget.getCurrentSavings());
    }

    @Test
    @DisplayName("Add transaction: Adding a valid transaction should update the current savings.")
    void testAddTransaction() {
        Transaction transaction = new Transaction(savingsTarget, BigDecimal.valueOf(1000));
        savingsTarget.addTransaction(transaction);

        assertEquals(BigDecimal.valueOf(1000), savingsTarget.getCurrentSavings());
    }

    @Test
    @DisplayName("Outstanding balance: Should be correct after transactions.")
    void testOutstandingBalance() {
        savingsTarget.addTransaction(new Transaction(savingsTarget, BigDecimal.valueOf(3000)));
        assertEquals(BigDecimal.valueOf(7000), savingsTarget.getOutstandingBalance());
    }

    @Test
    @DisplayName("Percent reached: Should return correct percentage of target achieved.")
    void testPercentReached() {
        savingsTarget.addTransaction(new Transaction(savingsTarget, BigDecimal.valueOf(5000)));
        assertEquals(new BigDecimal("0.5"), savingsTarget.getPercentReached());
    }

    @Test
    @DisplayName("Target reached: Should return true when savings equal or exceed target.")
    void testTargetReached() {
        savingsTarget.addTransaction(new Transaction(savingsTarget, BigDecimal.valueOf(10000)));
        assertTrue(savingsTarget.isTargetReached());
    }

    @Test
    @DisplayName("Target not reached: Should return false when savings are less than target.")
    void testTargetNotReached() {
        savingsTarget.addTransaction(new Transaction(savingsTarget, BigDecimal.valueOf(5000)));
        assertFalse(savingsTarget.isTargetReached());
    }
}