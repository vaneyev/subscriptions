package test.subscriptions.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.subscriptions.dto.UserRequest;
import test.subscriptions.dto.UserResponse;
import test.subscriptions.entity.User;
import test.subscriptions.repository.SubscriptionRepository;
import test.subscriptions.repository.UserRepository;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public UserResponse getUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> createUserNotFoundException(id));
        return new UserResponse()
                .setUserId(user.getUserId())
                .setName(user.getName());
    }

    @Override
    @Transactional
    public void deleteUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> createUserNotFoundException(id));
        subscriptionRepository.deleteByUserId(user.getUserId());
        userRepository.delete(user);
    }

    @Override
    public UserResponse postUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        User savedUser = userRepository.save(user);
        return new UserResponse().setUserId(savedUser.getUserId()).setName(savedUser.getName());
    }

    @Override
    @Transactional
    public UserResponse putUser(UUID id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> createUserNotFoundException(id));
        user.setName(userRequest.getName());
        User savedUser = userRepository.save(user);
        return new UserResponse().setUserId(savedUser.getUserId()).setName(savedUser.getName());
    }

    private NoSuchElementException createUserNotFoundException(UUID id) {
        return new NoSuchElementException("User not found with id: " + id);
    }
}
