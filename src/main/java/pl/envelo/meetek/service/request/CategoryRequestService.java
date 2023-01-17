package pl.envelo.meetek.service.request;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.repository.request.CategoryRequestRepo;

@AllArgsConstructor
@Service
public class CategoryRequestService {

    private final CategoryRequestRepo categoryRequestRepo;
}
