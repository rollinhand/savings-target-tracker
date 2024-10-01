package org.kivio.stt.savings.adapter.jpa.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.meecrowave.jpa.api.Jpa;
import org.apache.meecrowave.jpa.api.Unit;
import org.kivio.stt.savings.adapter.jpa.SavingsTargetRepository;
import org.kivio.stt.savings.domain.SavingsTarget;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Jpa
@RequestScoped
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SavingsTargetRepositoryImpl implements SavingsTargetRepository {
    private EntityManager em;

    @Inject
    public SavingsTargetRepositoryImpl(@Unit(name = "stt") EntityManager em) {
        this.em = em;
    }

    @Override
    @Jpa(transactional = false)
    public Optional<SavingsTarget> findById(String id) {
        return Optional.ofNullable(em.find(SavingsTarget.class, id));
    }

    @Override
    @Jpa(transactional = false)
    public List<SavingsTarget> findByUserId(String userId) {
        TypedQuery<SavingsTarget> query = em.createNamedQuery("SavingsTarget.findByUserId", SavingsTarget.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public void save(SavingsTarget savingsTarget) {
        em.persist(savingsTarget);
    }

    @Override
    public void deleteById(String id) {
        findById(id).ifPresent(em::remove);
    }

    @Override
    public SavingsTarget update(SavingsTarget savingsTarget) {
        return em.merge(savingsTarget);
    }
}
