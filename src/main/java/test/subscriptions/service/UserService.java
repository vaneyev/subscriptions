package test.subscriptions.service;

import test.subscriptions.dto.UserRequest;
import test.subscriptions.dto.UserResponse;

import java.util.UUID;

public interface UserService {
    UserResponse getUser(UUID id);

    void deleteUser(UUID id);

    UserResponse postUser(UserRequest userRequest);

    UserResponse putUser(UUID userId, UserRequest userRequest);
}
