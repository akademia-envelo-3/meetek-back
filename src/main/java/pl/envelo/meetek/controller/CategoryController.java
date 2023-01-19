package pl.envelo.meetek.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.envelo.meetek.service.CategoryService;
import pl.envelo.meetek.service.DtoMapperService;

@AllArgsConstructor
@RestController
@Tag(name = "Category")
@RequestMapping("/${app.prefix}/${app.version}/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final DtoMapperService dtoMapperService;

}
