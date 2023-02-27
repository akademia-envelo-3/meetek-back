package pl.envelo.meetek.domain.request.model;

import java.util.Arrays;
import java.util.Optional;

public enum RequestStatus {

    ACCEPTED, REJECTED, NOT_PROCESSED;

    public static Optional<RequestStatus> findRequestStatus(String status) {
        return Arrays.stream(RequestStatus.values())
                .filter(rs -> rs.toString().equals(status.toUpperCase()))
                .findFirst();
    }

}
