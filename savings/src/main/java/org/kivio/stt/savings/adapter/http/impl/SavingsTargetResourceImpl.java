package org.kivio.stt.savings.adapter.http.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kivio.stt.savings.adapter.http.SavingsTargetResource;
import org.kivio.stt.savings.adapter.jpa.SavingsTargetRepository;
import org.kivio.stt.savings.domain.SavingsTarget;
import org.kivio.stt.savings.domain.SavingsTargetException;
import org.kivio.stt.savings.domain.Transaction;
import org.kivio.stt.savings.domain.cmd.AddSavingsTargetCmd;
import org.kivio.stt.savings.domain.factory.SavingsTargetFactory;
import org.kivio.stt.savings.domain.factory.TransactionFactory;
import org.kivio.stt.savings.domain.payment.Deposit;
import org.kivio.stt.savings.domain.payment.Payment;
import org.kivio.stt.savings.domain.payment.PaymentException;
import org.kivio.stt.savings.domain.payment.Withdrawal;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

@Slf4j
@RequestScoped
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SavingsTargetResourceImpl implements SavingsTargetResource {
    private SavingsTargetRepository repository;
    private SavingsTargetFactory savingsTargetFactory;
    private TransactionFactory transactionFactory;

    @Inject
    public SavingsTargetResourceImpl(
            SavingsTargetRepository repository,
            SavingsTargetFactory savingsTargetFactory,
            TransactionFactory transactionFactory
    ) {
        this.repository = repository;
        this.savingsTargetFactory = savingsTargetFactory;
        this.transactionFactory = transactionFactory;
    }

    @Override
    public Response createSavings(AddSavingsTargetCmd addSavingsTargetCmd) throws URISyntaxException {
        Objects.requireNonNull(addSavingsTargetCmd);    // TODO: build a wrapper for it
        SavingsTarget savingsTarget = savingsTargetFactory.create(addSavingsTargetCmd);
        repository.save(savingsTarget);
        String uri = "/savings/%s";
        return Response.created(new URI(String.format(uri, savingsTarget.getId()))).build();
    }

    @Override
    public Response deleteSavings(String savingsTargetId) {
        repository.deleteById(savingsTargetId);
        return Response.noContent().build();
    }

    @Override
    public Response getSavingsTarget(String savingsTargetId) {
        SavingsTarget savingsTarget = repository.findById(savingsTargetId)
                .orElseThrow(() -> new SavingsTargetNotFoundException("Savings target with ID " + savingsTargetId + " not found"));
        return Response.ok(savingsTarget).build();
    }

    @Override
    public Response getSavingsTargetByUser(String userId) {
        return Response.ok(repository.findByUserId(userId)).build();
    }

    @Override
    public Response deposit(String savingsTargetId, Deposit deposit) {
        return addTransaction(savingsTargetId, deposit);
    }

    @Override
    public Response withdraw(String savingsTargetId, Withdrawal withdrawal) {
        return addTransaction(savingsTargetId, withdrawal);
    }

    private Response addTransaction(String savingsTargetId, Payment payment) {
        try {
            SavingsTarget savingsTarget = repository.findById(savingsTargetId)
                    .orElseThrow(()-> new SavingsTargetNotFoundException("Savings target with ID " + savingsTargetId + " not found"));
            Transaction transaction = this.transactionFactory.create(savingsTarget, payment);
            savingsTarget.addTransaction(transaction);
            savingsTarget = repository.update(savingsTarget);
            return Response.accepted().build();
        } catch (PaymentException e) {
            throw new SavingsTargetException(e.getMessage());
        }
    }
}
