package test.subscriptions.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.subscriptions.dto.SubscriptionRequest;
import test.subscriptions.dto.SubscriptionResponse;
import test.subscriptions.entity.Subscription;
import test.subscriptions.entity.SubscriptionSummary;
import test.subscriptions.entity.User;
import test.subscriptions.repository.SubscriptionRepository;
import test.subscriptions.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultSubscriptionService implements SubscriptionService {
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public List<SubscriptionResponse> getSubscriptions(UUID userId) {
        return subscriptionRepository.findAllByUser_UserId(userId).stream()
                .map((s) -> new SubscriptionResponse()
                        .setId(s.getSubscriptionId())
                        .setUserId(userId)
                        .setName(s.getName()))
                .toList();
    }

    @Override
    public SubscriptionResponse postSubscription(UUID userId, SubscriptionRequest request) {
        User user = userRepository.getReferenceById(userId);
        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setName(request.getName());
        Subscription response = subscriptionRepository.save(subscription);
        return new SubscriptionResponse().setId(response.getSubscriptionId()).setUserId(userId).setName(response.getName());
    }

    @Override
    @Transactional
    public void deleteSubscription(UUID userId, UUID subscriptionId) {
        Optional<Subscription> subscription = subscriptionRepository.findById(subscriptionId);
        if (subscription.isEmpty() || !subscription.get().getUser().getUserId().equals(userId)) {
            throw new NoSuchElementException("Subscription not found with id: " + subscriptionId);
        }
        subscriptionRepository.deleteById(subscriptionId);
    }

    @Override
    public List<SubscriptionSummary> getTopSubscriptions() {
        return subscriptionRepository.getTopSubscriptions();
    }
}
