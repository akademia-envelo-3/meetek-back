package pl.envelo.meetek.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ArgumentNotValidException extends RuntimeException {

    private Map<String, String> violations;

    public ArgumentNotValidException(String message, Map<String, String> violations) {
        super(message);
        this.violations = violations;
    }

}