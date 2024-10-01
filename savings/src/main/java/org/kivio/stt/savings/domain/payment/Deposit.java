package org.kivio.stt.savings.domain.payment;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public final class Deposit extends Payment {
    @ConstructorProperties({"amount"})
    public Deposit(BigDecimal amount) {
        this(amount, LocalDate.now());
    }

    @ConstructorProperties({"amount", "paymentDate"})
    public Deposit(BigDecimal amount, LocalDate paymentDate) {
        super(Optional.ofNullable(amount).orElse(BigDecimal.ZERO), paymentDate);
    }

    @Override
    public void verify() throws PaymentException {
        super.verify();
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new PaymentException("Amount must be greater than zero");
        }
    }
}
