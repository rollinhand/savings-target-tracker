package org.kivio.stt.savings.domain.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public abstract class Payment {
    BigDecimal amount;
    LocalDate paymentDate;

    public void verify() throws PaymentException {
        if (paymentDate == null || paymentDate.isAfter(LocalDate.now())) {
            throw new PaymentException("Payment date is after current date");
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) == 0) {
            throw new PaymentException("Payment amount is zero");
        }
    }
}
