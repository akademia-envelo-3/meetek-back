package pl.envelo.meetek.domain.event.model;

import java.util.Arrays;
import java.util.Optional;

public enum EventResponseStatus {

    ACCEPTED, REJECTED, UNDECIDED;

    public static Optional<EventResponseStatus> findResponseStatus(String status) {
        return Arrays.stream(EventResponseStatus.values())
                .filter(rs -> rs.toString().equals(status.toUpperCase()))
                .findFirst();
    }

}
