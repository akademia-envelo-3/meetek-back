package pl.envelo.meetek.integration.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.category.Category;
import pl.envelo.meetek.domain.category.CategoryDto;
import pl.envelo.meetek.domain.category.CategoryRepo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CategoryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CategoryRepo categoryRepo;

    @Value("/${app.prefix}/${app.version}/categories")
    String prefix;

    String slash = "/";

    @Test
    void getCategory_whenExist_integration_status_200() throws Exception {
        // given
        Category testCategory = new Category("PartyHard", true);
        categoryRepo.save(testCategory);
        // when
        MvcResult mvcResult = mockMvc.perform(get(prefix + slash + testCategory.getCategoryId()))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        Category resultCategory = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Category.class);
        // then
        assertThat(resultCategory).isNotNull();

        assertThat(resultCategory.getCategoryId())
                .isEqualTo(testCategory.getCategoryId());

        assertThat(resultCategory.getName())
                .isEqualTo(resultCategory.getName());
    }

    @Test
    void getCategory_whenNotFound_integration_status_404() throws Exception {
        // given
        Category testCategory = new Category("PartyHard", true);
        categoryRepo.save(testCategory);
        // when
        MvcResult mvcResult = mockMvc.perform(get(prefix + slash + (testCategory.getCategoryId()) + 1L))
                .andDo(print())
                .andExpect(status().is(404))
                .andReturn();
        // then
        assertThat(mvcResult.getResponse().getContentAsString()).isNotNull();
    }

    @Test
    void createCategory_whenDataValid_integration_status_201() throws Exception {
        // given
        CategoryDto categoryDto = new CategoryDto(0, "aaa");
        // when
        MvcResult mvcResult = mockMvc.perform(post(prefix)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDto)))
                .andDo(print())
                .andExpect(status().is(201))
                .andReturn();
        // then
        assertThat(mvcResult.getResponse().getRedirectedUrl()).isNotNull();
    }

    @Test
    void createCategory_whenNameIsToShort_integration_status_201() throws Exception {
        // given
        CategoryDto categoryDto = new CategoryDto(0, "a");
        // when
        MvcResult mvcResult = mockMvc.perform(post(prefix)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDto)))
                .andDo(print())
                .andExpect(status().is(400))
                .andReturn();
        // then
    }

    @Test
    void createCategory_whenNameExist_integration_status_400() throws Exception {
        // given
        Category category = new Category("aaa");
        categoryRepo.save(category);
        CategoryDto categoryDto = new CategoryDto(0, "aaa");
        // when
        MvcResult mvcResult = mockMvc.perform(post(prefix)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDto)))
                .andDo(print())
                .andExpect(status().is(400))
                .andReturn();
        // then
    }

    @Test
    void editCategory_whenDataValid_integration_status_200() throws Exception {
        // given
        Category category = new Category("aaa", true);
        category = categoryRepo.save(category);
        CategoryDto categoryDto = new CategoryDto(category.getCategoryId(), "bbb");
        // when
        MvcResult mvcResult = mockMvc.perform(put(prefix + slash + category.getCategoryId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDto)))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        // then
    }

    @Test
    void getAllActiveCategories_whenFound_integration_status_200() throws Exception {
        // given
        Category category = new Category("aaa", true);
        categoryRepo.save(category);
        // when
        MvcResult mvcResult = mockMvc.perform(get(prefix))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        // then
    }

    @Test
    void getAllActiveCategories_whenNotFound_integration_status_204() throws Exception {
        // given
        // when
        MvcResult mvcResult = mockMvc.perform(get(prefix))
                .andDo(print())
                .andExpect(status().is(204))
                .andReturn();
        // then
    }

}
