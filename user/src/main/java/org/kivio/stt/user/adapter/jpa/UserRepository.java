package org.kivio.stt.user.adapter.jpa;

import org.kivio.stt.user.domain.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    void deleteById(String id);

    User update(User user);

    Optional<User> findById(String id);
}
