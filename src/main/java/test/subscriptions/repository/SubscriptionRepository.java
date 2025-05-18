package test.subscriptions.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test.subscriptions.entity.Subscription;
import test.subscriptions.entity.SubscriptionSummary;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, UUID> {
    @Modifying
    @Query("DELETE FROM Subscription subscription WHERE subscription.user.userId = :userId")
    void deleteByUserId(UUID userId);

    List<Subscription> findAllByUser_UserId(UUID userUserId);

    @Query("SELECT new test.subscriptions.entity.SubscriptionSummary(s.name, COUNT(s)) FROM Subscription s GROUP BY s.name ORDER BY COUNT(s) DESC LIMIT 3")
    List<SubscriptionSummary> getTopSubscriptions();
}
