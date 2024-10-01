package org.kivio.stt.savings.domain.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DepositTest {

    @Test
    @DisplayName("Deposit: verify qualified input")
    void verifyQualifiedInput() {
        Deposit deposit = new Deposit(BigDecimal.valueOf(10000), LocalDate.now().minusDays(15));
        assertDoesNotThrow(deposit::verify);
    }

    @Test
    @DisplayName("Amount: it fails on null, zero or negative amount")
    void testInvalidAmount() {
        Deposit depositNull = new Deposit(null);
        assertEquals(BigDecimal.ZERO, depositNull.getAmount());
        assertThrows(PaymentException.class, depositNull::verify);

        Deposit depositZero = new Deposit(BigDecimal.ZERO);
        assertThrows(PaymentException.class, depositZero::verify);

        Deposit depositNegative = new Deposit(BigDecimal.valueOf(-1));
        assertThrows(PaymentException.class, depositNegative::verify);
    }

    @Test
    @DisplayName("Payment date: it fails on missing or future dates")
    void testInvalidPaymentDate() {
        Deposit depositMissingDate = new Deposit(BigDecimal.valueOf(10000), null);
        assertThrows(PaymentException.class, depositMissingDate::verify);

        Deposit depositFutureDate = new Deposit(BigDecimal.valueOf(10000), LocalDate.now().plusDays(15));
        assertThrows(PaymentException.class, depositFutureDate::verify);
    }
}