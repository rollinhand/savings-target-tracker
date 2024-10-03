package org.kivio.stt.user.adapter.http.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kivio.stt.user.adapter.http.UserResource;
import org.kivio.stt.user.adapter.jpa.UserRepository;
import org.kivio.stt.user.domain.cmd.AddUserCmd;
import org.kivio.stt.user.domain.cmd.UpdateUserCmd;
import org.kivio.stt.user.domain.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@RequestScoped
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResourceImpl implements UserResource {
    private static final String NOT_FOUND_MESSAGE = "User '%s' not found";
    private UserRepository userRepository;

    @Inject
    public UserResourceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Response getAllUsers() {
        return Response.ok(userRepository.findAll()).build();
    }

    @Override
    public Response getUser(String userId) {
        return Response.ok(
                this.userRepository.findById(userId)
                        .orElseThrow(() -> new UserNotFoundException(String.format(NOT_FOUND_MESSAGE, userId))))
                .build();
    }

    @Override
    public Response addUser(final AddUserCmd addUserCmd) throws URISyntaxException {
        final User addedUser = this.userRepository.save(addUserCmd.asUser());
        String uri = "/users/%s";
        return Response.created(new URI(String.format(uri, addedUser.getId()))).build();
    }

    @Override
    public Response deleteUser(String userId) {
        this.userRepository.deleteById(userId);
        return Response.noContent().build();
    }

    @Override
    public Response updateUser(String userId, final UpdateUserCmd updateUserCmd) {
        final User userToUpdate = this.userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format(NOT_FOUND_MESSAGE, userId)));
        updateUserCmd.update(userToUpdate);
        this.userRepository.update(userToUpdate);
        return Response.accepted().build();
    }
}

