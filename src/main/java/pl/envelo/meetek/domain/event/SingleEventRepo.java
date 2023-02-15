package pl.envelo.meetek.domain.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.domain.event.model.SingleEvent;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SingleEventRepo extends JpaRepository<SingleEvent, Long> {

    List<SingleEvent> findAllByDateTimeFromBeforeOrderByDateTimeFromDesc(LocalDateTime dateTime);

    List<SingleEvent> findAllByDateTimeFromAfterOrderByDateTimeFromAsc(LocalDateTime dateTime);

    @Query(value = """
            SELECT * FROM events
            WHERE is_private IS FALSE
            AND date_time_from >= ?1
            AND event_id
            NOT IN (SELECT event_id FROM EVENTS_X_USERS_RESPONSES WHERE user_id = ?2)
            ORDER BY date_time_from ASC
            """,
            nativeQuery = true)
    List<SingleEvent> findAllPublicFutureNotRespondedByUser(LocalDateTime currentDateTime, long userId);

    @Query(value = """
            SELECT * FROM events
            WHERE is_private IS FALSE
            AND date_time_from <= ?1
            AND event_id
            NOT IN (SELECT event_id FROM EVENTS_X_USERS_RESPONSES WHERE user_id = ?2)
            ORDER BY date_time_from DESC
            """,
            nativeQuery = true)
    List<SingleEvent> findAllPublicPastNotRespondedByUser(LocalDateTime currentDateTime, long userId);

    @Query(value = """
            SELECT * FROM events
            WHERE date_time_from >= ?1
            AND event_id
            IN (SELECT event_id FROM EVENTS_X_USERS_RESPONSES
            LEFT JOIN EVENT_RESPONSES
            ON EVENTS_X_USERS_RESPONSES.response_id = EVENT_RESPONSES.response_id
            WHERE user_id = ?2 AND response LIKE ?3)
            ORDER BY date_time_from ASC
            """,
            nativeQuery = true)
    List<SingleEvent> findAllFutureWithResponseByUser(LocalDateTime currentDateTime, long userId, String response);

    @Query(value = """
            SELECT * FROM events
            WHERE date_time_from <= ?1
            AND event_id
            IN (SELECT event_id FROM EVENTS_X_USERS_RESPONSES
            LEFT JOIN EVENT_RESPONSES
            ON EVENTS_X_USERS_RESPONSES.response_id = EVENT_RESPONSES.response_id
            WHERE user_id = ?2 AND response LIKE ?3)
            ORDER BY date_time_from DESC
            """,
            nativeQuery = true)
    List<SingleEvent> findAllPastWithResponseByUser(LocalDateTime currentDateTime, long userId, String response);

    @Query(value = """
            SELECT * FROM events
            WHERE Date_Time_From > ?1 AND owner_id = ?2
            ORDER BY Date_Time_from ASC
            """,
            nativeQuery = true)
    List<SingleEvent> findFutureOwnedByUser(LocalDateTime currentDateTimeFrom, long userId);

    @Query(value = """
            SELECT * FROM events
            WHERE date_time_from <= ?1
            AND owner_id = ?2
            ORDER BY date_time_from DESC
            """,
            nativeQuery = true)
    List<SingleEvent> findPastOwnedByUser(LocalDateTime currentDateTimeFrom, long userId);

    @Modifying
    @Query(value = "UPDATE events SET owner_id = ?2 WHERE event_id = ?1", nativeQuery = true)
    void updateOwner(long eventId, long ownerId);

}

