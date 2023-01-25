package pl.envelo.meetek.repository.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.event.EventResponse;
import pl.envelo.meetek.model.event.SingleEvent;
import pl.envelo.meetek.model.user.StandardUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface SingleEventRepo extends JpaRepository<SingleEvent, Long> {

    List<SingleEvent> findAllByDateTimeFromBeforeOrderByDateTimeFromDesc(LocalDateTime dateTime);

    List<SingleEvent> findAllByDateTimeFromAfterOrderByDateTimeFromAsc(LocalDateTime dateTime);

    @Query(value = """
            SELECT * FROM events 
            WHERE is_private IS FALSE 
            AND date_time_from <= ?1 
            AND event_id 
            NOT IN (SELECT event_id FROM EVENTS_X_USERS_RESPONSES WHERE user_id = ?2 AND response_id LIKE '1') 
            ORDER BY date_time_from DESC
            """,
            nativeQuery = true)
    List<SingleEvent> findAllPublicPastNotAcceptedByUser(LocalDateTime currentDateTime, long userId);

    @Query(value = """
            SELECT * FROM events 
            WHERE is_private IS FALSE 
            AND date_time_from >= ?1 
            AND event_id 
            NOT IN (SELECT event_id FROM EVENTS_X_USERS_RESPONSES WHERE user_id = ?2 AND response_id LIKE '1') 
            ORDER BY date_time_from ASC
            """,
            nativeQuery = true)
    List<SingleEvent> findAllPublicFutureNotAcceptedByUserAll(LocalDateTime currentDateTime, long userId);

    @Query(value = """
            SELECT * FROM events 
            WHERE is_private IS FALSE 
            AND date_time_from BETWEEN ?1 AND ?2 
            AND event_id 
            NOT IN (SELECT event_id FROM EVENTS_X_USERS_RESPONSES WHERE user_id = ?3 AND response_id LIKE '1') 
            ORDER BY date_time_from ASC
            """,
            nativeQuery = true)
    List<SingleEvent> findAllPublicFutureNotAcceptedByUserForFewDays(LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo, long userId);

    @Query(value = """
            SELECT * FROM events 
            WHERE is_private IS FALSE 
            AND date_time_from <= ?1 
            AND event_id 
            IN (SELECT event_id FROM EVENTS_X_USERS_RESPONSES WHERE user_id = ?2 AND response_id LIKE '1') 
            ORDER BY date_time_from DESC
            """,
            nativeQuery = true)
    List<SingleEvent> findAllPastAcceptedByUser(LocalDateTime currentDateTime, long userId);

    @Query(value = """
            SELECT * FROM events 
            WHERE date_time_from <= ?1
            AND owner_id = ?2
            ORDER BY date_time_from DESC 
            """,
            nativeQuery = true)
    List<SingleEvent> findPastOwnedByUser(LocalDateTime currentDateTimeFrom, long userId);

    @Query(value = """
            SELECT * FROM events
            WHERE date_time_from >= ?1
            AND event_id
            IN (SELECT event_id FROM EVENTS_X_USERS_RESPONSES WHERE user_id = ?2 AND response_id LIKE '1')
            ORDER BY date_time_from ASC 
            """,
            nativeQuery = true)
    List<SingleEvent> findAllFutureAccepted(LocalDateTime currentDateTime, long userId);

    @Query(value = """
            SELECT * FROM events
            WHERE date_time_from BETWEEN ?1 AND ?2 
            AND event_id
            IN (SELECT event_id FROM EVENTS_X_USERS_RESPONSES WHERE user_id = ?3 AND response_id LIKE '1')
            ORDER BY date_time_from ASC 
            """,
            nativeQuery = true)
    List<SingleEvent> findAllFutureAcceptedForFewNearestDays(LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo, long userId);

    @Query(value = """
            SELECT * FROM events
            WHERE Date_Time_From > ?1 AND owner_id = ?2
            ORDER BY Date_Time_from ASC
            """,
            nativeQuery = true)
    List<SingleEvent> findFutureOwnedByUser(LocalDateTime currentDateTimeFrom, long userId);

}

