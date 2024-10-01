package org.kivio.stt.savings.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {
    public static final String USER_1 = "user1";
    public static final String EMERGENCY_FUND = "Emergency Fund";

    private SavingsTarget savingsTarget;

    @BeforeEach
    void setUp() {
        savingsTarget = new SavingsTarget(USER_1, EMERGENCY_FUND, BigDecimal.valueOf(5000),
                LocalDate.now().plusMonths(6), BigDecimal.valueOf(500));
    }

    @Test
    @DisplayName("Transaction creation: Transaction date should be set to current date.")
    void testTransactionCreation() {
        Transaction transaction = new Transaction(savingsTarget, BigDecimal.valueOf(1000));

        assertNotNull(transaction.getDate());
        assertEquals(LocalDate.now(), transaction.getDate());
    }

    @Test
    @DisplayName("Transaction amount: Transaction should store the correct amount.")
    void testTransactionAmount() {
        Transaction transaction = new Transaction(savingsTarget, BigDecimal.valueOf(2000));

        assertEquals(BigDecimal.valueOf(2000), transaction.getAmount());
    }

    @Test
    @DisplayName("Transaction savings target: Transaction should be associated with the correct savings target.")
    void testTransactionSavingsTarget() {
        Transaction transaction = new Transaction(savingsTarget, BigDecimal.valueOf(1500));

        assertEquals(savingsTarget, transaction.getSavingsTarget());
    }
}