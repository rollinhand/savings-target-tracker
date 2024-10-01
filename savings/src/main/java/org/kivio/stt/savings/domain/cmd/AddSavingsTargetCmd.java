package org.kivio.stt.savings.domain.cmd;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddSavingsTargetCmd {
    private String userId;
    private String name;
    private BigDecimal targetAmount;
    private LocalDate targetDate;
    private BigDecimal monthlySavings;

    @ConstructorProperties({"userId", "name", "targetAmount", "targetDate", "monthlySavings"})
    public AddSavingsTargetCmd(String userId, String name, BigDecimal targetAmount, LocalDate targetDate, BigDecimal monthlySavings) {
        this.userId = userId;
        this.name = name;
        this.targetAmount = targetAmount;
        this.targetDate = targetDate;
        this.monthlySavings = monthlySavings;
    }
}
