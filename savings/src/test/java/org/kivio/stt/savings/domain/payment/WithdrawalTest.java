package org.kivio.stt.savings.domain.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WithdrawalTest {
    @Test
    @DisplayName("Withdrawal: verify qualified input")
    void verifyQualifiedInput() {
        Withdrawal withdrawal = new Withdrawal(BigDecimal.valueOf(500), LocalDate.now().minusDays(15));
        assertEquals(BigDecimal.valueOf(-500), withdrawal.getAmount());
        assertDoesNotThrow(withdrawal::verify);
    }

    @Test
    @DisplayName("Amount: it fails on null, zero or negative amount")
    void testInvalidAmount() {
        Withdrawal withdrawalNull = new Withdrawal(null);
        assertEquals(BigDecimal.ZERO, withdrawalNull.getAmount());
        assertThrows(PaymentException.class, withdrawalNull::verify);

        Withdrawal withdrawalZero = new Withdrawal(BigDecimal.ZERO);
        assertThrows(PaymentException.class, withdrawalZero::verify);

        Withdrawal withdrawalNegative = new Withdrawal(BigDecimal.valueOf(-1));
        assertThrows(PaymentException.class, withdrawalNegative::verify);
    }
}