package org.kivio.stt.savings.domain.factory;

import lombok.extern.slf4j.Slf4j;
import org.kivio.stt.savings.domain.SavingsTarget;
import org.kivio.stt.savings.domain.SavingsTargetException;
import org.kivio.stt.savings.domain.cmd.AddSavingsTargetCmd;

import javax.enterprise.context.RequestScoped;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;

@Slf4j
@RequestScoped
public class SavingsTargetFactory {
    private static final MathContext PERCENTAGE = new MathContext(0, RoundingMode.HALF_EVEN);

    public SavingsTarget create(AddSavingsTargetCmd addSavingsTargetCmd) {
        // validate the build command - otherwise throw an exception
        assertTargetAmount(addSavingsTargetCmd);
        assertTargetDate(addSavingsTargetCmd);
        assertMonthlySavings(addSavingsTargetCmd);
        assertTargetIsReachable(addSavingsTargetCmd);

        // build object if everything's fine
        return new SavingsTarget(
                addSavingsTargetCmd.getUserId(),
                addSavingsTargetCmd.getName(),
                addSavingsTargetCmd.getTargetAmount(),
                addSavingsTargetCmd.getTargetDate(),
                addSavingsTargetCmd.getMonthlySavings()
        );
    }

    private void assertTargetAmount(AddSavingsTargetCmd addSavingsTargetCmd) {
        if (addSavingsTargetCmd.getTargetAmount() == null ||
                addSavingsTargetCmd.getTargetAmount().compareTo(BigDecimal.ZERO) <= 0 ||
                addSavingsTargetCmd.getTargetAmount().compareTo(addSavingsTargetCmd.getMonthlySavings()) <= 0) {
            throw new SavingsTargetException("Target amount must be positive and greater than your monthly savings");
        }
    }

    private void assertTargetDate(AddSavingsTargetCmd addSavingsTargetCmd) {
        final LocalDate today = LocalDate.now();
        if (addSavingsTargetCmd.getTargetDate().isBefore(today)) {
            throw new SavingsTargetException("Target date is before today");
        }
    }

    private void assertMonthlySavings(AddSavingsTargetCmd addSavingsTargetCmd) {
        if (addSavingsTargetCmd.getMonthlySavings() == null ||
                addSavingsTargetCmd.getMonthlySavings().compareTo(BigDecimal.ZERO) <= 0) {
            throw new SavingsTargetException("Monthly savings must be positive");
        }
    }

    private void assertTargetIsReachable(AddSavingsTargetCmd addSavingsTargetCmd) {
        int monthsToGo = addSavingsTargetCmd.getTargetAmount()
                .divide(addSavingsTargetCmd.getMonthlySavings(), PERCENTAGE).intValue();
        final LocalDate expectedDateToReachTarget = LocalDate.now().plusMonths(monthsToGo);

        log.info("Calculated months to go: {}", monthsToGo);
        log.info("Expected date: {}, target date: {}", expectedDateToReachTarget, addSavingsTargetCmd.getTargetDate());

        if (addSavingsTargetCmd.getTargetDate().isBefore(expectedDateToReachTarget)) {
            throw new SavingsTargetException("The target cannot be reached in " + monthsToGo + " months");
        }
    }
}
