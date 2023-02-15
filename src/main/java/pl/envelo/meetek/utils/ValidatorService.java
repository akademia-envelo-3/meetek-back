package pl.envelo.meetek.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.exceptions.ArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@Service
public abstract class ValidatorService<T> {

    protected final Validator validator;

    public void validateInput(T t) {
        Set<ConstraintViolation<T>> violations = validator.validate(t);
        if (!violations.isEmpty()) {
            Map<String, String> messages = new HashMap<>();
            for (ConstraintViolation<T> violation : violations) {
                messages.put(violation.getPropertyPath().toString(), violation.getMessage());
            }
            throw new ArgumentNotValidException("Not valid data provided", messages);
        }
    }

    public abstract T validateExists(long id);

}