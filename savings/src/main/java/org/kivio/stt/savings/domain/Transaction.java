package org.kivio.stt.savings.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Data
@Entity
@Table(name = "stt_transactions")
@NoArgsConstructor
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid-hex")
    private String id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonbTransient
    private SavingsTarget savingsTarget;
    private BigDecimal amount;
    private LocalDate date;

    public Transaction(SavingsTarget savingsTarget, BigDecimal amount) {
        this(savingsTarget, amount, LocalDate.now());
    }

    public Transaction(SavingsTarget savingsTarget, BigDecimal amount, LocalDate date) {
        this.savingsTarget = savingsTarget;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", savingsTargetId=" + savingsTarget.getId() +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
