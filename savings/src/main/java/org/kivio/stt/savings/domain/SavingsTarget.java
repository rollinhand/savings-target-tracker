package org.kivio.stt.savings.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "stt_savings_targets")
@NamedQuery(name = "SavingsTarget.findByUserId", query = "select st from SavingsTarget st where st.userId = :userId")
@NoArgsConstructor
@JsonbPropertyOrder({
        "id", "userId", "name", "targetAmount", "targetDate", "monthlySavings"
})
public class SavingsTarget implements Serializable {
    private static MathContext PERCENTAGE = new MathContext(0, RoundingMode.HALF_EVEN);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid-hex")
    private String id;
    @Column(name = "user_id")
    private String userId;
    private String name;
    @Column(name = "target_amount")
    private BigDecimal targetAmount;
    @Column(name = "target_date")
    private LocalDate targetDate;
    @Column(name = "monthly_savings")
    private BigDecimal monthlySavings;
    @OneToMany(
            mappedBy = "savingsTarget",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<Transaction> transactions = new ArrayList<>();

    public SavingsTarget(String userId, String name, BigDecimal targetAmount, LocalDate targetDate, BigDecimal monthlySavings) {
        Objects.requireNonNull(userId, "userId must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(targetAmount, "targetAmount must not be null");

        this.userId = userId;
        this.name = name;
        this.targetAmount = targetAmount;
        this.targetDate = targetDate;
        this.monthlySavings = monthlySavings;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public BigDecimal getCurrentSavings() {
        return this.transactions.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getOutstandingBalance() {
        return targetAmount.subtract(getCurrentSavings());
    }

    public BigDecimal getPercentReached() {
        return getCurrentSavings().divide(targetAmount, PERCENTAGE);
    }

    public boolean isTargetReached() {
        BigDecimal currentSavings = getCurrentSavings();
        return targetAmount.subtract(currentSavings).compareTo(BigDecimal.ZERO) <= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SavingsTarget that = (SavingsTarget) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
