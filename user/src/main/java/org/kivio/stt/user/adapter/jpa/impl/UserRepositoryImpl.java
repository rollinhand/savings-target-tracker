package org.kivio.stt.user.adapter.jpa.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.meecrowave.jpa.api.Jpa;
import org.apache.meecrowave.jpa.api.Unit;
import org.kivio.stt.user.adapter.jpa.UserRepository;
import org.kivio.stt.user.domain.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Jpa
@RequestScoped
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRepositoryImpl implements UserRepository {
    private EntityManager em;

    @Inject
    public UserRepositoryImpl(@Unit(name = "stt") EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public User save(final User user) {
        em.persist(user);
        return user;
    }

    @Override
    public void deleteById(String id) {
        Optional.ofNullable(em.find(User.class, id)).ifPresent(em::remove);
    }

    @Override
    public User update(User user) {
        return em.merge(user);
    }

    @Override
    @Jpa(transactional = false)
    public Optional<User> findById(final String id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Override
    public List<User> findAll() {
        return em.createNamedQuery("User.findAll", User.class).getResultList();
    }
}
