package pl.envelo.meetek.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.repository.CategoryRepo;

@AllArgsConstructor
@Service
public class CategoryService {

    final private CategoryRepo categoryRepo;
}
