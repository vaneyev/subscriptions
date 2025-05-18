package test.subscriptions.service;

import test.subscriptions.dto.SubscriptionRequest;
import test.subscriptions.dto.SubscriptionResponse;
import test.subscriptions.entity.SubscriptionSummary;

import java.util.List;
import java.util.UUID;

public interface SubscriptionService {
    List<SubscriptionResponse> getSubscriptions(UUID userId);

    SubscriptionResponse postSubscription(UUID userId, SubscriptionRequest request);

    void deleteSubscription(UUID userId, UUID subscriptionId);

    List<SubscriptionSummary> getTopSubscriptions();
}
