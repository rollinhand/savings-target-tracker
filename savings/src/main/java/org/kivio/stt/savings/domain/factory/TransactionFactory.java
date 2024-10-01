package org.kivio.stt.savings.domain.factory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kivio.stt.savings.domain.SavingsTarget;
import org.kivio.stt.savings.domain.Transaction;
import org.kivio.stt.savings.domain.payment.Payment;
import org.kivio.stt.savings.domain.payment.PaymentException;

import javax.enterprise.context.RequestScoped;
import java.util.Objects;

@Slf4j
@RequestScoped
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class TransactionFactory {

    /**
     * Validate and create a transaction from the given deposit or withdrawal.
     * @param payment instance of deposit or withdrawal.
     * @return a transaction.
     */
    public Transaction create(SavingsTarget savingsTarget, Payment payment) throws PaymentException {
        Objects.requireNonNull(savingsTarget, "savingsTargetId must not be null");
        Objects.requireNonNull(payment, "payment must not be null");

        // validate and transform payment
        payment.verify();
        return new Transaction(savingsTarget, payment.getAmount(), payment.getPaymentDate());
    }
}
