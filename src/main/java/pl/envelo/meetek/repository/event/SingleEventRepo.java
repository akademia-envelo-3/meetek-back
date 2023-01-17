package pl.envelo.meetek.repository.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.event.SingleEvent;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SingleEventRepo extends JpaRepository<SingleEvent, Long> {

    List<SingleEvent> findAllByDateTimeFromBeforeOrderByDateTimeFromDesc(LocalDateTime dateTime);

    List<SingleEvent> findAllByDateTimeFromAfterOrderByDateTimeFromAsc(LocalDateTime dateTime);

    @Query(value = """
            SELECT * FROM single_event 
            WHERE is_private IS FALSE 
            AND date_time_from <= ?1 
            AND single_event.event_id 
            NOT IN (SELECT single_event_event_id FROM single_event_participants WHERE participants_key = ?2 AND participants_event_response_id LIKE '1') 
            ORDER BY date_time_from DESC
            """,
            nativeQuery = true)
    List<SingleEvent> findAllPublicPastNotAcceptedByUser(LocalDateTime currentDateTime, long userId);

    @Query(value = """
            SELECT * FROM single_event 
            WHERE is_private IS FALSE 
            AND date_time_from >= ?1 
            AND single_event.event_id 
            NOT IN (SELECT single_event_event_id FROM single_event_participants WHERE participants_key = ?2 AND participants_event_response_id LIKE '1') 
            ORDER BY date_time_from ASC
            """,
            nativeQuery = true)
    List<SingleEvent> findAllPublicFutureNotAcceptedByUserAll(LocalDateTime currentDateTime, long userId);

    @Query(value = """
            SELECT * FROM single_event 
            WHERE is_private IS FALSE 
            AND date_time_from BETWEEN ?1 AND ?2 
            AND single_event.event_id 
            NOT IN (SELECT single_event_event_id FROM single_event_participants WHERE participants_key = ?3 AND participants_event_response_id LIKE '1') 
            ORDER BY date_time_from ASC 
            """,
            nativeQuery = true)
    List<SingleEvent> findAllPublicFutureNotAcceptedByUserForFewDays(LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo, long userId);

}
