package pl.envelo.meetek.domain.event.model;

import java.util.Arrays;
import java.util.Optional;

public enum TimeStatus {

    FUTURE, PAST;

    public static Optional<TimeStatus> findTimeStatus(String status) {
        return Arrays.stream(TimeStatus.values())
                .filter(ts -> ts.toString().equals(status.toUpperCase()))
                .findFirst();
    }

}
