package org.kivio.stt.savings.adapter.jpa;

import org.kivio.stt.savings.domain.SavingsTarget;

import java.util.List;
import java.util.Optional;

public interface SavingsTargetRepository {
    Optional<SavingsTarget> findById(String id);
    List<SavingsTarget> findByUserId(String userId);
    void save(SavingsTarget savingsTarget);
    void deleteById(String id);
    SavingsTarget update(SavingsTarget savingsTarget);
}
